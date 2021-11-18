package org.knowm.xchange.zb.dto;

public class ZbException extends RuntimeException {

    private String message;

    public ZbException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
