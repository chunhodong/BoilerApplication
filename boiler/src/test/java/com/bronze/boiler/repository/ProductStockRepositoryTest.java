package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductStock;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class ProductStockRepositoryTest {


    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 재고수량추가(){

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductStock productStock = productStockRepository.save(ProductStock.builder()
                .product(product)
                .currentStock(100l)
                .totalStock(100l)
                .build());

        assertThat(productStock.getTotalStock()).isEqualTo(100l);

    }

    @Test
    void 남은재고가0보다큰경우_TRUE반환(){

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductStock productStock = productStockRepository.save(ProductStock.builder()
                .product(product)
                .currentStock(100l)
                .totalStock(100l)
                .build());

        assertThat(productStock.isRemainCurrentStock()).isEqualTo(true);

    }

    @Test
    void 남은재고가0보다작거나같은경우_FALSE반환(){

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductStock productStock = productStockRepository.save(ProductStock.builder()
                .product(product)
                .currentStock(0L)
                .totalStock(100l)
                .build());

        assertThat(productStock.isRemainCurrentStock()).isEqualTo(false);

    }

    @Test
    void 남은재고1개감소_재고확인(){

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductStock productStock = productStockRepository.save(ProductStock.builder()
                .product(product)
                .currentStock(12L)
                .totalStock(100l)
                .build());
        productStock.minusCurrentStock();

        assertThat(productStock.getCurrentStock()).isEqualTo(11l);

    }

    @Test
    void 남은재고1개증가_재고확인(){

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductStock productStock = productStockRepository.save(ProductStock.builder()
                .product(product)
                .currentStock(12L)
                .totalStock(100l)
                .build());
        productStock.plusCurrentStock();

        assertThat(productStock.getCurrentStock()).isEqualTo(13l);

    }





}
