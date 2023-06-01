package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.entity.SuccessfulBid;
import com.anabada.repository.SuccessBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuccessBidService {
    private final SuccessBidRepository successBidRepository;
    @Value("${s3.bucket.endpoint}")
    private String s3EndPoint;

    public SuccessBidDto successBidDto(Long successBidNo,MemberDetailDTO memberDetailDTO) {
        Long memberNo = memberDetailDTO.getNo();
        SuccessfulBid successfulBid = successBidRepository.findSuccessfulBid(successBidNo);
        if(successfulBid.getBid().getMember().getMemberNo() != memberNo&&
                successfulBid.getProduct().getMember().getMemberNo()!=memberNo){
            throw new RuntimeException("낙찰 또는 판매와 관련없는 회원은 해당 페이지에 접근할 수 없습니다.");
        }


        return new SuccessBidDto(successfulBid, s3EndPoint);
    }
}
