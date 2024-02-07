package org.project.member.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.commons.contants.MemberType;
import org.project.commons.entities.Base;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Base {
    @Id
    @GeneratedValue
    private Long seq;
    @Column(length=60, unique = true, nullable = false)
    private String email;

    @Column(length=65, nullable = false)
    private String password;

    @Column(length=30, nullable = false)
    private String name;

    @Column(length=11)
    private String mobile; //휴대폰 번호

    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable = false)
    private MemberType type = MemberType.USER;

    //좌석번호
    //사용요금
    //사용시간
    //남은시간
}
