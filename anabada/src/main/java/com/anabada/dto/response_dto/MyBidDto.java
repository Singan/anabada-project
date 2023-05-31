package com.anabada.dto.response_dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
public class MyBidDto {
    private Long bidNo;
    private Integer bidPrice;
    private LocalDateTime bidTime;
    private Long productNo;
    private String productMemberName;
    private LocalDateTime productTime;
    private String productName;
    private Integer productPrice;
    private Boolean productSuccessIs;
    private String productThumbnail;
    private String memberAddr;
    private Long memberNo;

}
