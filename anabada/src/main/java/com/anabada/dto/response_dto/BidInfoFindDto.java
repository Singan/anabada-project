package com.anabada.dto.response_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BidInfoFindDto {

    private String member;
    private Integer price;

    public BidInfoFindDto(String member, Integer price) {
        this.member = member;
        this.price = price;
    }
}
