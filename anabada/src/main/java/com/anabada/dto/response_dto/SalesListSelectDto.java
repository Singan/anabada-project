package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SalesListSelectDto {
    private Integer bidPrice;
    private String productThumbnail;
    private Long productNo;
    private LocalDateTime createDateTime;
    private LocalDateTime bidTime;
    private String memberName;
    private Long memberNo;
    private Integer productPrice;
    private Boolean isBidComplete;

    @Builder

    public SalesListSelectDto(Integer bidPrice, String productThumbnail, Long productNo, LocalDateTime createDateTime,
                              LocalDateTime bidTime, String memberName, Long memberNo, Integer productPrice, Boolean isBidComplete) {
        this.bidPrice = bidPrice;
        this.productThumbnail = productThumbnail;
        this.productNo = productNo;
        this.createDateTime = createDateTime;
        this.bidTime = bidTime;
        this.memberName = memberName;
        this.memberNo = memberNo;
        this.productPrice = productPrice;
        this.isBidComplete = isBidComplete;
    }
}
