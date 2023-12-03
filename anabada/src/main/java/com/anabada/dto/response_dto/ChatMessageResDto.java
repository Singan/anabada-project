package com.anabada.dto.response_dto;

import com.anabada.dto.request_dto.ChatReqMessageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class ChatMessageResDto {
    private String memberName;
    private String memberImage;
    private Long memberNo;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDateTime;
    private Long successNo;

    public ChatMessageResDto(ChatReqMessageDto chatReqMessageDto, LocalDateTime now, Long memberNo) {
        this.memberName = chatReqMessageDto.getMemberName();
        this.memberImage = chatReqMessageDto.getMemberImage();
        this.memberNo = memberNo;
        this.message = chatReqMessageDto.getMessage();
        this.createDateTime = now;
        this.successNo = chatReqMessageDto.getSuccessNo();
    }
}
