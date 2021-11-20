package org.knowm.xchange.zb.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.zb.ZbDigest;
import org.knowm.xchange.zb.ZbUtils;
import org.knowm.xchange.zb.dto.trade.ZbOrderRequest;
import org.knowm.xchange.zb.dto.trade.ZbOrderResponse;

import java.io.IOException;
import java.math.BigDecimal;

public class ZbTradeServiceRaw extends ZbBaseService {
    /**
     * Constructor
     *
     * @param exchange
     */
    protected ZbTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }


    public ZbOrderResponse placeLimitOrderRaw(
            CurrencyPair pair, Order.OrderType type, BigDecimal price, BigDecimal amount)
            throws IOException {
        ZbOrderRequest request = new ZbOrderRequest();
        request.setAmount(amount);
        String symbol = String.format("%s_%s", pair.base.getSymbol().toLowerCase(), pair.counter.getSymbol().toLowerCase());
        request.setSymbol(symbol);
        request.setPrice(price);
        int tradeType;
        if(type == Order.OrderType.BID)
            tradeType = 1;
        else
            tradeType = 0;
        request.setTradeType(tradeType);

        Long nonce = exchange.getNonceFactory().createValue();
        //String sign =
        //        this.signatureCreator.createPostSign(apiKey, apiSecret, amount, symbol, price, tradeType);
        String digest = ZbUtils.digest(apiSecret);
        StringBuilder sb = new StringBuilder();
        sb.append("accesskey=")
                .append(apiKey)
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

        ZbOrderResponse response = zb.limitOrder(
                this.apiKey,
                amount,
                symbol,
                "order",
                price,
                nonce,
                sign,
                tradeType
        );

        if (response.getCode() != 1000) throw new ExchangeException(response.getMessage());
        return response;
    }
}
