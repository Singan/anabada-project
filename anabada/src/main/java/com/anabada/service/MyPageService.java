package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.MyBidDto;
import com.anabada.dto.response_dto.MyBuyDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.nativeQuery.MyBidHistory;
import com.anabada.entity.nativeQuery.MyBuyListInterface;
import com.anabada.repository.BidRepository;
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
    private final BidRepository bidRepository;

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
                        .productThumbnail(s3Endpoint+s.getProductThumbnail())
                        .successBidNo(s.getSuccessBidNo())
                        .productTime(s.getProductTime())
                        .productMemberName(s.getProductMemberName())
                        .productMemberNo(s.getProductMemberNo())
                        .build()).collect(Collectors.toList())
        );
    }
    // 입찰 내역
    public ResultList<List<MyBidDto>> findBiddingDetail(MemberDetailDTO memberDetailDTO, Pageable pageable) {
        Page<MyBidHistory> bidList = bidRepository.findBidListByMemberNo(memberDetailDTO.getNo(),pageable);
        List<MyBidDto> biddingDetail =
                bidList.stream().map(bid -> MyBidDto.builder()
                        .bidNo(bid.getBidNo())
                        .bidPrice(bid.getBidPrice())
                        .bidTime(bid.getBidTime())
                        .productNo(bid.getProductNo())
                        .productMemberName(bid.getProductMemberName())
                        .productTime(bid.getProductTime())
                        .productName(bid.getProductName())
                        .productPrice(bid.getProductPrice())
                        .productSuccessIs(bid.getProductSuccessIs())
                        .productThumbnail(bid.getProductThumbnail())
                        .memberAddr(bid.getMemberAddr())
                        .memberNo(bid.getMemberNo())
                        .build()).collect(Collectors.toList());
        return new ResultList<>(bidList.getTotalPages(),biddingDetail);
    }
}
