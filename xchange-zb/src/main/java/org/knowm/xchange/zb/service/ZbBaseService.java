package org.knowm.xchange.zb.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.knowm.xchange.zb.ZbAuthenticated;
import org.knowm.xchange.zb.ZbDigest;
import si.mazi.rescu.ParamsDigest;

public class ZbBaseService extends BaseExchangeService implements BaseService {

    protected final ZbAuthenticated zb;
    protected final String apiKey;
    protected final String apiSecret;
    protected final String url;
    protected ZbDigest signatureCreator;

    /**
     * Constructor
     *
     * @param exchange
     */
    protected ZbBaseService(Exchange exchange) {
        super(exchange);
        this.zb = ExchangeRestProxyBuilder.forInterface(
                ZbAuthenticated.class, exchange.getExchangeSpecification()
        ).build();
        this.apiKey = exchange.getExchangeSpecification().getApiKey();
        this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
        this.url = exchange.getExchangeSpecification().getSslUri();
        this.signatureCreator = ZbDigest.createInstance(this.apiSecret);
    }
}
