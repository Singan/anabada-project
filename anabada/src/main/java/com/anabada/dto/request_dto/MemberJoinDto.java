package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto extends MemberLoginDto{
    @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1,15}" , message = "이름은 영어,한글,숫자만 입력 가능합니다.")
    private String name;
    private String birth;
    private String detailAddr;
    @NotEmpty(message = "기본 주소지를 입력해주세요")
    private String addr;
    @NotEmpty(message = "거래 희망 주소지를 입력해주세요")
    private String wishAddr;
    private MultipartFile image;
    private String profileImagePath;

    @JsonIgnore
    public Member getMember(){
        return Member.builder()
                .memberId(this.id)
                .memberPw(this.pw)
                .memberName(this.name)
                .memberBirth(this.birth)
                .memberAddr(this.addr)
                .memberDetailAddr(this.detailAddr)
                .memberImage(this.profileImagePath)
                .memberMoney(0)
                .memberExist(false)
                .memberWishAddr(this.wishAddr).build();
    }

}