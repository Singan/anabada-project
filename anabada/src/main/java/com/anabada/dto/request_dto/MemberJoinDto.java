package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto extends MemberLoginDto{
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birth;
    private String detailAddr;
    @NotEmpty(message = "기본 주소지를 입력해주세요")
    private String addr;
    private String wishAddr;
    private MultipartFile image;
    private String profileImagePath;
    public Member getMember(){
        return Member.builder()
                .memberId(this.id)
                .memberPw(this.pw)
                .memberName(this.name)
                .memberBirth(this.birth)
                .memberAddr(this.addr)
                .memberDetailAddr(this.detailAddr)
                .memberImage(this.profileImagePath)
                .memberExist(false)
                .memberWishAddr(this.wishAddr).build();
    }

}