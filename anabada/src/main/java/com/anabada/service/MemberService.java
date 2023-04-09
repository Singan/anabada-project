package com.anabada.service;

import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public boolean existsMemberByMemberId(String id){
        return memberRepository.existsMemberByMemberId(id);
    }
    public Member findByMemberId(String id){
        if (existsMemberByMemberId(id))
            throw new RuntimeException("없는 회원입니다.");
        return memberRepository.findByMemberId(id);

    }
}
