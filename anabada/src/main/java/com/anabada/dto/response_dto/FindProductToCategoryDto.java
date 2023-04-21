package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindProductToCategoryDto {

    private String productName;
    private Integer productPrice;
    private String productImage;

    public FindProductToCategoryDto(Product product) {
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
        this.setProductImage(product.getProductImageList().get(0).getImageAddr());
    }
}
