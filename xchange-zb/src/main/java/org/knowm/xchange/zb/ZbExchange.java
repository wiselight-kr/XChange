package org.knowm.xchange.zb;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import org.knowm.xchange.zb.service.ZbTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ZbExchange extends BaseExchange implements Exchange {

    private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeIncrementalNonceFactory(MILLISECONDS);

    @Override
    protected void initServices() {
        this.tradeService = new ZbTradeService(this);
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return nonceFactory;
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
        exchangeSpecification.setSslUri("https://trade.zb.team");
        exchangeSpecification.setHost("www.zb.com");
        exchangeSpecification.setExchangeName("Zb");

        return exchangeSpecification;
    }
}
