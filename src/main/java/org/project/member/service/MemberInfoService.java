package org.project.member.service;

import lombok.RequiredArgsConstructor;
import org.project.commons.contants.MemberType;
import org.project.member.entities.Member;
import org.project.member.repositories.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {
    //회원 정보조회
    private final MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //optional 형태로 반화받기 때문에 간단하게 가능
        Member member = repository.findByEmail(username).orElseThrow(()-> new
         UsernameNotFoundException(username));

        //권한
        //member에서 getType 가져오고 , 없을때는 기본적으로 일반사용자로 해주자 ( null값일때 오류발생할수도 있으니
        MemberType type = Objects.requireNonNullElse(member.getType(), MemberType.USER);

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(type.name()));

        return MemberInfo.builder()
                .email(member.getEmail())
                .userPw(member.getUserPw())
                .authorities(authorities)
                .member(member)
                .build();
    }
}
