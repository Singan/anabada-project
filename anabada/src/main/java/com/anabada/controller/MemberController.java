package com.anabada.controller;

import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.security.token.TokenResultDto;
import com.anabada.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(description = "회원가입")
    public Long memberJoin(@RequestBody @Valid MemberJoinDto memberJoinDto) {
        return memberService.memberJoin(memberJoinDto);
    }

    @PostMapping("/login")
    public TokenResultDto memberLogin(@RequestBody @Valid MemberLoginDto memberLoginDto) {
        System.out.println("오나?");
        return memberService.memberLogin(memberLoginDto);


    }
}
