package com.anabada.entity.nativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface MyBuyListInterface {
    Long getBidNo();

    Long getProductNo();

    LocalDateTime getSuccessTime();

    String getProductName();

    Integer getBidPrice();

    String getProductThumbnail();

    Long getSuccessBidNo();

    LocalDateTime getProductTime();
    String getProductMemberName();
    Long getProductMemberNo();


}
