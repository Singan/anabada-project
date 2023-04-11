package com.anabada.dto.response_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductFindAllDto {

    private Long productNo;
    private String productName;
    private String memberName;
    private Integer price;

    public ProductFindAllDto(Long no, String productName, String memberName, Integer price) {
        this.productNo = no;
        this.productName = productName;
        this.memberName = memberName;
        this.price = price;
    }
}
