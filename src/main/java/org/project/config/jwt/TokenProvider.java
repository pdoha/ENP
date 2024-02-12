package org.project.config.jwt;

import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
@EnableConfigurationProperties(JwtProperties.class) //설정값 가져오기
public class TokenProvider {
    @Autowired
    private JwtProperties jwtProperties; //설정 클래스 가져오기
    private final String secret;
    private final Long tokenValidityInSeconds;

    private Key  key; //secret 해시화

    public TokenProvider(){
        //초기화
        secret = jwtProperties.getSecret();
        tokenValidityInSeconds = jwtProperties.getAccessTokenValidityInSeconds();

        //secret 값을 해시화 해서 key 변수에 할당
        byte[] bytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //토큰 만들기 ( 로그인 할때 필요함 )
    public String createToken(Authentication authentication){
        return null;

    }
}
