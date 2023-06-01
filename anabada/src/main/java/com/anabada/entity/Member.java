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



    private String memberDetailAddr;
    private String memberAddr;
    private String memberWishAddr;
    private String memberImage;


    @OneToMany(mappedBy = "member")
    private List<Product> memberProductList;

    @Column(name="member_exist")
    private boolean memberExist;
    @Builder
    public Member(Long memberNo, String memberName,
                  String memberId,
                  String memberPw,
                  LocalDate memberBirth,
                  Integer memberMoney,
                  String memberDetailAddr,
                  String memberAddr,
                  String memberWishAddr,
                  String memberImage,
                  List<Product> memberProductList,
                  boolean memberExist
    ) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberBirth = memberBirth;
        this.memberMoney = memberMoney;
        this.memberDetailAddr = memberDetailAddr;
        this.memberAddr = memberAddr;
        this.memberWishAddr = memberWishAddr;
        this.memberImage = memberImage;
        this.memberProductList = memberProductList;
        this.memberExist = memberExist;
    }


    // 비밀번호, 이미지, 주소 변경
    public void updateMember(MemberUpdateDto memberUpdateDto, String updateImagePath) {
        this.memberPw = memberUpdateDto.getUpdatePw();
        this.memberAddr = memberUpdateDto.getUpdateAddr();
        this.memberDetailAddr = memberUpdateDto.getUpdateDetailAddr();
        this.memberWishAddr = memberUpdateDto.getUpdateWishAddr();
        this.memberImage = updateImagePath;
    }

    // 이미지만 빼고 변경
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        this.memberPw = memberUpdateDto.getUpdatePw();
        this.memberAddr = memberUpdateDto.getUpdateAddr();
        this.memberDetailAddr = memberUpdateDto.getUpdateDetailAddr();
        this.memberWishAddr = memberUpdateDto.getUpdateWishAddr();
    }

    // 이미지와 주소만 변경
    public void updateMemberImageAddr(MemberUpdateDto memberUpdateDto, String updateImagePath) {
        this.memberAddr = memberUpdateDto.getUpdateAddr();
        this.memberDetailAddr = memberUpdateDto.getUpdateDetailAddr();
        this.memberWishAddr = memberUpdateDto.getUpdateWishAddr();
        this.memberImage = updateImagePath;
    }

    // 주소만 변경
    public void updateMemberAddr(MemberUpdateDto memberUpdateDto) {
        this.memberAddr = memberUpdateDto.getUpdateAddr();
        this.memberDetailAddr = memberUpdateDto.getUpdateDetailAddr();
        this.memberWishAddr = memberUpdateDto.getUpdateWishAddr();
    }

    public void memberExistUpdate(){
        this.memberExist = true;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }

    public void updateMemberMoney(PayDto payDto) {
        this.memberMoney += payDto.getAddMoney();
    }
}
