package com.myretail.product.model.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ProductNotFoundException() {
        super();
    }

    public String getFieldName() {
        return message;
    }

    public void setFieldName(String message) {
        this.message = message;
    }
}
