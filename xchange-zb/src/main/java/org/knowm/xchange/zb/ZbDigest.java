package org.knowm.xchange.zb;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.crypto.Mac;
import javax.ws.rs.FormParam;
import org.knowm.xchange.service.BaseParamsDigest;
import org.knowm.xchange.zb.service.ZbBaseService;
import si.mazi.rescu.RestInvocation;

public class ZbDigest extends BaseParamsDigest {

    protected ZbDigest(String sign) throws IllegalArgumentException {
        super(sign, HMAC_SHA_1);
    }

    public static ZbDigest createInstance(String sign) {
        return sign == null ? null : new ZbDigest(sign);
    }

    //Sign
    public String createPostSign(String apiKey, String apiSecret, BigDecimal amount, String symbol, BigDecimal price, int tradeType) {
        String digest = ZbUtils.digest(apiSecret);
        StringBuilder sb = new StringBuilder();
        sb.append("accesskey=")
                .append(apiKey)
                .append("&acctType=")
                .append("0")
                .append("&amount=")
                .append(amount)
                .append("&currency=")
                .append(symbol)
                .append("&method=order")
                .append("&price=")
                .append(price)
                .append("&tradeType=")
                .append(tradeType);
        String sign = ZbUtils.hmacSign(sb.toString(), digest);

        return sign;
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        return null;
    }
}
