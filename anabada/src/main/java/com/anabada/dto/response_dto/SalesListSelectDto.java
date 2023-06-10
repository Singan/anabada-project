package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SalesListSelectDto {
    private Integer bidPrice;
    private String productThumbnail;
    private Long productNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bidTime;
    private String memberName;
    private Long memberNo;
    private Integer productPrice;
    private Boolean isBidComplete;
    private String productName;

    @Builder

    public SalesListSelectDto(Integer bidPrice, String productThumbnail, Long productNo, LocalDateTime createDateTime,
                              LocalDateTime bidTime, String memberName, Long memberNo, Integer productPrice, Boolean isBidComplete,String productName) {
        this.bidPrice = bidPrice;
        this.productThumbnail = productThumbnail;
        this.productNo = productNo;
        this.createDateTime = createDateTime;
        this.bidTime = bidTime;
        this.memberName = memberName;
        this.memberNo = memberNo;
        this.productPrice = productPrice;
        this.isBidComplete = isBidComplete;
        this.productName = productName;
    }
}
