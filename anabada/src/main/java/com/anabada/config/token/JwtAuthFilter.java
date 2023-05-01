package com.anabada.config.token;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {

        String token = request.getHeader("X-AUTH-TOKEN");


            if (jwtTokenProvider.isValidToken(token)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token);
                if (userId != null) {
                    SecurityContextHolder.getContext().setAuthentication(memberService.getAuthentication(userId));
                }


        }

        System.out.println("필터 검증완료");
        chain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getRequestURI().contains("ws")){
            return true;

        }
        return false;
    }


}
