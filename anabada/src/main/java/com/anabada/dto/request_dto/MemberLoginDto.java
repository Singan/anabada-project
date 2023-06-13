package com.anabada.dto.request_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}" , message = "아이디는 4글자 이상의 대소문자 영어,숫자만 입력 가능합니다.")
    protected String id;
    @Pattern(regexp="(?=.*[a-z])(?=.*[0-9])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    protected String pw;

}
