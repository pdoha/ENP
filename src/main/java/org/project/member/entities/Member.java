package org.project.member.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.commons.contants.MemberType;
import org.project.commons.entities.Base;

@Entity @Data
@Builder
@NoArgsConstructor @AllArgsConstructor //기본생성자추가
public class Member extends Base {
    @Id @GeneratedValue //자동증감
    private Long seq; //회원번호

    @Column(length = 65, unique = true, nullable = false)
    private String email;

    @Column(length = 65, nullable = false)
    private String userPw;

    @Column(length = 40, nullable = false)
    private String userName;

    @Column(length = 11)
    private String mobile; //휴대폰 번호

    @Enumerated(EnumType.STRING) //이넘타입은 상수명
    @Column(length = 15, nullable = false)
    private MemberType type = MemberType.USER; //회원 타입 (기본값)

    //사용요금
    //사용 시간
    //남은 시간
    //private Long seatNo; //좌석번호

}
