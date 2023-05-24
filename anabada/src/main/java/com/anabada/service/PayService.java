package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.PayDto;
import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayService {

    private final MemberRepository memberRepository;

    @Transactional
    public void addMoney(MemberDetailDTO memberDetailDTO, PayDto payDto) {
        Member member = memberRepository.findByMemberIdAndMemberExistFalse(memberDetailDTO.getUsername());
        member.updateMemberMoney(payDto);
    }
}
