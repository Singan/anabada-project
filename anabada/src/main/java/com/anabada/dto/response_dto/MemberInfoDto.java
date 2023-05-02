package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class MemberInfoDto {
    private Long no;
    private String name;
    private String image;

    public MemberInfoDto(Member member) {
        no = member.getMemberNo();
        name = member.getMemberName();
        image = member.getMemberImage();
    }
}
