package org.knowm.xchange.mxc.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.mxc.dto.trade.MxcTradeResponse;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.knowm.xchange.mxc.MxcAuthenticated;
import org.knowm.xchange.mxc.MxcDigest;

import java.io.IOException;
import java.math.BigDecimal;

public class MxcTradeServiceRaw extends MxcBaseService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected MxcTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public MxcTradeResponse placeLimitOrderRaw(
          CurrencyPair pair, String type, BigDecimal price, BigDecimal amount) throws IOException {
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
