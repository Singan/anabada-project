package com.anabada.service;

import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.entity.Bid;
import com.anabada.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public Long bidSave(BidInsertDto bidInsertDto) {
        Bid bid = bidInsertDto.getBid();
        bidRepository.save(bid);
        return bid.getBidNo();
    }
}
