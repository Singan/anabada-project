package com.anabada.dto.response_dto;

import com.anabada.dto.request_dto.ChatMessageDto;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;
    private Long successNo;

    public ChatMessageResDto(ChatMessageDto chatMessageDto, LocalDateTime now,Long memberNo) {
        this.memberName = chatMessageDto.getMemberName();
        this.memberImage = chatMessageDto.getMemberImage();
        this.memberNo = memberNo;
        this.message = chatMessageDto.getMessage();
        this.createDateTime = now;
        this.successNo = chatMessageDto.getSuccessNo();
    }
}
