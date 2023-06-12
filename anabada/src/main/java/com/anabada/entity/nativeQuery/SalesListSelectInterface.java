package com.anabada.entity.nativeQuery;

import javax.persistence.ColumnResult;
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

    Boolean getProductIsBidComplete();
    String getProductName();
    Long getSuccessNo();
}
