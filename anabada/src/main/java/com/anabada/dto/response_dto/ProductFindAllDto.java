package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.*;

@Getter
@NoArgsConstructor

public class ProductFindAllDto {

    private Long productNo;
    private String productName;
    private String wishAddr;
    private Integer price;
    private String productImage;
    public ProductFindAllDto(Product product) {
        this.productName = (product.getProductName());
        this.productNo = (product.getProductNo());
        this.wishAddr = (product.getMember().getMemberWishAddr());
        this.price = (product.getProductPrice());
        if(!product.getProductImageList().isEmpty()) {
            this.productImage = (product.getProductImageList().get(0).getImageAddr());
        }
    }

}
