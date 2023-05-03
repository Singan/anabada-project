package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MemberUpdateFindDto {
    private String image;

    public MemberUpdateFindDto(Member member) {
        this.image = member.getMemberImage();
    }
}