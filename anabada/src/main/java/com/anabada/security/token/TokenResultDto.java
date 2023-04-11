package com.anabada.security.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenResultDto {

    //private String grantType;
    private String accessToken;
    private String refreshToken;
}