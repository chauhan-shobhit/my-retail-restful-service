package com.myretail.product.controller;

import com.myretail.product.model.Product;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ProductApi {

    @Operation(summary = "Get Product Details by Id ", tags = { "Product Details" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product details retrieved successfully", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Malformed Request"
            // content = @Content(schema = @Schema(implementation = Product.class))
            ),

    })
    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable(required = true) Integer id);

    @Operation(summary = "Update price details by Id ", tags = { "Update Price details" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product details retrieved successfully", content = @Content(schema = @Schema(implementation = Product.class))),

    })
    @PutMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updatePriceById(@RequestBody Product product);
}
