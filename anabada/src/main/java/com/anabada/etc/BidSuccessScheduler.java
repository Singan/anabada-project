package com.anabada.etc;

import com.anabada.dto.response_dto.SuccessBidToastDto;
import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.repository.SuccessBidRepository;
import com.anabada.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay = 10000)//1000에 1초
    @Transactional
    public void bidSuccess(){
        List<MaxBidProductNoInterface> bidProductNoList = bidService.productByMaxBidList();
        List<Long> productNoList = bidProductNoList.stream().map(m -> m.getProductNo())
                .collect(Collectors.toList());
        if(!productNoList.isEmpty()){
            bidService.updateProductBidSuccess(productNoList);
            List<SuccessfulBid> successfulBids = bidProductNoList.stream().map(bidProduct -> SuccessfulBid
                    .builder()
                    .bid(Bid.builder().bidNo(bidProduct.getBidNo()).build())
                    .product(Product.builder().productNo(bidProduct.getProductNo()).build())
                    .build()).collect(Collectors.toList());
            successBidRepository.saveAll(successfulBids);
        }
        for (MaxBidProductNoInterface bidAndProduct:bidProductNoList) {
            simpMessagingTemplate.convertAndSend("/product/myProduct/" + bidAndProduct.getProductNo(),
                    new SuccessBidToastDto(
                            bidAndProduct.getProductNo(),
                            bidAndProduct.getProductName(),
                            "등록하신 상품이 낙찰되었습니다. 상품명 : ",true
                    ));
        }

    }

}
