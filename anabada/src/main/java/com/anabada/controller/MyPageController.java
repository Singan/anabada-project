package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    // MemberDetailDto에서 값 받아오는걸로 변경하기
//    public MyPageFindDto myPageFindDto(@RequestHeader("X-AUTH-TOKEN") String header) {
//        return memberService.myPage(header);
//    }

    public MyPageFindDto myPageFindDto(@AuthenticationPrincipal MemberDetailDTO principal) {
        return memberService.myPage(principal);
    }
}
