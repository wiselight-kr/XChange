package org.knowm.xchange.indodax;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import javax.crypto.Mac;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class IndodaxDigest extends BaseParamsDigest {
  private IndodaxDigest(String secretKey) throws IllegalArgumentException {
    super(secretKey, HMAC_SHA_512);
  }

  public static IndodaxDigest createInstance(String secretKey) {
    return secretKey == null ? null : new IndodaxDigest(secretKey);
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
