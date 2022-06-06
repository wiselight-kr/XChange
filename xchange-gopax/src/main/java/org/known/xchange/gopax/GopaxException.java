package org.known.xchange.gopax;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class GopaxException extends HttpStatusExceptionSupport {

    private String errorMessage;
    private Integer errorCode;
    private Object errorData;

    public GopaxException(@JsonProperty("errorMessage") String errorMessage,
                         @JsonProperty("errorCode") Integer errorCode,
                         @JsonProperty("errorData") Object errorData) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

}
