package com.anabada.service;

import com.anabada.dto.MemberJoinDto;
import com.anabada.dto.ProductInsertDto;
import com.anabada.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberPasswordService memberPasswordService;
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final String pw = "테스트비밀번호12345";
    private final String id = "테스트계정아이디";
    private final String name = "테스트이름";

    @Test
    void findByUserId() {
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setId(id);
        memberJoinDto.setPw(pw);
        memberJoinDto.setName(name);
        memberPasswordService.memberJoin(memberJoinDto);
        Member member = memberService.findByUserId(id).get();

        assertEquals(id,member.getMemberId());
        assertTrue(passwordEncoder.matches(pw,member.getMemberPw()));
        assertEquals(name,member.getMemberName());
    }

}