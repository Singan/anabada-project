package com.anabada.dto.request_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatReqMessageDto {
    private String message;
    private Long successNo;
    private String memberName;
    private String memberImage;
}
