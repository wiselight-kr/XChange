package org.known.xchange.gopax.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GopaxTradeRequest {
    @JsonProperty("tradingPairName")
    private String symbol;

    @JsonProperty("side")
    private String tradeType;

    @JsonProperty("type")
    private String orderType;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("amount")
    private BigDecimal amount;

}
