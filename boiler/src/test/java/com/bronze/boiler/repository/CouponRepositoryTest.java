package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class CouponRepositoryTest {


    @Autowired
    private CouponRepository couponRepository;


    @Nested
    @DisplayName("spring data jpa에서 delete메소드를 사용했을때")
    class Delete {
        @Test
        @DisplayName("deleteAllById는 id에 해당하는 엔티티가 없으면 예외발생")
        void deleteAllById는_id가없으면_예외발생() {

            assertThatThrownBy(() -> couponRepository.deleteAllById(List.of(12314l, 33329l)))
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("deleteAllByIdInBatch는 id에 대한 검증없이 삭제")
        void deleteAllByIdInBatch는_id검증없이_엔티티삭제() {
            couponRepository.deleteAllByIdInBatch(List.of(12314l, 33329l));
        }
    }





}
