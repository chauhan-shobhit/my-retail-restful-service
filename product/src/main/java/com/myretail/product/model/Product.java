package com.myretail.product.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "productDetails")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Indexed
    private Integer id;
    private String name;
    private Price current_price;

    public Product(Integer id, String name, Price current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }

    public Product() {
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Price return the current_price
     */
    public Price getCurrent_price() {
        return current_price;
    }

    /**
     * @param current_price the current_price to set
     */
    public void setCurrent_price(Price current_price) {
        this.current_price = current_price;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
