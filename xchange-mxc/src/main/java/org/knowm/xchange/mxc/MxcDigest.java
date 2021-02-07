package org.knowm.xchange.mxc;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import javax.crypto.Mac;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class MxcDigest extends BaseParamsDigest {
  private MxcDigest(String sign) throws IllegalArgumentException {
    super(sign, HMAC_SHA_256);
  }

  public static MxcDigest createInstance(String sign) {
    return sign == null ? null : new MxcDigest(sign);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    try {
      String postBody = restInvocation.getRequestBody();
      Mac mac = getMac();
      mac.update(postBody.getBytes("UTF-8"));
      return String.format("%0128x", new BigInteger(1, mac.doFinal()));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Illegal encoding, check the code.", e);
    }
  }
}
