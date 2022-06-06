package org.known.xchange.gopax;

import org.knowm.xchange.service.BaseParamsDigest;
import org.known.xchange.gopax.dto.trade.GopaxTradeRequest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Base64;

public class GopaxDigest extends BaseParamsDigest {

    protected GopaxDigest(String secretKey) throws IllegalArgumentException {
        super(decodeBase64(secretKey), HMAC_SHA_512);
    }

    public static GopaxDigest createInstance(String secretKey) {
        return secretKey == null ? null : new GopaxDigest(secretKey);
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("t")
                    .append(restInvocation.getHttpHeadersFromParams().get("timestamp"))
                    .append(restInvocation.getHttpMethod())
                    .append('/')
                    .append(restInvocation.getPath())
                    .append(restInvocation.getRequestBody());

            Mac mac = getMac();
            mac.update(sb.toString().getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(mac.doFinal());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Illegal encoding, check the code.", e);
        }
    }
}
