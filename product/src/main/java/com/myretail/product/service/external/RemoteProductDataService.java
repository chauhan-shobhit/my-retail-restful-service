package com.myretail.product.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.myretail.product.model.exception.ProductNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;

/**
 *
 * @author shobhit chauhan
 *
 */

@Service
public class RemoteProductDataService {

    private final Logger LOGGER = LoggerFactory.getLogger(RemoteProductDataService.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${external.application.url.key}")
    private String key;

    @Value("${external.application.url}")
    private String resourceUrl;

    ResponseEntity<String> response;

    public String getProductNameById(Integer id) {

        Integer tcin = id;

        try {
            response = restTemplate.getForEntity(resourceUrl, String.class, key, tcin);
        } catch (HttpClientErrorException hce) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        JSONObject obj = new JSONObject(response.getBody());

        JSONObject productArr = obj.getJSONObject("data").getJSONObject("product").getJSONObject("item")
                .getJSONObject("product_description");

        String title = productArr.getString("title");

        return title;

    }

}
