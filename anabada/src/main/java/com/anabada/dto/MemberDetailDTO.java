package com.anabada.dto;

import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
@Getter
@Setter
public class MemberDetailDTO implements UserDetails {
    private Long no;
    private String username;
    private String password;
    private String memberName;


    private LocalDate memberBirth;
    private Integer memberMoney;
    private Float memberTemper;


    private String memberDetailAddr;
    private String memberAddr;
    private String memberWishAddr;
    private String memberAccount;

    public MemberDetailDTO(Member member) {
        this.no = member.getMemberNo();
        this.username = member.getMemberId();
        this.password = member.getMemberPw();
        this.memberName = member.getMemberName();
        this.memberBirth = member.getMemberBirth();
        this.memberMoney = member.getMemberMoney();
        this.memberTemper = member.getMemberTemper();
        this.memberDetailAddr = member.getMemberDetailAddr();
        this.memberAddr = member.getMemberAddr();
        this.memberWishAddr = member.getMemberWishAddr();
        this.memberAccount = member.getMemberAccount();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return no.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
