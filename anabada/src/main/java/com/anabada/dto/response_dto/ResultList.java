package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultList <T,T2>{
    private T length;
    private T2 list;
}
