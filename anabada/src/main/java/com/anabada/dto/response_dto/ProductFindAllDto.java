package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class ProductFindAllDto {

    private Long productNo;
    private String productName;
    private String wishAddr;
    private Integer price;
    private String productImage;
    public ProductFindAllDto(Product product) {
        this.setProductName(product.getProductName());
        this.setProductNo(product.getProductNo());
        this.setWishAddr(product.getMember().getMemberWishAddr());
        this.setPrice(product.getProductPrice());
        if(!product.getProductImageList().isEmpty()) {
            this.setProductImage(product.getProductImageList().get(0).getImageAddr());
        }
    }

}
