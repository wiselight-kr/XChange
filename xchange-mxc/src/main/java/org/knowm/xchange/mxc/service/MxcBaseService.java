package org.knowm.xchange.mxc.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.mxc.MxcAuthenticated;
import org.knowm.xchange.mxc.MxcDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;

public class MxcBaseService extends BaseExchangeService implements BaseService {

  protected final MxcAuthenticated mxc;
  protected final String apiKey;
  protected final String apiSecret;
  protected final String url;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public MxcBaseService(Exchange exchange) {
    super(exchange);
    this.mxc =
        ExchangeRestProxyBuilder.forInterface(
                MxcAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
    this.url = exchange.getExchangeSpecification().getSslUri();
    this.signatureCreator = MxcDigest.createInstance(this.apiSecret);
  }
}
