package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.filter.ProductFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCst {
    List<Product> findAllByPage(ProductFilter productFilter, Pageable pageable);
}
