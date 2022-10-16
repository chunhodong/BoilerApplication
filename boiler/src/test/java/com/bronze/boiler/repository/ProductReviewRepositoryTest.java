package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.product.entity.ProductReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class ProductReviewRepositoryTest {


    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Test
    void 리뷰목록조회_offset_limit만_사용() {
        List<ProductReview> reviews = productReviewRepository.findAllWithOffset(10000000l,20l);
        assertThat(reviews.size()).isEqualTo(20);
    }

    @Test
    void 리뷰목록조회_nooffset_사용() {
        List<ProductReview> reviews = productReviewRepository.findAllWithNoOffset(29000000l,20l);
        assertThat(reviews.size()).isEqualTo(20);
    }



}