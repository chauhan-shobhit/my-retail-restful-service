package com.myretail.product.service;

import java.util.Optional;

import com.myretail.product.dao.ProductRepository;
import com.myretail.product.model.Product;
import com.myretail.product.model.exception.ProductNotFoundException;
import com.myretail.product.service.external.RemoteProductDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shobhit chauhan
 *
 */
@Slf4j
@Service
public class ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RemoteProductDataService remoteProductDataService;

    public Product getProductById(Integer id) {

        Optional<Product> product = productRepository.findById(id);

        if (productRepository.findById(id).isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        String title = remoteProductDataService.getProductNameById(id);
        product.get().setName(title);

        return product.get();
    }

    public Product updatePriceById(Product product) {

        if (productRepository.findById(product.getId()) == null) {
            productRepository.save(product);
        }

        productRepository.save(product);
        return product;

    }
}
