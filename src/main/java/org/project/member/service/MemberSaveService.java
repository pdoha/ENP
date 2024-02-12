package org.project.member.service;

import lombok.RequiredArgsConstructor;
import org.project.commons.contants.MemberType;
import org.project.member.controllers.JoinValidator;
import org.project.member.controllers.RequestJoin;
import org.project.member.entities.Member;
import org.project.member.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class MemberSaveService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JoinValidator joinValidator;

    //회원가입
    public void save(RequestJoin form, Errors errors){
        joinValidator.validate(form, errors); //검증하고
        //검증실패시 회원가입 x
        if(errors.hasErrors()){
            //오류가 있는 경우 처리 x
            return;
        }

        //회원가입처리
        String hash = passwordEncoder.encode(form.userPw()); //비밀번호 해시화
        Member member = Member.builder()
                .email(form.email())
                .userName(form.userName())
                .userPw(hash)
                .type(MemberType.USER)
                .mobile(form.mobile())
                .build();

        save(member);

    }
    public void save(Member member){
        String mobile = member.getMobile();
        //휴대폰번호 형식 체크
        if(member != null){
            mobile = mobile.replaceAll("\\D", ""); //숫자만빼놓고 제거
            member.setMobile(mobile);
        }
        repository.saveAndFlush(member);
    }
}
