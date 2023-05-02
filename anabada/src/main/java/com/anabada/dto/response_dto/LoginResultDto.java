package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
public class LoginResultDto {

    //private String grantType;
    private String accessToken;
    private String refreshToken;

    private List<String> socketList;
}
