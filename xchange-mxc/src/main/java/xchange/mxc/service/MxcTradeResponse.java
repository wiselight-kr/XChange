package xchange.mxc.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MxcTradeResponse {
    private final String orderId;

    public MxcTradeResponse(@JsonProperty ("data") String data){
        this.orderId = data;
    }

    public String getOrderId() {
        return orderId;
    }
}
