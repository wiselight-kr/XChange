package org.knowm.xchange.indodax.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.indodax.IndodaxAdapter;
import org.knowm.xchange.indodax.IndodaxDigest;
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
    if (type.equals("sell")) IndodaxDigest.setAmount(pair.base.getSymbol().toLowerCase(), amount);
    else IndodaxDigest.setAmount(pair.counter.getSymbol().toLowerCase(), amount.multiply(price));

    IndodaxTradeResponse response =
        indodax.limitOrder(
            this.apiKey,
            this.signatureCreator,
            exchange.getNonceFactory(),
            "trade",
            IndodaxAdapter.toSymbol(pair),
            type,
            price);
    if (response.getError() != null) throw new ExchangeException(response.getError());
    return response;
  }
}
