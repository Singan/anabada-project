package com.anabada.controller;

import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.service.MemberPasswordService;
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
    private final MemberPasswordService memberPasswordService;

    @PostMapping
    @Operation(description = "회원가입")
    public Long memberJoin(@RequestBody @Valid MemberJoinDto memberJoinDto){


        return memberPasswordService.memberJoin(memberJoinDto);
    }

    @PostMapping("/login")
    public void memberLogin(@RequestBody @Valid MemberLoginDto memberLoginDto){



    }
}
