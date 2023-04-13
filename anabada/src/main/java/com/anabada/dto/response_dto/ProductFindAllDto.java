package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class ProductFindAllDto {

    private Long productNo;
    private String productName;
    private String memberName;
    private Integer price;

    public ProductFindAllDto(Product product) {
        this.setProductName(product.getProductName());
        this.setProductNo(product.getProductNo());
        this.setMemberName(product.getMember().getMemberName());
        this.setPrice(product.getProductPrice());
    }

}
