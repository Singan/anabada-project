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
    private final BidManagement bidManagement;
    @Scheduled(fixedDelay = 1000)//1000에 1초
    @Transactional
    public void bidSuccess() {
        List<SuccessfulBid> successfulBids  = bidManagement.checkSuccessBidHashMap();
        if (!successfulBids.isEmpty()) {
            List<Long> productNoList = successfulBids.stream().map(successfulBid -> successfulBid.getProduct().
                    getProductNo()).collect(Collectors.toList());
            bidService.updateProductBidSuccess(productNoList);
            successBidRepository.saveAll(successfulBids);
        }
        for (SuccessfulBid bidAndProduct : successfulBids) {
            simpMessagingTemplate.convertAndSend("/product/myProduct/" + bidAndProduct.getProduct().getProductNo(),
                    new SuccessBidToastDto(
                            bidAndProduct.getProduct().getProductNo(),
                            bidAndProduct.getProduct().getProductName(),
                            "등록하신 상품이 낙찰되었습니다. 상품명 : ", true
                    ));
        }

    }

}
