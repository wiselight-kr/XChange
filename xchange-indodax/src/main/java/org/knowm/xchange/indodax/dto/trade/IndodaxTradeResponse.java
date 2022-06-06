package org.knowm.xchange.indodax.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndodaxTradeResponse {

  private Integer success;
  private String error;

  @JsonProperty("return")
  private Return _return;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Return {
    private String order_id;
  }
}
