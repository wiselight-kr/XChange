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

    protected GopaxDigest(String sign) throws IllegalArgumentException {
        super(sign, HMAC_SHA_512);
    }

    public static GopaxDigest createInstance(String sign) {
        return sign == null ? null : new GopaxDigest(sign);
    }


    public String createPostSign(String apiSecret, Long timeStamp, String method, String url, GopaxTradeRequest body) {
        StringBuilder sb = new StringBuilder();
        sb.append("t")
                .append(timeStamp)
                .append(method)
                .append(url)
                .append(body.toString());

        byte[] rawSecret = decodeBase64(apiSecret);

        Mac mac = getMac();
        mac.update(rawSecret);

        return Base64.getEncoder().encodeToString(mac.doFinal());
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        return null;
    }
}
