package com.anabada.entity.nativeQuery;

import java.time.LocalDateTime;

public interface MyBidHistory {
    Long getBidNo();
    Integer getBidPrice();
    LocalDateTime getBidTime();
    Long getProductNo();
    String getProductMemberName();
    LocalDateTime getProductTime();
    String getProductName();
    Integer getProductPrice();
    Boolean getProductSuccessIs();
    String getProductThumbnail();
    String getMemberAddr();
    Long getMemberNo();
}
