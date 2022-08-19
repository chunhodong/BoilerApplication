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

    /**
     * 재고추가
     * @param stock 재고DTO
     */
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

    /**
     * 재고수량변경
     * @param productStockId 재고아이디
     * @param currentStock 변경할현재수량
     */
    public void modifyCurrentStock(Long productStockId, Long currentStock) {
        ProductStock productStock = productStockRepository.findById(productStockId)
                .orElseThrow(() -> new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT));

        productStock.modifyCurrentStock(currentStock);

    }

    /**
     * 현재재고1추가
     * @param productStockId 재고아이디
     */
    public void plusCurrentStock(Long productStockId) {
        ProductStock productStock = productStockRepository.findById(productStockId)
                .orElseThrow(() -> new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT));
        productStock.plusCurrentStock();
    }

    /**
     * 현재재고1감소
     * @param productStockId 재고아이디
     */
    public void minusCurrentStock(Long productStockId) {
        ProductStock productStock = productStockRepository.findById(productStockId)
                .orElseThrow(() -> new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT));
        productStock.minusCurrentStock();

    }
}
