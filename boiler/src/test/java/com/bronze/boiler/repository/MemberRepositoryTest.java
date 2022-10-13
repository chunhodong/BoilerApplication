package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원조회_회원정보확인() {

        Member member = memberRepository.save(Member.builder()
                .name("박지수")
                .email("test@test.com")
                .password("aoioajf")
                .build());
        Optional<Member> optionalMember = memberRepository.findById(member.getId());
        assertThat(optionalMember.get().getName()).isEqualTo("박지수");
    }


    @Test
    void 회원목록조회_회원목록수확인() {
        memberRepository.save(Member.builder()
                .name("회원0123")
                .email("test@test.com")
                .password("aoioajf")
                .build());
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    void 회원추가_회원정보확인() {
        Member member = memberRepository.save(Member.builder()
                .name("김딴딴")
                .email("test@test.com")
                .password("1234")
                .role(Role.USER).build());

        assertThat(member.getName()).isEqualTo("김딴딴");
        assertThat(member.getEmail()).isEqualTo("test@test.com");
        assertThat(member.getRole()).isEqualTo(Role.USER);
        assertThat(member.getPassword()).isEqualTo("1234");


    }

    @Test
    void 회원삭제_회원목록없음() {
        Member member = memberRepository.save(Member.builder().name("김딴딴").email("test@test.com").password("1234").role(Role.USER).build());

        Member savedMember = memberRepository.save(member);

        memberRepository.deleteById(savedMember.getId());

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(0);

    }

    @Test
    void 회원수정_이전회원정보변경() {


        Member member = memberRepository.save(Member.builder().name("김딴딴").email("test@test.com").password("1234").role(Role.USER).build());

        Member savedMember = memberRepository.save(member);
        savedMember.modifyEmail("test1@test.com");

        assertThat(member.getEmail()).isEqualTo("test1@test.com");


    }


    //---------------------------------------Member--------------------------------------------
    @Test
    void 전체회원수조회(){
        /**
         * -spring-data-jpa의 count는 select count(*) from table 방식
         *  -count(*)는 내부적으로 값을 확인하지 않음
         * -select count(id) from table 방식으로 쿼리한다면
         *  -내부적으로 NULL값을 제외해야되기 떄문에 테이블값을 일일이 확인, 시간이 더 걸릴 수 있음
        */
        long memberCount = memberRepository.count();
        assertThat(memberCount).isEqualTo(6000000);
    }

    @Test
    void 상태별회원수조회(){
        long memberCount = memberRepository.countByStatus(MemberStatus.SLEEP);
        assertThat(memberCount).isEqualTo(1001l);
    }


    @Test
    void 역할별회원수조회(){
        long memberCount = memberRepository.countByRole(Role.USER);
        assertThat(memberCount).isEqualTo(6000000l);
    }

    @Test
    void 이메일중복여부조회(){
        /**
         spring-data-jpa

         - countByEmail로 조회를 하면
            select
                count(member0_.id) as col_0_0_
            from
                member member0_
            where
                member0_.email=?

         - existsByEmail로 조회를 하면
            select
                member0_.id as col_0_0_
            from
                member member0_
            where
                member0_.email=? limit ?

         existsByEmail의 경우 email조건으로 테이블을 조회하다가 limit 조건을 충족시키면 추가검색을 안함
         countByEmail의 경우 email조건으로 테이블을 조회
         */
        boolean memberCount = memberRepository.existsByEmail("bppfcmufsh@naver.com");
        assertThat(memberCount).isEqualTo(true);
    }


    @Test
    void 가입일자별회원정렬(){
        LocalDateTime startDate = LocalDateTime.now().minusDays(100);
        LocalDateTime endDate = LocalDateTime.now();
        List<Member> members = memberRepository.findAllByLastLogin(startDate,endDate,20);
        assertThat(members.size()).isEqualTo(20);
    }






}
