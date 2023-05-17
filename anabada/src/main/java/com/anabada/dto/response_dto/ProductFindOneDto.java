package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.nativeQuery.ProductFindOneInterface;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductFindOneDto {

    private Long productNo;
    private String memberName;
    private String memberAddr;
    private String productName;
    private String productDetail;
    private Integer productPrice;
    private String productUseDate;
    private Long productVisit;
    private Long memberNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime productInsertTime;
    private List<String> productImageList;
    private Integer productHighPrice;

    public ProductFindOneDto(ProductFindOneInterface product, String prefix) {
        this.productNo = product.getProductNo();
        this.memberName = product.getMemberName();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.productPrice = product.getProductPrice();
        this.productUseDate = product.getProductUseDate();
        this.productVisit = product.getProductVisit();
        this.memberAddr = product.getMemberAddr();
        this.productInsertTime = product.getProductTime();
        this.memberNo = product.getMemberNo();
        this.productImageList = product.getProductImageList().stream().map(productImage ->  {
            return prefix+productImage;
        }).collect(Collectors.toList());
        productImageList.add(0,prefix+product.getProductThumbnail());
        Integer highPrice = product.getProductHighPrice();
        if(highPrice != null) {
            this.productHighPrice = highPrice;
        }

    }
}
