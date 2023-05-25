package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.MyBuyDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.SuccessfulBid;
import com.anabada.repository.SuccessBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final SuccessBidRepository successBidRepository;

    @Value("${s3.bucket.endpoint}")
    private String s3Endpoint;

    public ResultList<List<MyBuyDto>> myBuyList(MemberDetailDTO memberDetailDTO){
        List<SuccessfulBid> successfulBids = successBidRepository.successfulBidList(memberDetailDTO.getNo());

        return new ResultList<>(
                successfulBids.stream().map(s -> MyBuyDto.builder()
                .productNo(s.getProduct().getProductNo())
                .productThumbnail(s3Endpoint+s.getProduct().getProductThumbnail())
                .bidPrice(s.getBid().getPrice())
                .productName(s.getProduct().getProductName())
                .successTime(s.getBid().getTime())
                .productSellerName(s.getProduct().getMember().getMemberName())
                .build()).collect(Collectors.toList())
        );
    }
}
