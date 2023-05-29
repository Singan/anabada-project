package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.MyBuyDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.MyBuyListInterface;
import com.anabada.repository.SuccessBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final SuccessBidRepository successBidRepository;

    @Value("${s3.bucket.endpoint}")
    private String s3Endpoint;

    public ResultList<List<MyBuyDto>> myBuyList(MemberDetailDTO memberDetailDTO, Pageable pageable) {
        Page<MyBuyListInterface> successfulBids = successBidRepository.successfulBidList(memberDetailDTO.getNo(), pageable);

        return new ResultList<>(successfulBids.getTotalPages(),
                successfulBids.stream().map(s -> MyBuyDto.builder()
                        .productNo(s.getProductNo())
                        .bidNo(s.getBidNo())
                        .successTime(s.getSuccessTime())
                        .productName(s.getProductName())
                        .bidPrice(s.getBidPrice())
                        .productThumbnail(s.getProductThumbnail())
                        .successBidNo(s.getSuccessBidNo())
                        .productTime(s.getProductTime())
                        .productMemberName(s.getProductMemberName())
                        .productMemberNo(s.getProductMemberNo())
                        .build()).collect(Collectors.toList())
        );
    }
}
