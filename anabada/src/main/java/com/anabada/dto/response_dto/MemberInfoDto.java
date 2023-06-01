package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberInfoDto {
    private Long no;
    private String name;
    private String image;
    private List<Long> productSocketNoList;
    public MemberInfoDto(Member member,String prefix) {
        no = member.getMemberNo();
        name = member.getMemberName();
        image =prefix+ member.getMemberImage();
        productSocketNoList = member.getMemberProductList().stream()
                .map(product -> product.getProductNo())
                .collect(Collectors.toList());
    }
}
