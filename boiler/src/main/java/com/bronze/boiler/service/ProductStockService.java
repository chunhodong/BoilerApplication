package com.bronze.boiler.service;


import com.bronze.boiler.domain.product.dto.ProductStockDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductStock;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.domain.product.enums.ProductStockExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.exception.ProductStockException;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockService {
    private final ProductStockRepository productStockRepository;
    private final ProductRepository productRepository;
    public void createProductStock(ProductStockDto stock) {


        Long productId = stock.getProductId();
        Long totalStock = stock.getTotalStock();
        Long currentStock = stock.getCurrentStock();


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));

        if(totalStock < currentStock){
            throw new ProductStockException(ProductStockExceptionType.ILLEGAL_STOCK);
        }

        productStockRepository.save(
                ProductStock.builder()
                        .product(product)
                        .totalStock(totalStock)
                        .currentStock(currentStock)
                        .build());

    }

    public void modifyCurrentStock(Long productStockId, Long currentStock) {
        ProductStock productStock = productStockRepository.findById(productStockId)
                .orElseThrow(() -> new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT));

        productStock.modifyCurrentStock(currentStock);

    }
}
