package org.knowm.xchange.upbit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class UpbitException extends HttpStatusExceptionSupport {

  private UpbitError error;

  public UpbitException(@JsonProperty("error") UpbitError error) {
    super();
    this.error = error;
  }

  public static class UpbitError {

    private String name;
    private String message;

    UpbitError(@JsonProperty("name") String name,
               @JsonProperty("message") String message) {
      this.name = name;
      this.message = message;
    }

  }

  @Override
  public String getMessage() {
    return this.error.message;
  }

}
