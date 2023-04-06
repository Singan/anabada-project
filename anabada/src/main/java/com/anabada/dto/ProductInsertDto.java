package com.anabada.dto;

import com.anabada.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductInsertDto {

    private String name;
    private String usingDate;

    private String detail;
    private Integer price;

    private Long category;

    @Builder
    public ProductInsertDto(String name, String usingDate, String detail, Integer price, Long category) {
        this.name = name;
        this.usingDate = usingDate;
        this.detail = detail;
        this.price = price;
        this.category = category;
    }

    public Product getProduct(){
        Product product = Product.builder()
                .productDetail(detail)
                .productName(name)
                .productUseDate(usingDate)
                .productPrice(price)
                .build();

        return product;
    }
}
