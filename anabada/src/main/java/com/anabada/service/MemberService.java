package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.entity.Member;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.MemberRepository;
import com.anabada.config.token.JwtTokenProvider;
import com.anabada.config.token.TokenResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileProcessor fileProcessor;
    public boolean existsMemberByMemberId(String id){
        return memberRepository.existsMemberByMemberId(id);
    }


    public Member findByMemberId(String id){
        if (!existsMemberByMemberId(id)) {
            throw new RuntimeException("없는 회원입니다.");
        }
        return memberRepository.findByMemberId(id);

    }
    @Transactional(readOnly = true)
    public Long memberJoin(MemberJoinDto memberJoinDto) {
        if (!existsMemberByMemberId(memberJoinDto.getId())) {
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));
            String profilePath =null;
            MultipartFile file = memberJoinDto.getImage();
            if(!((file==null)||(file.isEmpty()))){
                profilePath = fileProcessor.fileSave(file);
            }
            Member member = memberJoinDto.getMember(profilePath);

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

    public MyPageFindDto myPage(MemberDetailDTO principal) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO(principal.getMember());
        return new MyPageFindDto(memberDetailDTO);
    }

    public Authentication getAuthentication(String id) {
        MemberDetailDTO userDetails = loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
