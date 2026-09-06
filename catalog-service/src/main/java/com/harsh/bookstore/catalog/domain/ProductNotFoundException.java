package com.harsh.bookstore.catalog.domain;

public class ProductNotFoundException extends RuntimeException {

    private ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}
