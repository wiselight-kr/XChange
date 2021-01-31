package org.knowm.xchange.indodax.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.indodax.IndodaxAuthenticated;
import org.knowm.xchange.indodax.IndodaxDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;

public class IndodaxBaseService extends BaseExchangeService implements BaseService {

  protected final IndodaxAuthenticated indodax;
  protected final String apiKey;
  protected final String apiSecret;
  protected final String url;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public IndodaxBaseService(Exchange exchange) {
    super(exchange);
    this.indodax =
        ExchangeRestProxyBuilder.forInterface(
                IndodaxAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
    this.url = exchange.getExchangeSpecification().getSslUri();
    this.signatureCreator = IndodaxDigest.createInstance(this.apiSecret);
  }
}
