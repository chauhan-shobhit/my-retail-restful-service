package com.myretail.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.myretail.product.dao.ProductRepository;
import com.myretail.product.model.Price;
import com.myretail.product.model.Product;
import com.myretail.product.model.exception.ProductNotFoundException;
import com.myretail.product.service.external.RemoteProductDataService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings({})
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RemoteProductDataService remoteProductDataService;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getProductData_ValidParameters_Success() {

        when(productRepository.findById(anyInt())).thenReturn(getProductData());
        when(remoteProductDataService.getProductNameById(13860428)).thenReturn("The Big Lebowski (Blu-ray)");

        assertEquals("The Big Lebowski (Blu-ray)", productService.getProductById(13860428).getName());
        assertEquals("USD", productService.getProductById(13860428).getCurrent_price().getcurrency_code());

        productService.getProductById(13860428);

    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductData_InvalidParameters_Fail() {

        when(productRepository.findById(13860428)).thenReturn(getProductData());
        when(remoteProductDataService.getProductNameById(13860428)).thenReturn("The Big Lebowski (Blu-ray)");

        productService.getProductById(1386042);

    }

    private Optional<Product> getProductData() {
        Product product = new Product(13860428, "The Big Lebowski (Blu-ray)", new Price(new BigDecimal(13.49), "USD"));

        return Optional.of(product);
    }
}
