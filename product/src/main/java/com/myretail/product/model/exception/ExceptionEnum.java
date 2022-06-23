package com.myretail.product.model.exception;

public enum ExceptionEnum {

    SERVER_ERROR("error.server.error"),
    PRODUCT_NOT_FOUND("error.application.product.not.found.missing"),
    INVALID_REQUEST("error.application.invalid.request");

    private final String value;

    ExceptionEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getCodeValue() {
        return value + ".code";
    }

    public String getMessageValue() {
        return value + ".message";
    }
}