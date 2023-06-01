package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MemberJoinDto;
import com.anabada.dto.request_dto.MemberLoginDto;
import com.anabada.dto.request_dto.MemberUpdateDto;
import com.anabada.dto.request_dto.MypageConfirmDto;
import com.anabada.dto.response_dto.*;
import com.anabada.entity.Member;
import com.anabada.entity.nativeQuery.MemberInfoInterface;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.MemberRepository;
import com.anabada.config.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @Value("${s3.bucket.endpoint}")
    private String s3EndPoint;

    public boolean existsMemberByMemberId(String id) {
        return memberRepository.existsMemberByMemberId(id);
    }


    public Member findByMemberId(String id) {
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(id);
        if (member == null) {
            throw new RuntimeException("없는 회원입니다.");
        }
        return member;

    }

    public MemberInfoDto findByMemberNoWithSocketList(Long memberNo) {
        System.out.println("쿼리 실행 부분");
        MemberInfoInterface member = memberRepository.findMemberAndProductList(memberNo);
        System.out.println("쿼리 실행 부분---------------------------");
        List<Long> productNoList;
        if (member.getProductNoList() != null) {
            productNoList   = member.getProductNoList();
        }else{
            productNoList= new ArrayList<>();
        }

        MemberInfoDto memberInfoDto = MemberInfoDto
                .builder()
                .productSocketNoList(productNoList)
                .image(s3EndPoint + member.getMemberImage())
                .name(member.getMemberName())
                .no(member.getMemberNo())
                .build();
        System.out.println("쿼리 실행 부분 레이지로딩되나?");
        return memberInfoDto;
    }

    @Transactional
    public Long memberJoin(MemberJoinDto memberJoinDto) {
        if (!existsMemberByMemberId(memberJoinDto.getId())) {
            memberJoinDto.setPw(passwordEncoder.encode(memberJoinDto.getPw()));

            MultipartFile file = memberJoinDto.getImage();

            if (!((file == null) || (file.isEmpty()))) {
                String profilePath = fileProcessor.fileSave(file, "member");
                memberJoinDto.setProfileImagePath(profilePath);
            } else {
                String basicProfileImagePath = "member/basic/user.png";
                memberJoinDto.setProfileImagePath(basicProfileImagePath);
            }
            Member member = memberJoinDto.getMember();

            memberRepository.save(member);
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
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(principal.getUsername());
        return new MyPageFindDto(member, s3EndPoint);
    }

    public Authentication getAuthentication(Member member) {
        MemberDetailDTO userDetails = new MemberDetailDTO(member);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean confirmPassword(MemberDetailDTO memberDetailDTO, MypageConfirmDto mypageConfirmDto) {
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(memberDetailDTO.getUsername());
        String confirmPw = mypageConfirmDto.getConfirmPassword();
        if (passwordEncoder.matches(confirmPw, member.getMemberPw())) {
            return true;
        } else {
            throw new RuntimeException("비밀번호가 다릅니다.");
        }
    }

    @Transactional
    public MemberUpdateFindDto memberUpdate(MemberDetailDTO memberDetailDTO, MemberUpdateDto memberUpdateDto) {
        MultipartFile imageFile = memberUpdateDto.getUpdateImage();
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(memberDetailDTO.getUsername());

        String updatePw = memberUpdateDto.getUpdatePw();  // 변경된 비밀번호
        String confirmPw = memberUpdateDto.getConfirmPw();  // 변경된 비밀번호 확인
        String updateAddr = memberUpdateDto.getUpdateAddr();  // 변경된 주소
        String detailAddr = memberUpdateDto.getUpdateDetailAddr();  // 변경된 상세 주소
        String wishAddr = memberUpdateDto.getUpdateWishAddr();  // 변경된 거래 희망지

        // 기존의 비밀 번호가 새로 입력받은 비밀 번호랑 같은지 확인
        if (passwordEncoder.matches(memberUpdateDto.getUpdatePw(), member.getMemberPw())) {
            throw new RuntimeException("같은 비밀번호로 변경할 수 없습니다.");
        }

        if (updateAddr.equals("") || detailAddr.equals("") || wishAddr.equals("")) {
            throw new RuntimeException("주소를 전부 입력하세요");
        }

        // 비밀번호를 두 칸을 다 입력할 때
        if (!updatePw.equals("") && !confirmPw.equals("")) {
            if (memberUpdateDto.getUpdatePw().equals(memberUpdateDto.getConfirmPw())) {  // 입력한 비밀번호가 전부 같다면
                if ((imageFile != null) && !imageFile.isEmpty()) {  // 이미지 파일이 있다면
                    memberUpdateDto.setUpdatePw(passwordEncoder.encode(memberUpdateDto.getUpdatePw()));
                    String updateImagePath = fileProcessor.fileSave(memberUpdateDto.getUpdateImage(), "member");  // 변경된 이미지파일
                    member.updateMember(memberUpdateDto, updateImagePath);
                    return new MemberUpdateFindDto(member, updateImagePath);
                } else {
                    memberUpdateDto.setUpdatePw(passwordEncoder.encode(memberUpdateDto.getUpdatePw()));
                    member.updateMember(memberUpdateDto);
                    return new MemberUpdateFindDto(member, member.getMemberImage());
                }
            } else {
                throw new RuntimeException("비밀번호가 같은지 확인해주세요");
            }
        } else {
            throw new RuntimeException("새 비밀번호를 입력해주세요");
        }
    }

    // 회원 정보 수정 페이지에 멤버 아이디, 이름, 이미지 보여주기
    public ShowUpdateMemberDto showMemberInfo(MemberDetailDTO memberDetailDTO) {
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(memberDetailDTO.getUsername());
        return new ShowUpdateMemberDto(member, s3EndPoint);
    }

    @Transactional
    public void deleteMember(MemberDetailDTO memberDetailDTO) {
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(memberDetailDTO.getUsername());
        member.memberExistUpdate();
    }
}
