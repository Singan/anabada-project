package com.anabada.entity.nativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductFindOneInterface {
    Long getProductNo();
    String getProductName();
    String getProductDetail();
    Integer getProductPrice();
    Long getProductVisit();
    LocalDateTime getProductTime();
    String getProductUseDate();
    String getProductThumbnail();
    String getMemberName();
    String getMemberAddr();

    Long getMemberNo();


    List<String> getProductImageList();
     Integer getProductHighPrice();
     //LocalDateTime getProductLeftTime();
}
