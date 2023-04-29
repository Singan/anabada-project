package com.anabada.service;

import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.entity.Member;
import com.anabada.config.token.JwtTokenProvider;
import com.anabada.config.token.TokenResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private final String pw = "테스트비밀번호12345";
    private final String id = "테스트계정아이디";
    private final String name = "테스트이름";

    @Test
    void findByUserId() {
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setId(id);
        memberJoinDto.setPw(pw);
        memberJoinDto.setName(name);
        memberService.memberJoin(memberJoinDto);
        Member member = memberService.findByMemberId(id);

        assertEquals(id,member.getMemberId());
        assertTrue(passwordEncoder.matches(pw,member.getMemberPw()));
        assertEquals(name,member.getMemberName());
    }
    @Test
    void memberJoin(){

        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setId(id);
        memberJoinDto.setPw(pw);

        memberService.memberJoin(memberJoinDto);
    }
    @Test
    void memberLogin() {
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setId(id);
        memberLoginDto.setPw(pw);
        TokenResultDto t = memberService.memberLogin(memberLoginDto);


        System.out.println(jwtTokenProvider.isValidToken(t.getAccessToken()));
    }
}