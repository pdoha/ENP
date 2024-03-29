package org.project.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing //이벤트 감지
public class MvcConfig implements WebMvcConfigurer {


    //메세지 공통으로 쓰기 위해 설정에 추가
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasenames("messages.commons", "messages.validations", "messages.errors");

        return ms;
    }



    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //설정클래스
        CorsConfiguration config = new CorsConfiguration();
        //출처 설정
        config.addAllowedOrigin("*"); //도메인
        config.addAllowedHeader("*"); //응답헤더
        config.addAllowedMethod("*"); //요청방식

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);

    }
}
