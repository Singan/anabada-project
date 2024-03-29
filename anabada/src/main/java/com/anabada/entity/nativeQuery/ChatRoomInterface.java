package com.anabada.entity.nativeQuery;

import java.time.LocalDateTime;

public interface ChatRoomInterface {
    Long getChatRoomNo();

    Long getMemberNo();

    Long getSuccessNo();

    String getMemberName();

    String getMemberImage();

    LocalDateTime getLastMessageTime();

    String getLastMessage();

    Integer getBidPrice();

    String getProductName();
    String getMemberWishAddr();
    String getProductThumbnail();
}
