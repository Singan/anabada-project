package com.anabada.dto.response_dto;

import com.anabada.entity.ProductSocket;
import lombok.Getter;

@Getter
public class SocketDto {
    private String socketId;
    public SocketDto(ProductSocket productSocket) {
        this.socketId = "product"+productSocket.getProductSocketNo();
    }
}
