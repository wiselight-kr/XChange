package xchange.mxc.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;
import si.mazi.rescu.ParamsDigest;

public class MxcTradeService extends MxcTradeRaw implements TradeService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public MxcTradeService(Exchange exchange) {
        super(exchange);
    }
    public String placeLimitOrder(LimitOrder limitOrder){

        MxcTradeResponse response = placeLimitOrderRaw(limitOrder);

        return response.getOrderId();
    }
}
