package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.PayDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    private final MemberRepository memberRepository;

    public void addMoney(MemberDetailDTO memberDetailDTO, PayDto payDto) {
        Member member = memberRepository.findByMemberId(memberDetailDTO.getUsername());
        member.updateMemberMoney(payDto);
    }
}
