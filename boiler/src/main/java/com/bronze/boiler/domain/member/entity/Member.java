package com.bronze.boiler.domain.member.entity;

import com.bronze.boiler.domain.member.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 회원엔티티
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


    public void modifyEmail(String email){
        this.email = email;
    }

}
