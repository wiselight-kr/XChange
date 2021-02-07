package org.knowm.xchange.mxc;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.mxc.service.MxcTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

public class MxcExchange extends BaseExchange implements Exchange {
  @Override
  protected void initServices() {
    this.tradeService = new MxcTradeService(this);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return null;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://www.mxc.com");
    exchangeSpecification.setHost("https://www.mxc.com");
    exchangeSpecification.setExchangeName("Mxc");

    return exchangeSpecification;
  }
}
