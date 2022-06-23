package com.myretail.product.model.exception;

public class InternalServerError extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public InternalServerError(String message) {
        super(message);
        this.message = message;
    }

    public InternalServerError() {
        super();
    }

    public String getFieldName() {
        return message;
    }

    public void setFieldName(String message) {
        this.message = message;
    }
}
