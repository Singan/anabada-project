package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto extends MemberLoginDto{
    private String name;
    private LocalDate birth;
    private String detailAddr;
//    @NotNull(message = "기본주소지를 입력해주세요")
    private String addr;
    private String wishAddr;
    private MultipartFile image;

    public Member getMember(String profilePath){
        return Member.builder()
                .memberId(this.id)
                .memberPw(this.pw)
                .memberName(this.name)
                .memberBirth(this.birth)
                .memberAddr(this.addr)
                .memberDetailAddr(this.detailAddr)
                .memberImage(profilePath)
                .memberWishAddr(this.wishAddr).build();
    }

}