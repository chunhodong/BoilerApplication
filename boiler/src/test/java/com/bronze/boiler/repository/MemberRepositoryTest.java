package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.member.domain.Role;
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

    @Nested
    @DisplayName("bulk update시")
    class BulkUpdate{
        @Test
        @DisplayName("jpa를 사용하면 엔티티 수 만큼 update쿼리가 발생한다")
        void 회원목록조회_서브쿼리사용(){

            List<Member> members = memberRepository.findAllById(List.of(1l,2l,3l,4l,5l,6l,7l,8l,9l,10l));
            members.forEach(member -> member.changeToStep());
        }

        @Test
        @DisplayName("querydsl로 사용하면 update쿼리가 한번만 발생한다")
        void 회원목록조회_쿼리나눠서실행사용() {
            memberRepository.updateMemberRoleInBatch(List.of(1l,2l,3l,4l,5l,6l,7l,8l,9l,10l), Role.USER);
        }

    }







}
