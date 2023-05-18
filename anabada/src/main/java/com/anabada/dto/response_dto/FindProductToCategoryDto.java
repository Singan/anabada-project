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
    private Integer productHighPrice;
    public FindProductToCategoryDto(Product product,String prefix) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productImage = prefix+product.getProductThumbnail();
        this.productNo = product.getProductNo();

    }
}
