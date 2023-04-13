package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessBidDto {

    private String successBidName;
    private Integer successBidPrice;
    private String memberName;

    public SuccessBidDto successBidDto(Bid bid) {
        this.setSuccessBidName(bid.getProduct().getProductName());
    }
}
