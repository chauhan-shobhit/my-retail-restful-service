package com.myretail.product.dao;

import com.myretail.product.model.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    // @Query("{id:'?0'}")
    // public Optional<Product> getProductById(Integer id);

}
