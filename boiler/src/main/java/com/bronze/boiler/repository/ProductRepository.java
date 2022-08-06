package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}
