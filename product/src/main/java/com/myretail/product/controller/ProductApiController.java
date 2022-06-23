package com.myretail.product.controller;

import com.myretail.product.model.Product;
import com.myretail.product.model.exception.BadRequestException;
import com.myretail.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shobhit chauhan
 *
 */

@Slf4j
@RestController
public class ProductApiController implements ProductApi {

    private static final String BAD_REQUEST_EXCEPTION_MESSAGE = "Required Field Missing.";

    private final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);
    @Autowired
    private ProductService productService;

    public ResponseEntity<Product> getProductById(@PathVariable(required = true) Integer id) {

        if (Integer.toString(id).isBlank()) {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION_MESSAGE);
        }
        Product availableProduct = productService.getProductById(id);

        return new ResponseEntity<>(availableProduct, HttpStatus.OK);
    }

    public ResponseEntity<Product> updatePriceById(@RequestBody Product product) {

        return new ResponseEntity<>(productService.updatePriceById(product), HttpStatus.OK);

    }

}
