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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class BidManagement {
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
    private final HashMap<Product, Bid> biddingHashMap;
    private final BidRepository bidRepository;


    public void updateHaspMap(Product productNo, Bid newBid){
        biddingHashMap.put(productNo,newBid);
    }



    public List<SuccessfulBid> checkSuccessBidHashMap(){
        List<SuccessfulBid> successfulBids= new ArrayList<>();
        for (Product product:biddingHashMap.keySet()){
            Bid bid = biddingHashMap.get(product);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime bidTime = bid.getTime();
            Duration duration = Duration.between(bidTime, now);
            long minutesPassed = duration.toMinutes();
            if (minutesPassed >= 10) {
                SuccessfulBid s = SuccessfulBid.builder().product(product).bid(bid).status(Status.대기).build();
                successfulBids.add(s);
                biddingHashMap.remove(product);
            }
        }
        return successfulBids;
    }
}
