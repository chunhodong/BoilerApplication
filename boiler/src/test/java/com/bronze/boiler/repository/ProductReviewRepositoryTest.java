package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.ProductReviewStatus;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.filter.ProductReviewFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestConfig.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class ProductReviewRepositoryTest {


    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void 댓글추가() {
        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        productReviewRepository.save(ProductReview
                .builder()
                .text("리뷰내용")
                .member(member)
                .product(product)
                .build());
    }


    @Test
    void 대댓글추가() {
        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        ProductReview parentReview = productReviewRepository.save(ProductReview
                .builder()
                .text("댓글")
                .member(member)
                .product(product)
                .build());


        productReviewRepository.save(ProductReview
                .builder()
                .text("대댓글")
                .member(member)
                .product(product)
                .parent(parentReview)
                .build());
    }

    @Test
    void 부모부모댓글목록조회() {
        Member member1 = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Product product1 = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());


        ProductReview productReview = productReviewRepository.save(ProductReview
                .builder()
                .text("댓글")
                .member(member1)
                .product(product1)
                .build());


        IntStream.range(0, 20).forEach(value -> {
            productReviewRepository.save(ProductReview
                    .builder()
                    .text("댓글" + value)
                    .member(member1)
                    .parent(value % 2 == 0 ? null : productReview)
                    .product(product1)
                    .build());


        });
        List<ProductReview> productReviewList = productReviewRepository.findAllByPage(ProductReviewFilter.builder().build(), getPage(1, 10, "id"));
        assertThat(productReviewList.size()).isEqualTo(10L);
    }

    @Test
    void 자식댓글목록조회() {
        Member member1 = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Product product1 = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());


        ProductReview productReview = productReviewRepository.save(ProductReview
                .builder()
                .text("댓글")
                .member(member1)
                .product(product1)
                .build());


        IntStream.range(0, 20).forEach(value -> {
            productReviewRepository.save(ProductReview
                    .builder()
                    .text("댓글" + value)
                    .member(member1)
                    .parent(value % 2 == 0 ? null : productReview)
                    .product(product1)
                    .build());


        });
        List<ProductReview> productReviewList = productReviewRepository.findAllByPage(ProductReviewFilter.builder().parentId(productReview.getId()).build(), getPage(1, 10, "id"));
        assertThat(productReviewList.size()).isEqualTo(10L);
    }

    @Test
    void 자식댓글삭제목록조회() {
        Member member1 = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Product product1 = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .description("상품설명")
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());


        ProductReview productReview = productReviewRepository.save(ProductReview
                .builder()
                .text("댓글")
                .member(member1)
                .product(product1)
                .build());


        IntStream.range(0, 20).forEach(value -> {
            productReviewRepository.save(ProductReview
                    .builder()
                    .text("댓글" + value)
                    .member(member1)
                    .parent(value % 2 == 0 ? null : productReview)
                    .status(value % 2 == 0 ? ProductReviewStatus.REMOVED : ProductReviewStatus.REMOVED)
                    .product(product1)
                    .build());


        });
        List<ProductReview> productReviewList = productReviewRepository
                .findAllByPage(ProductReviewFilter
                        .builder()
                        .status(ProductReviewStatus.REMOVED)
                        .parentId(productReview.getId()).build(), getPage(1, 10, "id"));
        assertThat(productReviewList.size()).isEqualTo(10L);
    }


    public Pageable getPage(int pageNumber, int pageSize, String sort) {
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


    //------------------------------------------ProductReview------------------------------------------
    @Test
    void 리뷰목록조회(){

        List<ProductReview> reviews = productReviewRepository.findAllById(List.of(1l,2l));
        System.out.println("review="+reviews);
        reviews.forEach(productReview -> System.out.println(productReview.getProduct().getName()));
    }
}
