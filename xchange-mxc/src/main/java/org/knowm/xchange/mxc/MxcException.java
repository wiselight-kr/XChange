package org.knowm.xchange.mxc;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class MxcException extends HttpStatusExceptionSupport {

    private String msg;
    private Integer code;

    public MxcException(@JsonProperty("msg") String msg,
                        @JsonProperty("code") Integer code) {
        super();
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
