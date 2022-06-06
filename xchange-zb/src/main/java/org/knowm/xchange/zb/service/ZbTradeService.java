package org.knowm.xchange.zb.service;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;

public class ZbTradeService extends ZbTradeServiceRaw implements TradeService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public ZbTradeService(Exchange exchange) {
        super(exchange);
    }

    public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

        return placeLimitOrderRaw(
                (CurrencyPair) limitOrder.getInstrument(),
                limitOrder.getType(),
                limitOrder.getLimitPrice(),
                limitOrder.getOriginalAmount())
                .getOrderId();
    }

}
