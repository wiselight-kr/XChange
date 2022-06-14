package org.knowm.xchange.indodax.service;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.service.trade.TradeService;

public class IndodaxTradeService extends org.knowm.xchange.indodax.service.IndodaxTradeServiceRaw implements TradeService {

  public IndodaxTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    System.out.println("Indodax : Limit order");

    return placeLimitOrderRaw(
            (CurrencyPair) limitOrder.getInstrument(),
            limitOrder.getType() == OrderType.BID ? "buy" : "sell",
            limitOrder.getLimitPrice(),
            limitOrder.getOriginalAmount())
        .get_return()
        .getOrder_id();
  }

  @Override
  public String placeMarketOrder(MarketOrder marketorder) throws IOException {

    return "ASDF";
  }

}
