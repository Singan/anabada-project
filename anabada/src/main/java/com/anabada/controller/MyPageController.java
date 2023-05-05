package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MemberService memberService;

    @GetMapping
    public MyPageFindDto myPageFindDto(@AuthenticationPrincipal MemberDetailDTO principal) {
        return memberService.myPage(principal);
    }

    
}
