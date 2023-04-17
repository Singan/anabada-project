package com.anabada.controller;

import com.anabada.dto.response_dto.MyPageFindDto;
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
    public MyPageFindDto myPageFindDto(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        return memberService.myPage(token);
    }
}
