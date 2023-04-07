package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import com.anabada.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
public class ProductFindOneDto {

    private Long productNo;
    private String memberName;
    private String productName;
    private String productDetail;
    private Integer productPrice;
    private String productUseDate;

    public ProductFindOneDto(Long productNo, String memberName, String productName, String productDetail, Integer productPrice, String productUseDate) {
        this.productNo = productNo;
        this.memberName = memberName;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productUseDate = productUseDate;
    }
}
