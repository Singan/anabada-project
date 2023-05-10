package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class FindProductToCategoryDto {

    private String productName;
    private Integer productPrice;
    private String productImage;
    private Long productNo;
    public FindProductToCategoryDto(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productImage = product.getProductImageList().get(0).getImageAddr();
        this.productNo = product.getProductNo();
    }
}
