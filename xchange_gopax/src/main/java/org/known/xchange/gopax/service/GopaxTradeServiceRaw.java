package org.known.xchange.gopax.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.known.xchange.gopax.dto.trade.GopaxTradeRequest;
import org.known.xchange.gopax.dto.trade.GopaxTradeResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;

public class GopaxTradeServiceRaw extends GopaxBaseService {
    /**
     * Constructor
     *
     * @param exchange
     */
    public GopaxTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public GopaxTradeResponse placeLimitOrderRaw(
            CurrencyPair pair, Order.OrderType type, BigDecimal price, BigDecimal amount)
            throws IOException {
        GopaxTradeRequest request = new GopaxTradeRequest();
        String symbol = String.format("%s_%s", pair.base.getSymbol().toUpperCase(), pair.counter.getSymbol().toUpperCase());
        request.setSymbol(symbol);
        String tradeType;
        if(type == Order.OrderType.BID)
            tradeType = "buy";
        else
            tradeType = "sell";
        request.setTradeType(tradeType);
        request.setOrderType("limit");
        request.setPrice(price);
        request.setAmount(amount);

        Long timeStamp = exchange.getNonceFactory().createValue();
        String sign =
                this.signatureCreator.createPostSign(apiSecret, timeStamp, "POST", "/orders", request);

        GopaxTradeResponse response = gopax.limitOrder(this.apiKey, timeStamp, sign, request);
        return response;
    }
}
