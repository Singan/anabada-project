package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.dto.response_dto.LoginResultDto;
import com.anabada.dto.response_dto.ShowUpdateMemberDto;
import com.anabada.dto.response_dto.MemberInfoDto;
import com.anabada.dto.response_dto.MemberUpdateFindDto;
import com.anabada.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(description = "회원가입")
    public void memberJoin(@Valid MemberJoinDto memberJoinDto) {
        memberService.memberJoin(memberJoinDto);
    }

    @PostMapping("/login")
    public LoginResultDto memberLogin(@RequestBody  MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto);
    }

//    @GetMapping("/socket")
//    public List<Long> memberSocketList(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
//        return socketService.memberSocketList(memberDetailDTO.getMember());
//    }

    @PutMapping("/update")
    @Operation(description = "회원 수정")
    public MemberUpdateFindDto memberUpdate(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO,
                                            MemberUpdateDto memberUpdateDto) {
        return memberService.memberUpdate(memberDetailDTO, memberUpdateDto);
    }

    @GetMapping
    public MemberInfoDto memberInfo(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        MemberInfoDto member = memberService.findByMemberNoWithSocketList(memberDetailDTO.getNo());
        return member;
    }

    @GetMapping("/idNameImage")
    public ShowUpdateMemberDto memberIdNameImageDto(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return memberService.showMemberInfo(memberDetailDTO);
    }
    @DeleteMapping("/exist")
    public void memberExist(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO){
        memberService.deleteMember(memberDetailDTO);
    }
}
