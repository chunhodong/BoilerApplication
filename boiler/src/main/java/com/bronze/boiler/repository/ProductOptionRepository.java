package com.bronze.boiler.repository;

import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption,Long> {


    @EntityGraph(attributePaths = {"product"})
    List<ProductOption> findAllByProduct(Product product);
}
