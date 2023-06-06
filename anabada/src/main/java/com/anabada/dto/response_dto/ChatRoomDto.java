package com.anabada.dto.response_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomDto {
    private Long chatRoomNo;
    private Long memberNo;
    private Long successNo;
    private String memberName;
    private String memberImage;
    private String lastMessage;
    private String lastMessageTime;
    private String productName;
    private Integer bidPrice;

}
