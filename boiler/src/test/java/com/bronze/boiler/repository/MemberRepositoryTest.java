package com.bronze.boiler.repository;

import com.bronze.boiler.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원조회(){
        memberRepository.save(Member.builder().name("회원0123").build());
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    public void 회원추가(){
        Member member = memberRepository.save(Member.builder().id(123L).name("222").build());
        assertThat(member.getId()).isEqualTo(2L);

        assertThat(member.getName()).isEqualTo("222");

    }

}
