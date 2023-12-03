package com.anabada.config.token;

import com.anabada.entity.Member;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {

        String token = request.getHeader("X-AUTH-TOKEN");

            if (jwtTokenProvider.isValidToken(token)) {
                Member member = jwtTokenProvider.getTokenConverterMember(token);
                if (member != null) {
                    SecurityContextHolder.getContext().setAuthentication(memberService.getAuthentication(member));
                }


        }

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
