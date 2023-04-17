package com.anabada.controller;

import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.security.token.JwtTokenProvider;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MemberService memberService;
    @PostMapping
    public MyPageFindDto myPageFindDto(@RequestHeader("X-AUTH-TOKEN") String header) {
        return memberService.myPage(header);
    }
}
