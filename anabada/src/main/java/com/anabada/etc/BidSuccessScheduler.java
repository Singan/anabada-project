package com.anabada.etc;

import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BidSuccessScheduler {
    private final BidService bidService;

    @Scheduled(fixedDelay = 10000)//1000에 1초
    public void bidSuccess(){
        List<MaxBidProductNoInterface> bidProductNoList = bidService.productByMaxBidList();
        List<Long> productNoList = bidProductNoList.stream().map(m -> m.getProductNo())
                .collect(Collectors.toList());
        if(!productNoList.isEmpty()){
            bidService.updateProductBidSuccess(productNoList);
        }
    }

}
