package com.anabada.dto.response_dto;

import com.anabada.entity.Category;
import com.anabada.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor

public class ProductFindAllDto {

    private Long productNo;
    private String productName;
    private String wishAddr;
    private Integer price;
    private String productImage;


    // 카테고리 넣기
    public ProductFindAllDto(Product product,String prefix) {
        this.productName = (product.getProductName());
        this.productNo = (product.getProductNo());
        this.wishAddr = (product.getMember().getMemberWishAddr());
        this.price = (product.getProductPrice());
        this.productImage = prefix + product.getProductThumbnail();
    }

}
