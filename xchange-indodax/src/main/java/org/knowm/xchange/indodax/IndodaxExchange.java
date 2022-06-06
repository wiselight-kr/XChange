package org.knowm.xchange.indodax;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.indodax.service.IndodaxTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class IndodaxExchange extends BaseExchange {
  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeIncrementalNonceFactory(MILLISECONDS);

  @Override
  protected void initServices() {
    this.tradeService = new IndodaxTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://indodax.com");
    exchangeSpecification.setHost("indodax.com");
    exchangeSpecification.setExchangeName("Indodax");
    exchangeSpecification.setExchangeDescription("Indodax Exchange.");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }
}
