package com.anabada.dto.request_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {

    @NotBlank(message = "ID를 입력하지 않았습니다.")
    protected String id;
    @NotBlank(message = "패스워드를 입력하지 않았습니다.")
    protected String pw;

}
