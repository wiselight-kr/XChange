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
        String sign =
                this.signatureCreator.createPostSign(apiKey, apiSecret, amount, symbol, price, tradeType);


        ZbOrderResponse response = zb.limitOrder("order", this.apiKey, sign, nonce, request);
        return response;
    }
}
