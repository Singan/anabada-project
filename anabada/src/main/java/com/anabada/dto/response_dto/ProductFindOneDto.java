package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFindOneDto {

    private Long productNo;
    private String memberName;
    private String productName;
    private String productDetail;
    private Integer productPrice;
    private String productUseDate;
    private Long productVisit;

    public ProductFindOneDto(Product product) {
        this.productNo = product.getProductNo();
        this.memberName = product.getMember().getMemberName();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.productPrice = product.getProductPrice();
        this.productUseDate = product.getProductUseDate();
        this.productVisit = product.getProductVisit();
    }
}
