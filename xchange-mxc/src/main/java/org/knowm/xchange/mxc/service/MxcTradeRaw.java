package org.knowm.xchange.mxc.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.knowm.xchange.mxc.MxcAuthenticated;
import org.knowm.xchange.mxc.MxcDigest;

public class MxcTradeRaw extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final org.knowm.xchange.mxc.MxcAuthenticated MxcAuthenticated;
  protected final String apiSecret;
  /**
   * Constructor
   *
   * @param exchange
   */
  protected MxcTradeRaw(Exchange exchange) {
    super(exchange);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.MxcAuthenticated =
        ExchangeRestProxyBuilder.forInterface(
                MxcAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
  }

  public MxcTradeResponse placeLimitOrderRaw(LimitOrder limitOrder) {
    String symbol =
        (limitOrder.getCurrencyPair().base + "_" + limitOrder.getCurrencyPair().counter);
    int reqTime = (int) System.currentTimeMillis() / 1000;
    String sign =
        "POST"
            + "\\n"
            + "/open/api/v2/order/place"
            + "\\n"
            + "api_key="
            + apiKey
            + "&req_time="
            + reqTime
            + "&secret_key="
            + apiSecret;
    String encodedSign = MxcDigest.createInstance(sign).toString();

    MxcTradeResponse response =
        MxcAuthenticated.limitOrder(
            apiKey,
            Integer.toString(reqTime),
            encodedSign,
            symbol,
            limitOrder.getLimitPrice(),
            limitOrder.getOriginalAmount(),
            "trade",
            "buy");
    return response;
  }
}
