package com.example.java_project.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    // SpringBoot: Bypass OncePerRequestFilter filters
    // [참고] https://stackoverflow.com/questions/52370411/springboot-bypass-onceperrequestfilter-filters/52370780#52370780

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 리퀘스트에서 토큰 가져오기
            String token = parseBearerToken(request);

            // 토큰 검사
            if (token != null && !token.equalsIgnoreCase("null")) {
                // userIdx 가져오기, 위조 된 경우 예외 처리
                String userIdx = jwtService.validateAndGetUserIdx(token);
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userIdx,
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception e) {
            // 위조가 된 경우
            log.info("invalid jwt");
        }

        filterChain.doFilter(request, response);
    }

    // [참고] https://stackoverflow.com/questions/50284841/how-to-extract-token-string-from-bearer-token
    private String parseBearerToken(HttpServletRequest request) {
        // Http 리퀘스트의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}