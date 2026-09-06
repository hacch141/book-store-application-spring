package com.harsh.bookstore.catalog.domain;

final class ProductMapper {

    private ProductMapper() {}

    static Product toProduct(ProductEntity entity) {
        return new Product(
                entity.getCode(), entity.getName(), entity.getDescription(), entity.getImageUrl(), entity.getPrice());
    }
}
