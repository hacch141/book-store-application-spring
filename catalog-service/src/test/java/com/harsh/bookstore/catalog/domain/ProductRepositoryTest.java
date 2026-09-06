package com.harsh.bookstore.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.harsh.bookstore.catalog.ContainersConfig;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(properties = "spring.test.database.replace=none")
@Import(ContainersConfig.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindAllProducts() {
        assertThat(productRepository.findAll()).hasSize(15);
    }

    @Test
    void shouldFindProductByCode() {
        ProductEntity product = productRepository.findByCode("P100").orElseThrow();

        assertThat(product.getName()).isEqualTo("The Hunger Games");
        assertThat(product.getPrice()).isEqualByComparingTo(new BigDecimal("34.00"));
    }

    @Test
    void shouldReturnEmptyForUnknownCode() {
        assertThat(productRepository.findByCode("UNKNOWN")).isEmpty();
    }
}
