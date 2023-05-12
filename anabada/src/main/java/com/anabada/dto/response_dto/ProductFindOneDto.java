package com.anabada.dto.response_dto;

import com.anabada.entity.CurrentBid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime productInsertTime;
    private List<String> productImageList;
    private Integer productHighPrice;
    private LocalDateTime productLeftTime;

    public ProductFindOneDto(Product product,String prefix) {
        Member member = product.getMember();
        this.productNo = product.getProductNo();
        this.memberName = member.getMemberName();
        this.productName = product.getProductName();
        this.productDetail = product.getProductDetail();
        this.productPrice = product.getProductPrice();
        this.productUseDate = product.getProductUseDate();
        this.productVisit = product.getProductVisit();
        this.memberAddr = member.getMemberAddr();
        this.productInsertTime = product.getCreateDateTime();

        this.productImageList = product.getProductImageList().stream().map(productImage ->  {
            return prefix+productImage.getImageAddr();
        }).collect(Collectors.toList());
        productImageList.add(0,prefix+product.getProductThumbnail());
        Collections.reverse(productImageList);
        CurrentBid CurrentBid = product.getCurrentBid();
        if(CurrentBid != null) {
            this.productHighPrice = product.getCurrentBid().getPrice();
            this.productLeftTime = product.getCurrentBid().getLocalDateTime();
        }

    }
}
