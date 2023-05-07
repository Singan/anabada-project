package com.anabada.entity;

import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.dto.request_dto.PayDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @Column(name = "member_id",unique = true, nullable = false)
    private String memberId;
    @Column(name = "member_pw", nullable = false)
    private String memberPw;
    @Column(name = "member_name", nullable = false)
    private String memberName;
    @Column(name = "member_birth")
    private LocalDate memberBirth;
    @Column(name = "member_money" ,columnDefinition = "int default 0")
    private Integer memberMoney;
    @Column(name = "member_temper")
    private Float memberTemper;


    private String memberDetailAddr;
    private String memberAddr;
    private String memberWishAddr;
    private String memberAccount;
    private String memberImage;

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
                  String memberImage,List<SocketRelation> memberSocketList,
                  List<Product> memberProductList
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
        this.memberSocketList = memberSocketList;
        this.memberProductList = memberProductList;
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

    public void updateMemberMoney(PayDto payDto) {
        this.memberMoney += payDto.getAddMoney();
    }
}
