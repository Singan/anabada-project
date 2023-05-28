package com.anabada.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MyBuyDto {
    private Long productNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime successTime;
    private String productName;
    private Integer bidPrice;
    private String productThumbnail;
    private String productSellerName;
    private Long successBidNo;

}
