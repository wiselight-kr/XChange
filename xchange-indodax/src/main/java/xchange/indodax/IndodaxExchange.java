package xchange.indodax;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import si.mazi.rescu.SynchronizedValueFactory;
import xchange.indodax.service.IndodaxTradeService;

public class IndodaxExchange extends BaseExchange implements Exchange {
  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return null;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://indodax.com/");
    exchangeSpecification.setHost("https://www.indodax.com");
    exchangeSpecification.setExchangeName("Indodax");

    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.tradeService = new IndodaxTradeService(this);
  }
}
