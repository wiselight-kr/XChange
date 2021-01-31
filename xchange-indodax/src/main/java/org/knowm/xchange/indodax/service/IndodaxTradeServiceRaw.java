package org.knowm.xchange.indodax.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.indodax.IndodaxAdapter;
import org.knowm.xchange.indodax.dto.trade.IndodaxTradeResponse;

public class IndodaxTradeServiceRaw extends IndodaxBaseService {

  /**
   * Constructor
   *
   * @param exchange
   */
  protected IndodaxTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public IndodaxTradeResponse placeLimitOrderRaw(
      CurrencyPair pair, String type, BigDecimal price, BigDecimal amount) throws IOException {
    // buy면 idr계산
    // sell면 amount 그대로 빠짐

    IndodaxTradeResponse response =
        indodax.limitOrder(
            this.apiKey,
            this.signatureCreator,
            exchange.getNonceFactory(),
            "trade",
            IndodaxAdapter.toSymbol(pair),
            type,
            price,
            amount);
    return response;
  }
}
