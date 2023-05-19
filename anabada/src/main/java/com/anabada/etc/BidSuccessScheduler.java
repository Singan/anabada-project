package com.anabada.etc;

import com.anabada.entity.Bid;
import com.anabada.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BidSuccessScheduler {
    private final BidService bidService;

    @Scheduled(fixedDelay = 1000)
    public void bidSuccess(){
//        List<Bid> bidList= bidService.productByMaxBidList();
//        for (Bid b:bidList) {
//            System.out.println(b.getBidNo());
//        }
    }

}
