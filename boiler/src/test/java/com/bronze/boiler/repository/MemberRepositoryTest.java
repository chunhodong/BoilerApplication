package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {



    @Autowired
    private MemberRepository memberRepository;


    @Nested
    @DisplayName("서브쿼리와 비교했을때")
    class ComparisonOfSubqueriesAndJoins{
        @Test
        @DisplayName("서브쿼리로 조회")
        void 회원목록조회_서브쿼리사용(){

            List<Member> reviews = memberRepository.findAllWithSubquery(1000000l);
            assertThat(reviews.size()).isEqualTo(850566);
        }

        @Test
        @DisplayName("두번 나눠서 조회")

        void 회원목록조회_쿼리나눠서실행사용() {
            List<Member> reviews = memberRepository.findAllWithDoubleQuery(1000000l);
            assertThat(reviews.size()).isEqualTo(850566);

        }

    }







}
