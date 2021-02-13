package org.knowm.xchange.mxc.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.mxc.dto.trade.MxcTradeRequest;
import org.knowm.xchange.mxc.dto.trade.MxcTradeResponse;

public class MxcTradeServiceRaw extends MxcBaseService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected MxcTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public MxcTradeResponse placeLimitOrderRaw(
      CurrencyPair pair, Order.OrderType type, BigDecimal price, BigDecimal amount)
      throws IOException {
    MxcTradeRequest request = new MxcTradeRequest();
    request.setOrderType("LIMIT_ORDER");
    request.setTradeType(type.name());
    request.setSymbol(
        String.format(
            "%s_%s", pair.base.getSymbol().toUpperCase(), pair.counter.getSymbol().toUpperCase()));
    request.setPrice(price);
    request.setQuantity(amount);

    MxcTradeResponse response =
        mxc.limitOrder(this.apiKey, exchange.getNonceFactory(), this.signatureCreator, request);
    return response;
  }
}
