package org.project.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomJwtFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //ServletRequest라서 haeader가 없으므로 형변환해서 사용해야한다
        HttpServletRequest req = (HttpServletRequest) request;
        //요청 헤더 Authorization 항목의 JWT 토큰 추출
        String header = req.getHeader("Authorization");
        String jwt = null;

        //헤더가 있으면
        if(StringUtils.hasText(header)){
            //Bearer . . . 명칭 제거하고 7번째부터 추출하면 토큰만 가져옴
            jwt = header.substring(7);
        }

        //로그인 유지 처리
        if(StringUtils.hasText(jwt)){
            //검증해보고 실패하면 예외발생 -> json형태로 응답
            tokenProvider.validateToken(jwt);

            //통과되면
            Authentication authentication = tokenProvider.getAuthentication(jwt); //회원정보 가져오고
            SecurityContextHolder.getContext().setAuthentication(authentication); //저장

        }

        chain.doFilter(request, response);
    }
}
