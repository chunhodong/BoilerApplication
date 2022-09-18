package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.utils.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
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
    void 회원추가() {

        int count = 2;
        for(int i = 0; i < count; i++){
            memberRepository.save(RandomGenerator.getMember());
        }
    }

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

}
