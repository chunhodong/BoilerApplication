package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.filter.Page;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCst{

    @Override
    public List<Product> findAllByPage(Page page) {
        return null;
    }
}
