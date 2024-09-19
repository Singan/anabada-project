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
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(description = "회원가입")
    public ResponseEntity memberJoin(@Valid MemberJoinDto memberJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> responseError = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError ->
                    responseError.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
        }
        memberService.memberJoin(memberJoinDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResultDto memberLogin(@RequestBody MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto);
    }


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
    public void memberExist(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        memberService.deleteMember(memberDetailDTO);
    }
}
