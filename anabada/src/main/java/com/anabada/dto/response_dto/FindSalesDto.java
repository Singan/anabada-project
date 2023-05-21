package com.anabada.dto.response_dto;

import com.anabada.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class FindSalesDto {
    private Long productNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;
    private String productName;
    private Integer productPrice;
    private String productThumbnail;
    private Boolean productIsBidding;

    public FindSalesDto(Product product, String prefix) {
        this.productNo = product.getProductNo();
        this.uploadTime = product.getCreateDateTime();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productThumbnail = prefix + product.getProductThumbnail();
        this.productIsBidding = product.getProductIsBidComplete();
    }
}
