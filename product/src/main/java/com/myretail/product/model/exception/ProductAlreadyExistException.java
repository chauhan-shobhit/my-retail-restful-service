package com.myretail.product.model.exception;

public class ProductAlreadyExistException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private String message;

  public ProductAlreadyExistException(String message) {
    super(message);
    this.message = message;
  }

  public ProductAlreadyExistException() {
    super();
  }

  public String getFieldName() {
    return message;
  }

  public void setFieldName(String message) {
    this.message = message;
  }

}
