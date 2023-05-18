package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class FindBiddingDetailDto {
    private Long bidNo;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime bidTime;
    private Integer bidPrice;
    private Long productNo;
    private String productName;
    private String productThumbnail;
    private boolean bidding;

    public FindBiddingDetailDto(Bid bid, String prefix) {
        this.bidNo = bid.getBidNo();
        this.bidTime = bid.getTime();
        this.bidPrice = bid.getPrice();
        this.productName = bid.getProduct().getProductName();
        this.productThumbnail = prefix + bid.getProduct().getProductThumbnail();
        this.bidding = bid.isBidding();
        this.productNo = bid.getProduct().getProductNo();
    }
}
