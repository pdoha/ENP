package org.project.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt") //하위속성에 변수를 담아주다

public class JwtProperties {
    //토큰 설정값 가져오기
    private String header;
    private String secret; //암호값
    private Long accessTokenValidityInSeconds; //토큰 유효시간
}
