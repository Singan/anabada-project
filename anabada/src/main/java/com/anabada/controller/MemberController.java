package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.security.token.TokenResultDto;
import com.anabada.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(description = "회원가입")
    public Long memberJoin(@Valid MemberJoinDto memberJoinDto) {
        return memberService.memberJoin(memberJoinDto);
    }

    @PostMapping("/login")
    public TokenResultDto memberLogin(@RequestBody @Valid MemberLoginDto memberLoginDto) {

        return memberService.memberLogin(memberLoginDto);


    }

    @PostMapping("/update")
    public Long memberUpdate(@RequestBody MemberUpdateDto memberUpdateDto) {
        return null;
    }

}
