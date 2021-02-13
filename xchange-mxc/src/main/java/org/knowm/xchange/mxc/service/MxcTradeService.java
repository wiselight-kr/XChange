package org.knowm.xchange.mxc.service;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.trade.TradeService;

public class MxcTradeService extends MxcTradeServiceRaw implements TradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public MxcTradeService(Exchange exchange) {
    super(exchange);
  }

  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    return placeLimitOrderRaw(
            limitOrder.getCurrencyPair(),
            limitOrder.getType(),
            limitOrder.getLimitPrice(),
            limitOrder.getOriginalAmount())
        .getData();
  }
}
