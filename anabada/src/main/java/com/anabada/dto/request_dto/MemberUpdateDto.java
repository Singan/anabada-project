package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {
    private String memberName;
    private String memberPw;
    private String addr;
    private String detailAddr;
    private String wishTradeAddr;
    private MultipartFile image;

    public Member updateMember(String profilePath) {
        return Member.builder()
                .memberName(memberName)
                .memberPw(memberPw)
                .memberAddr(addr)
                .memberDetailAddr(detailAddr)
                .memberWishAddr(wishTradeAddr)
                .memberImage(profilePath)
                .build();
    }

}
