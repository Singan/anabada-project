package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Getter;

@Getter
public class ShowUpdateMemberDto {
    private String memberId;
    private String memberName;
    private String memberImage;
    private String memberAddr;
    private String memberDetailAddr;
    private String memberWishAddr;

    public ShowUpdateMemberDto(Member member,String s3) {
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.memberImage = s3+member.getMemberImage();
        this.memberAddr = member.getMemberAddr();
        this.memberDetailAddr = member.getMemberDetailAddr();
        this.memberWishAddr = member.getMemberWishAddr();
    }
}
