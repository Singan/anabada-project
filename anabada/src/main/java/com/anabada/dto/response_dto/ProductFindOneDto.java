package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    public ProductFindOneDto(Long productNo, String memberName,
                             String productName, String productDetail,
                             Integer productPrice, String productUseDate ) {
        this.productNo = productNo;
        this.memberName = memberName;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productUseDate = productUseDate;

    }
    public ProductFindOneDto(Product product) {
        this.productNo = product.getProductNo();
        this.memberName = product.getMember().getMemberName();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.productPrice = product.getProductPrice();
        this.productUseDate = product.getProductUseDate();

    }
}
