
package com.myretail.product.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import com.myretail.product.model.Price;
import com.myretail.product.model.Product;

import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product savedProduct;

    @Before
    public void setup() {

        Product product = new Product();
        Price currentPrice = new Price(new BigDecimal(12.34), "USD");
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");
        product.setCurrent_price(currentPrice);

        savedProduct = productRepository.save(product);

    }

    @Test
    public void findProductData_ValidParameter_Success() {
        Product actualResponse = productRepository.findById(savedProduct.getId()).get();
        assertNotNull(actualResponse);
        assertEquals("USD", actualResponse.getCurrent_price().getcurrency_code());
    }

    @Test
    public void findProductData_ValidParameter_Fail() {
        Product actualResponse = productRepository.findById(savedProduct.getId()).get();
        assertEquals("DOL", actualResponse.getCurrent_price().getcurrency_code());
    }

}