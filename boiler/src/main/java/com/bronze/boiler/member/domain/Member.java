package com.bronze.boiler.member.domain;

import com.bronze.boiler.common.BaseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @ColumnDefault("'USER'")
    private Role role;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NORMAL'")
    private MemberStatus status;

    @PrePersist
    public void prepareDefault(){
        this.role = this.role == null ? Role.USER : this.role;
        this.status = this.status == null ? MemberStatus.NORMAL : this.status;
    }
}
