package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.utils.RandomGenerator;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private HikariDataSource hikariDataSource;


    private int batchSize = 50000;
    @Test
    void 회원추가() {

        List<Member> members = new ArrayList<>();
        int count = 1;
        for(int i = 0; i < count; i++){
            members.add(RandomGenerator.getMember());

        }
        saveAllJdbcBatch(members);

    }


    public void saveAllJdbcBatch(List<Member> members){
        String sql =
                "INSERT INTO member (id,email,last_login,name,password,period_of_block,role,status,created,modified) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ){
            int counter = 0;
            for (Member member : members) {
                statement.clearParameters();
                statement.setLong(1,6000000);
                statement.setString(2, member.getEmail());
                statement.setTimestamp(3, Timestamp.valueOf(member.getLastLogin()));
                statement.setString(4, member.getName());
                statement.setString(5,member.getPassword());

                statement.setDate(6, null);
                statement.setString(7, member.getRole().name());
                statement.setString(8, member.getStatus().name());
                statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                statement.addBatch();


                if ((counter + 1) % batchSize == 0 || (counter + 1) == members.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
