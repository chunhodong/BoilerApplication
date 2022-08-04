package com.bronze.boiler.domain.member.entity;

import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.enums.Status;
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
public class Member {

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
    
    @Column
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;


    @Column(name = "period_of_block")
    private LocalDate periodOfBlock;


    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    public void modifyEmail(String email){
        this.email = email;
    }

    public void remove() {
        this.status = Status.REMOVE;
    }

    public void unregister() {
        this.status = Status.UNREGISTER;
    }

    public void block() {
        this.status = Status.BLOCK;
        this.periodOfBlock = LocalDate.now().plusDays(7L);

    }

    public void sleep() {
        this.status = Status.SLEEP;
    }

    public void renewLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}
