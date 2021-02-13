package org.knowm.xchange.mxc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class MxcDigest extends BaseParamsDigest {

  private ObjectMapper mapper = new ObjectMapper();
  TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

  private MxcDigest(String sign) throws IllegalArgumentException {
    super(sign, HMAC_SHA_256);
  }

  public static MxcDigest createInstance(String sign) {
    return sign == null ? null : new MxcDigest(sign);
  }

  public static String urlEncode(String s) {
    try {
      return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("UTF-8 encoding not supported!");
    }
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    try {
      StringBuilder sb = new StringBuilder(1024);
      sb.append(restInvocation.getHttpMethod().toUpperCase())
          .append('\n')
          .append(restInvocation.getInvocationUrl())
          .append('\n');

      HashMap<String, Object> map = mapper.readValue(restInvocation.getRequestBody(), typeRef);
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue().toString();
        sb.append(key).append('=').append(urlEncode(value)).append('&');
      }
      sb.deleteCharAt(sb.length() - 1);

      Mac mac = getMac();
      mac.update(sb.toString().getBytes("UTF-8"));
      return String.format("%0128x", new BigInteger(1, mac.doFinal()));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Illegal encoding, check the code.", e);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
