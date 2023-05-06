package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductFindOneDto {

    private Long productNo;
    private String memberName;
    private String memberAddr;
    private String productName;
    private String productDetail;
    private Integer productPrice;
    private String productUseDate;
    private Long productVisit;
    private LocalDateTime productInsertTime;
    public ProductFindOneDto(Product product) {
        Member member = product.getMember();
        this.productNo = product.getProductNo();
        this.memberName = member.getMemberName();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.productPrice = product.getProductPrice();
        this.productUseDate = product.getProductUseDate();
        this.productVisit = product.getProductVisit();
        this.memberAddr = member.getMemberAddr();
        this.productInsertTime = product.getCreateDateTime();

    }
}
