package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.dto.response_dto.MemberUpdateFindDto;
import com.anabada.dto.response_dto.MyPageFindDto;
import com.anabada.entity.Member;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.MemberRepository;
import com.anabada.config.token.JwtTokenProvider;
import com.anabada.dto.response_dto.LoginResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileProcessor fileProcessor;

    public boolean existsMemberByMemberId(String id) {
        return memberRepository.existsMemberByMemberId(id);
    }


    public Member findByMemberId(String id) {
        if (!existsMemberByMemberId(id)) {
            throw new RuntimeException("없는 회원입니다.");
        }
        return memberRepository.findByMemberId(id);

    }
    public Member findByMemberNoWithSocketList(String id){
        Member member = memberRepository.findMemberByMemberId(id);
        return member;
    }
    @Transactional
    public Long memberJoin(MemberJoinDto memberJoinDto) {
        if (!existsMemberByMemberId(memberJoinDto.getId())) {
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));

            MultipartFile file = memberJoinDto.getImage();
            Member member = memberJoinDto.getMember();
            memberRepository.save(member);
            if (!((file == null) || (file.isEmpty()))) {
                String profilePath = fileProcessor.fileSave(file,"member",member.getMemberNo());
                member.setMemberImage(profilePath);
            }


            return member.getMemberNo();
        }
        throw new RuntimeException("중복된 회원입니다.");
    }

    public LoginResultDto memberLogin(MemberLoginDto memberLoginDto) {
        Member member = findByMemberId(memberLoginDto.getId());
        if (!passwordEncoder.matches(memberLoginDto.getPw(), member.getMemberPw())) {
            throw new RuntimeException("없는 회원입니다.");
        }

        LoginResultDto loginResultDto = LoginResultDto.builder()
                .accessToken(jwtTokenProvider.generateJwtToken(member))
                .build();
        return loginResultDto;
    }

    @Override
    public MemberDetailDTO loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = findByMemberId(id);
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO(member);
        return memberDetailDTO;
    }

    public MyPageFindDto myPage(MemberDetailDTO principal) {
        Member member = memberRepository.findByMemberId(principal.getUsername());
        return new MyPageFindDto(member);
    }

    public Authentication getAuthentication(Member member) {
        MemberDetailDTO userDetails = new MemberDetailDTO(member);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Transactional
    public MemberUpdateFindDto memberUpdate(MemberDetailDTO memberDetailDTO, MemberUpdateDto memberUpdateDto) {
        String updateImagePath = fileProcessor.fileSave(memberUpdateDto.getUpdateImage(),"member",memberDetailDTO.getNo());
        memberUpdateDto.setUpdatePw(passwordEncoder.encode(memberUpdateDto.getUpdatePw()));
        Member member = memberRepository.findByMemberId(memberDetailDTO.getUsername());
        member.updateMember(memberUpdateDto, updateImagePath);
        return new MemberUpdateFindDto(member);
    }
}
