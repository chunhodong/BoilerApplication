package com.bronze.boiler.repository;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.filter.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductRepositoryTest {


    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Test
    void 상품목록조회_상품정보확인(){

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0,31).boxed().forEach(integer -> {
            productRepository.save(Product.builder()
                    .name("상품".concat(String.valueOf(integer)))
                    .code("AEOAK001")
                    .category(integer % 2 == 0 ? category1 : category2)
                    .description("상품설명")
                    .originPrice(120000L)
                    .refundInfo("환불정보")
                    .sellerInfo("판매자정보")
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });
        List<Product> products = productRepository.findAllByPage(Page.builder().pageNum(1L)
                .categoryId(1L).pageSize(5L).build());

        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품8");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(1L);
        assertThat(products.get(1).getCategory().getId()).isEqualTo(1L);





    }



}
