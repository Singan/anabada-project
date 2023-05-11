package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class MemberUpdateFindDto {
    private String memberId;
    private String memberName;
    private String image;

    public MemberUpdateFindDto(Member member,String prefix) {
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.image = prefix+member.getMemberImage();
    }
}
