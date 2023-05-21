package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidInsertResponseDto {
    private Long productNo;
    private String productName;
    private String memberName;
    private Integer price;
    private Long memberNo;
    private String memberImage;
    private String bidTime;
    @Builder
    public BidInsertResponseDto(Long productNo, String memberName, Integer price, Long memberNo,String memberImage,String bidTime,String productName) {
        this.productNo = productNo;
        this.memberName = memberName;
        this.price = price;
        this.memberNo = memberNo;
        this.memberImage = memberImage;
        this.bidTime = bidTime;
        this.productName = productName;
    }
}
