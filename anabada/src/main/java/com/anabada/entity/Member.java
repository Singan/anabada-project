package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String memberName;

    private String memberId;
    private String memberPw;


    private LocalDate memberBirth;
    private Integer memberMoney;
    private Float memberTemper;


    private String memberDetailAddr;
    private String memberAddr;
    private String memberWishAddr;
    private String memberAccount;
    @Builder
    public Member(Long memberNo, String memberName,
                  String memberId,
                  String memberPw,
                  LocalDate memberBirth,
                  Integer memberMoney,
                  Float memberTemper,
                  String memberDetailAddr,
                  String memberAddr,
                  String memberWishAddr,
                  String memberAccount) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberBirth = memberBirth;
        this.memberMoney = memberMoney;
        this.memberTemper = memberTemper;
        this.memberDetailAddr = memberDetailAddr;
        this.memberAddr = memberAddr;
        this.memberWishAddr = memberWishAddr;
        this.memberAccount = memberAccount;
    }
}
