package xchange.indodax.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;

public class IndodaxTradeService extends IndodaxTradeServiceRaw implements TradeService {

  public IndodaxTradeService(Exchange exchange) {
    super(exchange);
  }

  public String placeLimitOrder(LimitOrder limitOrder) {

    IndodaxTradeResponse response = placeLimitOrderRaw(limitOrder);

    return response.getOrderId();
  }
}
