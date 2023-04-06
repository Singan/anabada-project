package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
