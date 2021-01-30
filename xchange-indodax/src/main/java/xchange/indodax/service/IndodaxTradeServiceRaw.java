package xchange.indodax.service;

import java.math.BigDecimal;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import xchange.indodax.IndodaxAdapter;
import xchange.indodax.IndodaxAuthenticated;
import xchange.indodax.IndodaxDigest;

public class IndodaxTradeServiceRaw extends BaseExchangeService implements BaseService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected final String apiKey;

  protected final IndodaxAuthenticated indodaxAuthenticated;
  protected final ParamsDigest apiSecret;

  protected IndodaxTradeServiceRaw(Exchange exchange) {
    super(exchange);
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.indodaxAuthenticated =
        ExchangeRestProxyBuilder.forInterface(
                IndodaxAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiSecret =
        IndodaxDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public IndodaxTradeResponse placeLimitOrderRaw(
      CurrencyPair pair, OrderSide type, BigDecimal price, BigDecimal idr, BigDecimal btc) {

    IndodaxTradeResponse response =
        indodaxAuthenticated.limitOrder(
            apiKey,
            apiSecret.toString(),
            "trade",
            IndodaxAdapter.toSymbol(pair),
            "buy",
            limitOrder.getLimitPrice(),
            limitOrder.getOriginalAmount());
    return response;
  }
}
