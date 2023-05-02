package com.anabada.dto;

import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
public class MemberDetailDTO implements UserDetails {
    private Long no;
    private String username;
    private String password;


    public MemberDetailDTO(Member member) {
        this.no = member.getMemberNo();
        this.username = member.getMemberId();
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
        return username;
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

    public Member getMember(){
        Member member = Member
                .builder()
                .memberNo(no)
                .memberId(username)
                .build();

        return member;
    };
}
