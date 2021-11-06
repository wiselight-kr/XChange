package org.knowm.xchange.mxc;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.mxc.service.MxcTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MxcExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeIncrementalNonceFactory(MILLISECONDS);

  @Override
  protected void initServices() {
    this.tradeService = new MxcTradeService(this);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://www.mexc.com");
    exchangeSpecification.setHost("www.mexc.com");
    exchangeSpecification.setExchangeName("Mxc");

    return exchangeSpecification;
  }
}
