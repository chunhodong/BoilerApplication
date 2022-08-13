package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.filter.ProductPage;

import java.util.List;

public interface ProductRepositoryCst {
    List<Product> findAllByPage(ProductPage productPage);
}
