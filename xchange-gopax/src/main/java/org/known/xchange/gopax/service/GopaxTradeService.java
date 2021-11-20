package org.known.xchange.gopax.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;

public class GopaxTradeService extends GopaxTradeServiceRaw implements TradeService {
    /**
     * Constructor
     *
     * @param exchange
     */
    public GopaxTradeService(Exchange exchange) {
        super(exchange);
    }


    public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

        return placeLimitOrderRaw(
                (CurrencyPair) limitOrder.getInstrument(),
                limitOrder.getType(),
                limitOrder.getLimitPrice(),
                limitOrder.getOriginalAmount())
                .getStatusCode();
    }
}
