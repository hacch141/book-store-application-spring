package com.harsh.bookstore.catalog.domain;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final int pageSize;

    ProductService(ProductRepository productRepository, @Value("${catalog.page-size}") int pageSize) {
        this.productRepository = productRepository;
        this.pageSize = pageSize;
    }

    public PagedResult<Product> getProducts(int requestedPage) {
        int pageIndex = Math.max(requestedPage, 1) - 1;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("name").ascending());
        Page<Product> page = productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PagedResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
