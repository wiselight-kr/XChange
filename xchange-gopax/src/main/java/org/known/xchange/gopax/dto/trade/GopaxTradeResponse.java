package org.known.xchange.gopax.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GopaxTradeResponse {
    @JsonProperty("statusCode")
    String statusCode;

    @JsonProperty("id")
    String id;

//    @JsonProperty("clientOrderId")
//    String clientOrderId;
//
//    @JsonProperty("status")
//    String orderStatus;
//
//    @JsonProperty("forceCompletionReason")
//    String forceCompletionReason;
//
//    @JsonProperty("tradingPairName")
//    String symbol;
//
//    @JsonProperty("side")
//    String tradeType;
//
//    @JsonProperty("type")
//    String orderType;
//
//    @JsonProperty("price")
//    BigDecimal price;
//
//    @JsonProperty("stopPrice")
//    BigDecimal stopPrice;
//
//    @JsonProperty("amount")
//    BigDecimal amount;
//
//    @JsonProperty("remaining")
//    BigDecimal remaining;
//
//    @JsonProperty("protection")
//    String protection;

}
