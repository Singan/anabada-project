package com.anabada.service;

import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.entity.Bid;
import com.anabada.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuccessBidService {

    private final BidRepository bidRepository;

    public SuccessBidDto getSuccessBid(Long bidNo) {

        Bid bid = bidRepository.findById(bidNo).get();
        // 입찰한 상품이 낙찰 되었다면
        if (bid.isBidding()) {

        }

    }
}
