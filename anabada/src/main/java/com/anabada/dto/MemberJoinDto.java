package com.anabada.dto;

import com.anabada.entity.Member;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {
    private String name;

    private String id;
    private String pw;


    private LocalDate birth;


    private String detailAddr;
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
