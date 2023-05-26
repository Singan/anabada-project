package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessBidToastDto {
    private Long productNo;
    private String productName;
    private String message;
    private boolean complete;
}
