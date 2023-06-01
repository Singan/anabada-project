package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MemberInfoDto {
    private Long no;
    private String name;
    private String image;
    private List<Long> productSocketNoList;

}
