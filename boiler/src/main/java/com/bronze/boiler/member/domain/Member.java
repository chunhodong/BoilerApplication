package com.bronze.boiler.member.domain;

import com.bronze.boiler.domain.base.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 회원
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseDate {



    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column
    @NotBlank(message = "이름을 입력해야합니다")
    private String name;

    @Column
    @NotBlank(message = "이메일을 입력해야합니다")
    private String email;

    @Column
    @NotBlank(message = "패스워드를 입력해야합니다")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private MemberStatus status;


    @Column(name = "period_of_block")
    private LocalDate periodOfBlock;


    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    public void modifyEmail(String email){
        this.email = email;
    }

    public void remove() {
        this.status = MemberStatus.REMOVE;
    }

    public void unregister() {
        this.status = MemberStatus.UNREGISTER;
    }

    public void block() {
        this.status = MemberStatus.BLOCK;
        this.periodOfBlock = LocalDate.now().plusDays(7L);
    }

    public void changeToStep(){
        this.role = Role.STEP;
    }

    public void sleep() {
        this.status = MemberStatus.SLEEP;
    }

    public void renewLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}
