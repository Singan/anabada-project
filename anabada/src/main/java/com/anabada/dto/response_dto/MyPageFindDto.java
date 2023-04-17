package com.anabada.dto.response_dto;

import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageFindDto {

    private String memberName;
    private Integer memberMoney;
    private String memberImage;
    // 멤버의 계좌번호
//    private String memberAccountNum;

    public MyPageFindDto (Member member) {
        this.memberName = member.getMemberName();
        this.memberMoney = member.getMemberMoney();
        this.memberImage = member.getMemberImage();
    }
}
