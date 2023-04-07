package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MemberJoinDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberPasswordService  implements UserDetailsService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public Long memberJoin(MemberJoinDto memberJoinDto){
        if(memberRepository.findByMemberId(memberJoinDto.getId()).isEmpty()){
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));
            Member member = memberJoinDto.getMember();

            memberRepository.save(member);
            return member.getMemberNo();
        }
        throw new IllegalStateException("중복된 회원입니다.");
    }

    public Long memberLogin(MemberJoinDto memberJoinDto){
        if(memberRepository.findByMemberId(memberJoinDto.getId()).isEmpty()){
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));
            Member member = memberJoinDto.getMember();

            memberRepository.save(member);
            return member.getMemberNo();
        }
        throw new IllegalStateException("중복된 회원입니다.");
    }
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new MemberDetailDTO(memberRepository.findByMemberId(id).get());
    }
}
