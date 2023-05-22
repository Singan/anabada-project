package com.anabada.etc;

import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.repository.SuccessBidRepository;
import com.anabada.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BidSuccessScheduler {
    private final BidService bidService;
    private final SuccessBidRepository successBidRepository;

    @Scheduled(fixedDelay = 10000)//1000에 1초
    @Transactional
    public void bidSuccess(){
        List<MaxBidProductNoInterface> bidProductNoList = bidService.productByMaxBidList();
        List<Long> productNoList = bidProductNoList.stream().map(m -> m.getProductNo())
                .collect(Collectors.toList());
        System.out.println("스케쥴러 실행");
        if(!productNoList.isEmpty()){
            System.out.println("업데이트 비드 실행");
            bidService.updateProductBidSuccess(productNoList);
            List<SuccessfulBid> successfulBids = bidProductNoList.stream().map(bidProduct -> SuccessfulBid
                    .builder()
                    .bid(Bid.builder().bidNo(bidProduct.getBidNo()).build())
                    .product(Product.builder().productNo(bidProduct.getProductNo()).build())
                    .build()).collect(Collectors.toList());
            System.out.println("세이브 실행");
            successBidRepository.saveAll(successfulBids);
        }

    }

}
