package org.knowm.xchange.mxc.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MxcTradeRequest {

    private String symbol;
    private BigDecimal price;
    private BigDecimal quantity;
    @JsonProperty("trade_type")
    private String tradeType;
    @JsonProperty("order_type")
    private String orderType;
}
