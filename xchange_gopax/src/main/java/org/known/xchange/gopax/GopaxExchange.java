package org.known.xchange.gopax;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import org.known.xchange.gopax.service.GopaxTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GopaxExchange extends BaseExchange implements Exchange {

    private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeIncrementalNonceFactory(MILLISECONDS);

    @Override
    protected void initServices() {
        this.tradeService = new GopaxTradeService(this);
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return nonceFactory;
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
        exchangeSpecification.setSslUri("https://www.gopax.co.kr");
        exchangeSpecification.setHost("www.gopax.co.kr");
        exchangeSpecification.setExchangeName("Gopax");

        return exchangeSpecification;
    }
}
