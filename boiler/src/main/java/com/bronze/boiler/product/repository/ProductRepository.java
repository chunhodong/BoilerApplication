package com.bronze.boiler.product.repository;

import com.bronze.boiler.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> ,ProductRepositoryCst{


}
