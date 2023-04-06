package com.anabada.controller;

import com.anabada.dto.MemberJoinDto;
import com.anabada.entity.Member;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public Long memberJoin(@RequestBody MemberJoinDto memberJoinDto){

        System.out.println("멤버조인");

        return memberService.memberJoin(memberJoinDto);
    }
}
