package org.project.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.project.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


public class TokenProvider {

    private final String secret;
    private final Long tokenValidityInSeconds;
    @Autowired
    private MemberInfoService infoService;

   private Key key; //secret 해시화

    public TokenProvider(String secret, Long tokenValidityInSeconds){
        //초기화
        this.secret = secret;
        this.tokenValidityInSeconds = tokenValidityInSeconds;

        //secret 값을 해시화 해서 key 변수에 할당
        byte[] bytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //토큰 만들기 ( 로그인 할때 필요함 )
    public String createToken(Authentication authentication){

        //권한
        String authories = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); //collect로 모아주고

        //토큰 만료시간 setExpiration의 data객체 만들기
        Date expires = new Date((new Date()).getTime() + tokenValidityInSeconds * 1000); //1시간뒤 만료시간
        return Jwts.builder()
                .setSubject(authentication.getName()) //아이디
                .claim("auth", authories) //권한 (키와 값 형태)
                .signWith(key, SignatureAlgorithm.ES512) // HMAC + SHA512
                .setExpiration(expires) //토큰유효시간
                .compact();

    }

    //회원정보 담겨 있는 객체 Authentication이용해서 회원정보 가져오기
    public Authentication getAuthentication(String token){
        //토큰을 가지고 정보가 담겨 있는
        //토큰을 가지고 claim 분해해서
                Claims claims = Jwts.parser()
                        .setSigningKey(key) //parser하기 전에 항목 검증
                        .build() //빌더로 감싸주고
                        .parseClaimsJws(token) //토큰을 가지고 회원 아이디
                        .getPayload(); //claims 객체 생성

        //이메일 가지고 회원정보 조회
        String email = claims.getSubject();
        UserDetails userDetails = infoService.loadUserByUsername(email);
        //통합해주는 클래스
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, token, authories);

        return authentication;
    }
}
