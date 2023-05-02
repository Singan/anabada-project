package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.dto.response_dto.LoginResultDto;
import com.anabada.dto.response_dto.MemberInfoDto;
import com.anabada.service.MemberService;
import com.anabada.service.SocketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final SocketService socketService;

    @PostMapping
    @Operation(description = "회원가입")
    public Long memberJoin(@Valid MemberJoinDto memberJoinDto) {
        return memberService.memberJoin(memberJoinDto);
    }

    @PostMapping("/login")
    public LoginResultDto memberLogin(@RequestBody @Valid MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto);
    }

    @GetMapping("/socket")
    public List<String> memberSocketList(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return socketService.memberSocketList(memberDetailDTO.getMember());
    }

    @PostMapping("/update")
    @Operation(description = "회원 수정")
    public Long memberUpdate(MemberUpdateDto memberUpdateDto) {
        return memberService.memberUpdate(memberUpdateDto);
    }

    @GetMapping
    public MemberInfoDto memberInfo(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return new MemberInfoDto(memberService.findByMemberId(memberDetailDTO.getUsername()));
    }

}
