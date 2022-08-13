package com.bronze.boiler.repository;

import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.filter.ProductReviewFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductReviewRepositoryTest {


    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void 댓글추가(){
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
    void 대댓글추가(){
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
    void 댓글목록조회(){
        Member member1 = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        Member member2 = memberRepository.save(Member.builder()
                .name("박딴딴")
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


        IntStream.range(0,10).forEach(value -> {
            productReviewRepository.save(ProductReview
                    .builder()
                    .text("댓글" + value)
                    .member(member1)
                    .product(product1)
                    .build());
        });

        List<ProductReview> productReviewList = productReviewRepository.findAllByPage(ProductReviewFilter.builder().build(), getPage(1,10,"id"));
        assertThat(productReviewList.get(0).getId()).isEqualTo(10L);
    }


    public Pageable getPage(int pageNumber,int pageSize,String sort){
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
