package com.anabada.service;

import com.anabada.dto.MemberJoinDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findByUserId(String id){
        return memberRepository.findByMemberId(id);
    }

}
