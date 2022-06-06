package org.known.xchange.gopax.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.known.xchange.gopax.GopaxAuthenticated;
import org.known.xchange.gopax.GopaxDigest;

public class GopaxBaseService extends BaseExchangeService implements BaseService {
    protected final GopaxAuthenticated gopax;
    protected final String apiKey;
    protected final String apiSecret;
    protected final String url;
    protected GopaxDigest signatureCreator;

    /**
     * Constructor
     *
     * @param exchange
     */
    public GopaxBaseService(Exchange exchange) {
        super(exchange);
        this.gopax =
                ExchangeRestProxyBuilder.forInterface(
                        GopaxAuthenticated.class, exchange.getExchangeSpecification())
                        .build();
        this.apiKey = exchange.getExchangeSpecification().getApiKey();
        this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
        this.url = exchange.getExchangeSpecification().getSslUri();
        this.signatureCreator = GopaxDigest.createInstance(this.apiSecret);
    }
}
