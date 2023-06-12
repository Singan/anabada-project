package com.anabada.dto.response_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MyPageFindDto {

    private String memberName;
    private String memberId;
    private String memberFullAddr;
    private String memberWishAddr;
    private Integer memberMoney;
    private String memberImage;

    public MyPageFindDto (Member member,String prefix) {
        this.memberName = member.getMemberName();
        this.memberId = member.getMemberId();
        this.memberFullAddr = member.getMemberAddr() + " " + member.getMemberDetailAddr();
        this.memberWishAddr = member.getMemberWishAddr();
        this.memberMoney = member.getMemberMoney();
        this.memberImage = prefix+member.getMemberImage();
    }
}
