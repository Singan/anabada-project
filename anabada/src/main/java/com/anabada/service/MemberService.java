package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import com.anabada.security.token.JwtTokenProvider;
import com.anabada.security.token.TokenResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public boolean existsMemberByMemberId(String id){
        return memberRepository.existsMemberByMemberId(id);
    }
    public Member findByMemberId(String id){
        if (!existsMemberByMemberId(id)) {
            throw new RuntimeException("없는 회원입니다.");
        }
        return memberRepository.findByMemberId(id);

    }

    public Long memberJoin(MemberJoinDto memberJoinDto) {
        if (!existsMemberByMemberId(memberJoinDto.getId())) {
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));
            Member member = memberJoinDto.getMember();
            memberRepository.save(member);
            return member.getMemberNo();
        }
        throw new RuntimeException("중복된 회원입니다.");
    }

    public TokenResultDto memberLogin(MemberLoginDto memberLoginDto) {
        Member member = findByMemberId(memberLoginDto.getId());
        if (!passwordEncoder.matches(memberLoginDto.getPw(), member.getMemberPw())) {
            throw new RuntimeException("없는 회원입니다.");
        }
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO(member);
        TokenResultDto tokenResultDto = TokenResultDto.builder().
                accessToken(jwtTokenProvider.generateJwtToken(memberDetailDTO)).
                build();
        return tokenResultDto;
    }

    @Override
    public MemberDetailDTO loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = findByMemberId(id);
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO(member);

        return memberDetailDTO;
    }

    public MyPageFindDto myPage(String token) {
        String memberId = jwtTokenProvider.parseTokenToUserInfo(token);
        Member member = findByMemberId(memberId);
        MyPageFindDto myPageFindDto = new MyPageFindDto(member);

        return myPageFindDto;
    }
}
