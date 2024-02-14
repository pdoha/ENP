package org.project.config;

import jakarta.servlet.http.HttpServletResponse;
import org.project.config.jwt.CustomJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity //기본 웹 보완 활성화
@EnableMethodSecurity //@PreAuthorize("hasAuthority('ADMIN')") 요청메서드 사이에 권한 부여 활성화
public class SecurityConfig {
    @Autowired
    private CustomJwtFilter customJwtFilter;
    @Autowired
    private CorsFilter corsFilter;

    //스프링 시큐리티 비활성화
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(c -> c.disable())
                //사용자 로그인을 처리하는 필터 전에 설정클래스와 토큰을 가지고 로그인 유지하는 필터를 실행시킨다
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                //세션 비활성화
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //인증 &인가 실패시 (특정 페이지 인가) -> 로그인페이지로 이동이나 응답코드형태로 보여줌 (401)
        http.exceptionHandling(c -> {
            c.authenticationEntryPoint((req, res, e) -> {
                //응답코드 401
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED); //401
            });

            //상세하게 설정 ( 페이지 이동이 아니라 응답코드만 나오게 세팅)
            c.accessDeniedHandler((req, res, e) -> {
                res.sendError(HttpServletResponse.SC_FORBIDDEN); //403
            });
        });

        http.authorizeHttpRequests(c -> {
            //전체 접근 가능한 페이지 & 로그인시 접근 가능한 페이지
            c.requestMatchers("/api/v1/member", // 회원가입
                    "/api/v1/member/token").permitAll()
                    .anyRequest().authenticated();
        });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
