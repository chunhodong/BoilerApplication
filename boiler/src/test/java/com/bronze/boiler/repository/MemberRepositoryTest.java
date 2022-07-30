package com.bronze.boiler.repository;

import com.bronze.boiler.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원조회(){
        memberRepository.save(Member.builder().build());
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    public void 회원추가(){
        Member member = memberRepository.save(Member.builder().name("회원1").build());
        assertThat(member.getName()).isEqualTo("회원1");

    }

}
