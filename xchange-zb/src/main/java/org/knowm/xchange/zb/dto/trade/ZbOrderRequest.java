package org.knowm.xchange.zb.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZbOrderRequest {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    private String symbol;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("tradeType")
    private int tradeType;
}
