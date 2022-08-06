package com.bronze.boiler.repository;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {


    @Autowired
    private  ProductRepository productRepository;

    @Test
    void 상품조회_상품정보확인(){
        productRepository.save(Product.builder()
                        .name("상품1")
                        .code("AEOAK001")
                        .category(Category.builder().name("카테고리1").build())
                        .description("상품설명")
                        .originPrice(120000L)
                        .refundInfo("환불정보")
                        .sellerInfo("판매자정보")
                        .sizeInfo("사이즈정보")
                        .sellPrice(100000L)
                        .savePoint(1000L)
                        .sizeInfo("사이즈정보").build());
        Product product = productRepository.findById(1L).get();
        assertThat(product.getCategory().getName()).isEqualTo("카테고리1");
    }


}
