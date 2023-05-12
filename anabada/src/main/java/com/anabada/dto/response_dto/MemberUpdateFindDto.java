package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Getter;

@Getter
public class MemberUpdateFindDto {
    private String image;

    public MemberUpdateFindDto(Member member,String prefix) {
        this.image = prefix+member.getMemberImage();
    }
}
