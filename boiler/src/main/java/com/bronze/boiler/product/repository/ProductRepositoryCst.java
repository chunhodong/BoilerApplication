package com.bronze.boiler.product.repository;

import com.bronze.boiler.product.domain.Product;
import com.bronze.boiler.product.ui.ProductFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCst {
    List<Product> findAllByPage(ProductFilter productFilter, Pageable pageable);

    List<Product> findAllMaxPrice();
}
