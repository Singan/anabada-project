package com.anabada.security.token;

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {
        System.out.println("여기와?");
        // 1. 토큰이 필요하지 않은 API URL에 대해서 배열로 구성합니다.

        String token = request.getHeader("X-AUTH-TOKEN");

        if (token != null && !token.isBlank()) {
            if (jwtTokenProvider.isValidToken(token)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token);
                logger.debug("[+] userId Check: " + userId);
                if (userId != null) {
                    SecurityContextHolder.getContext().setAuthentication(getAuthentication(userId));
                }

            }
        }
        chain.doFilter(request, response);

    }

    public Authentication getAuthentication(String id) {
        MemberDetailDTO userDetails = memberService.loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
