package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MypageConfirmDto;
import com.anabada.dto.request_dto.PayDto;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.service.MemberService;
import com.anabada.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MemberService memberService;
    private final PayService payService;

    @GetMapping
    public MyPageFindDto myPageFindDto(@AuthenticationPrincipal MemberDetailDTO principal) {
        return memberService.myPage(principal);
    }

    @PostMapping("/pay")
    public void pay(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO, @RequestBody PayDto payDto) {
        payService.addMoney(memberDetailDTO, payDto);
    }

    @PostMapping("/confirm")
    public boolean mypageConfirm(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO,@RequestBody MypageConfirmDto mypageConfirmDto) {
        System.out.println("마이페이지 컨펌 들어옴");
        System.out.println(mypageConfirmDto.getConfirmPassword());
        System.out.println("------------------------------------");
        return memberService.confirmPassword(memberDetailDTO, mypageConfirmDto);
    }


}
