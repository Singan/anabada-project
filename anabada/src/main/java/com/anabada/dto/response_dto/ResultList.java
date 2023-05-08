package com.anabada.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultList <T>{
    private T list;
}
