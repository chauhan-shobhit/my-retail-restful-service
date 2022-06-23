package com.myretail.product.advice;

import com.myretail.product.model.exception.CustomErrorResponse;
import com.myretail.product.model.exception.ExceptionEnum;
import com.myretail.product.model.exception.ProductAlreadyExistException;
import com.myretail.product.model.exception.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class APIExceptionHandlerAdvice {

    private final Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(APIExceptionHandlerAdvice.class);

    @Autowired
    public APIExceptionHandlerAdvice(Environment env) {
        this.env = env;
    }

    @ExceptionHandler({ Throwable.class })
    @ResponseBody
    public ResponseEntity<CustomErrorResponse> handleUnknownExceptions(Throwable e) {
        String errorKey = ExceptionEnum.SERVER_ERROR.getValue();
        String errorCode = getErrorCode(errorKey);
        String errorMessage = getErrorMessage(errorKey);
        LOGGER.error(errorMessage, e);
        CustomErrorResponse response = new CustomErrorResponse(errorCode, errorMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ ProductAlreadyExistException.class })
    @ResponseBody
    public ResponseEntity<CustomErrorResponse> handleProductAlreadyExistException(Throwable e) {
        CustomErrorResponse response = new CustomErrorResponse("100", "Item Already Exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ ProductNotFoundException.class })
    @ResponseBody
    public ResponseEntity<CustomErrorResponse> handleProductNotFoundException(Throwable e) {

        String errorKey = ExceptionEnum.PRODUCT_NOT_FOUND.getValue();
        String errorCode = getErrorCode(errorKey);
        String errorMessage = getErrorMessage(errorKey);
        LOGGER.error(errorMessage, e);
        CustomErrorResponse response = new CustomErrorResponse(errorCode, errorMessage);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private String getErrorCode(final String keyValue) {
        return env.getProperty(keyValue + ".code");
    }

    private String getErrorMessage(final String keyValue) {
        return env.getProperty(keyValue + ".message");
    }
}
