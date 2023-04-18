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
    private Integer memberMoney;
    private String memberImage;
//    만약 생일까지 뽑고 싶다면
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate memberBirth;

//    멤버 계좌번호
//    private String memberAccountNum;

    public MyPageFindDto (MemberDetailDTO member) {
        this.memberName = member.getMemberName();
        this.memberMoney = member.getMemberMoney();
        this.memberImage = member.getMemberImage();
//        this.memberBirth = member.getMemberBirth();
    }
}
