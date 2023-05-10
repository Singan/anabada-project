package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import com.anabada.entity.ProductSocket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberInfoDto {
    private Long no;
    private String name;
    private String image;
    private List<SocketDto> productSocketList;
    public MemberInfoDto(Member member,String prefix) {
        no = member.getMemberNo();
        name = member.getMemberName();
        image =prefix+ member.getMemberImage();
        productSocketList = member.getMemberProductList().stream()
                .map(product -> new SocketDto(product.getProductSocket()))
                .collect(Collectors.toList());
    }
}
