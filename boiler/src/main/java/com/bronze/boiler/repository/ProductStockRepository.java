package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock,Long> {


}
