package xchange.indodax;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

public class Test {
    public static void main(String args[]) {
        ExchangeSpecification es = new IndodaxExchange().getDefaultExchangeSpecification();
        es.setApiKey("");
        es.setSecretKey("");
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(es):
        Indodaxexchange.getTradeService();
    }
}
