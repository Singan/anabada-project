package com.anabada.entity.nativeQuery;

import java.time.LocalDateTime;

public interface SalesListSelectInterface {
     Integer getBidPrice();
     String getProductThumbnail();
     Long getProductNo();
     LocalDateTime getCreateDateTime();
    LocalDateTime getBidTime();
     String getMemberName();
     Long getMemberNo();
     Integer getProductPrice();
}
