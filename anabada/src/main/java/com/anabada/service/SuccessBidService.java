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

    public SuccessBidDto successBidDto(Long successBidNo) {
        SuccessfulBid successfulBid = successBidRepository.findSuccessfulBid(successBidNo);
        return new SuccessBidDto(successfulBid, s3EndPoint);
    }
}
