package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BidInfoFindDto {

    private String memberName;
    private Integer price;




    public BidInfoFindDto(Bid bid) {
        this.memberName = bid.getMember().getMemberName();
        this.price = bid.getPrice();
    }
}
