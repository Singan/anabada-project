package com.anabada.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
public class MyBidDto {
    private Long bidNo;
    private Integer bidPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bidTime;
    private Long productNo;
    private String productMemberName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime productTime;
    private String productName;
    private Integer productPrice;
    private Boolean productSuccessIs;
    private String productThumbnail;
    private String memberAddr;
    private Long memberNo;

}
