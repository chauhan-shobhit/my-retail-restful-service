package com.myretail.product.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.myretail.product.model.Price;
import com.myretail.product.model.Product;
import com.myretail.product.service.ProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductApiController.class)
public class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Before
    public void setup() {
    }

    private static final String PRODUCT_DETAILS_GET = "/products/13860428";

    @Test
    public void getProductData_ValidParameters_Success() throws Exception {
        when(productService.getProductById(13860428)).thenReturn(getProductData());

        MvcResult actualResponse = mockMvc.perform(get(PRODUCT_DETAILS_GET).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
                .andExpect(jsonPath("$.current_price.currency_code").value("USD"))
                .andReturn();
        // String content = actualResponse.getResponse().getContentAsString();

    }

    @Test
    public void getProductData_BlankParameters_Fail() throws Exception {
        when(productService.getProductById(anyInt())).thenReturn(getProductData());

        mockMvc.perform(get("/products/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getProductData_NonIntegerParameters_Fail() throws Exception {
        String expectedResponse = "{\"errorCode\": \"MR-PS-SE-001\",\"errorMessage\": \"Internal Server Error\"}";
        when(productService.getProductById(anyInt())).thenReturn(getProductData());

        mockMvc.perform(get("/products/asd").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse))
                .andReturn();
        // String content = actualResponse.getResponse().getContentAsString();
    }

    private Product getProductData() {
        return new Product(13860428, "The Big Lebowski (Blu-ray)", new Price(new BigDecimal(13.49), "USD"));
    }

}
