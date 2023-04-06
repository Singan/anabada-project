package com.anabada.service;

import com.anabada.dto.MemberJoinDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findByUserId(String id){
        return memberRepository.findByMemberId(id);
    }
    public Long memberJoin(MemberJoinDto memberJoinDto){

        Member member = memberJoinDto.getMember();
        if(findByUserId(member.getMemberId()).isEmpty()){
            memberRepository.save(member);
            return member.getMemberNo();
        }
        throw new IllegalStateException("중복된 회원입니다.");


    }
}
