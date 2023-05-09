package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidInsertResponseDto {
    private Long productNo;
    private String memberName;
    private Integer price;
    private Long memberNo;

    public BidInsertResponseDto(Long productNo, String memberName, Integer price, Long memberNo) {
        this.productNo = productNo;
        this.memberName = memberName;
        this.price = price;
        this.memberNo = memberNo;
    }
}
