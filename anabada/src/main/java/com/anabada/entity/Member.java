package com.anabada.entity;

import com.anabada.dto.request_dto.MemberUpdateDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(unique = true)
    private String memberId;
    private String memberPw;
    private String memberName;

    private LocalDate memberBirth;
    private Integer memberMoney;
    private Float memberTemper;


    private String memberDetailAddr;
    private String memberAddr;
    private String memberWishAddr;
    private String memberAccount;
    private String memberImage;
    private String memberBank;

    @OneToMany(mappedBy = "member")
    private List<SocketRelation> memberSocketList;

    @OneToMany(mappedBy = "member")
    private List<Product> memberProductList;
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
                  String memberAccount,
                  String memberImage,String memberBank,List<SocketRelation> memberSocketList
    ) {
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
        this.memberImage = memberImage;
        this.memberBank = memberBank;
        this.memberSocketList = memberSocketList;
    }
    public void addSocketList(SocketRelation socketRelation){
        memberSocketList.add(socketRelation);

    }
    public void updateMember(MemberUpdateDto memberUpdateDto, String updateImagePath) {
        this.memberPw = memberUpdateDto.getUpdatePw();
        this.memberAddr = memberUpdateDto.getUpdateAddr();
        this.memberDetailAddr = memberUpdateDto.getUpdateDetailAddr();
        this.memberWishAddr = memberUpdateDto.getUpdateWishAddr();
        this.memberImage = updateImagePath;
    }
}
