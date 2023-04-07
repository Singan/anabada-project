package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import lombok.*;

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


    public Member getMember(){
        return Member.builder()
                .memberId(this.id)
                .memberPw(this.pw)
                .memberName(this.name)
                .memberBirth(this.birth)
                .memberAddr(this.addr)
                .memberDetailAddr(this.detailAddr)
                .memberWishAddr(this.wishAddr).build();
    }
}