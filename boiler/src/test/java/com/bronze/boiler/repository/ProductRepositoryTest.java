package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.filter.ProductFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Test
    void 상품필드수정() {
        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .category(category)
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());
        product.modifySellprice(100000L);


    }


    @Test
    void 상품모든필드수정() {
        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .category(category)
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        productRepository.save(Product.builder()
                .id(product.getId())
                .name("상품123")
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

    }


    @Test
    void 상품조회_상품정보확인(){
        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                        .name("상품1")
                        .code("AEOAK001")
                        .category(category)
                        .description("상품설명")
                        .status(ProductStatus.NEW)
                        .originPrice(120000L)
                        .refundInfo("환불정보")
                        .sellerInfo("판매자정보")
                        .sizeInfo("사이즈정보")
                        .sellPrice(100000L)
                        .savePoint(1000L)
                        .sizeInfo("사이즈정보").build());
        Product findproduct = productRepository.findById(product.getId()).get();
        assertThat(findproduct.getCategory().getName()).isEqualTo("카테고리1");
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
                    .status(ProductStatus.NEW)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품26");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
    }

    @Test
    void 상품목록조회_카테고리필터추가_상품정보확인(){

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
                    .status(ProductStatus.NEW)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category1.getId()).build(),getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품22");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category1.getId());
    }

    @Test
    void 상품목록조회_상태값추가_상품정보확인(){

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
                    .status(integer % 2 == 0 ? ProductStatus.NEW : ProductStatus.SELL)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });


        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category2.getId()).status(ProductStatus.SELL).build(),getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(0).getStatus()).isEqualTo(ProductStatus.SELL);
        assertThat(products.get(1).getStatus()).isEqualTo(ProductStatus.SELL);
    }

    @Test
    void 상품목록조회_정렬기준추가_상품정보확인(){

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
                    .status(integer % 2 == 0 ? ProductStatus.NEW : ProductStatus.SELL)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });

        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
    }



    public Pageable getPage(int pageNumber, int pageSize, String sort){
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return (pageNumber - 1) * pageSize;
            }

            @Override
            public Sort getSort() {
                return Sort.by(sort).descending();

            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }




}
