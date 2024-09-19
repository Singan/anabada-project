package com.anabada.etc;

import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.repository.BidRepository;
import com.anabada.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class BidManagement {
    private final HashMap<Product, Bid> biddingHashMap;
    private final BidRepository bidRepository;
    public BidManagement(BidRepository bidRepository) {
        this.biddingHashMap = new HashMap<>();
        this.bidRepository = bidRepository;
        List<MaxBidProductNoInterface> initData = bidRepository.bidList();
        for (MaxBidProductNoInterface m: initData) {
            Bid b = Bid.builder().bidNo(m.getBidNo()).time(m.getBidTime()).build();
            Product product = Product.builder().productNo(m.getProductNo()).productName(m.getProductName()).build();
            biddingHashMap.put(product,b);
        }
    }



    public synchronized  void updateHaspMap(Product productNo, Bid newBid){
        biddingHashMap.put(productNo,newBid);
    }



    public List<SuccessfulBid> checkSuccessBidHashMap(){
        List<SuccessfulBid> successfulBids= new ArrayList<>();
        Iterator<Product> mapKey = biddingHashMap.keySet().iterator();
        while (mapKey.hasNext()){
            Product product = mapKey.next();
            Bid bid = biddingHashMap.get(product);
            bid.setProduct(product);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime bidTime = bid.getTime();
            Duration duration = Duration.between(bidTime, now);
            long minutesPassed = duration.toMinutes();
            if (minutesPassed >= 10) {
                SuccessfulBid s = SuccessfulBid.builder().bid(bid).status(Status.대기).build();
                successfulBids.add(s);
                mapKey.remove();            }
        }
        return successfulBids;
    }
}
