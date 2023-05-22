package com.anabada.entity.nativeQuery;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.SuccessfulBid;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SalesListSelectDto {
    private Member member;
    private Bid bid;
    private Product product;
    private SuccessfulBid successfulBid;

    @Builder
    public SalesListSelectDto(Member member, Bid bid, Product product, SuccessfulBid successfulBid) {
        this.member = member;
        this.bid = bid;
        this.product = product;
        this.successfulBid = successfulBid;
    }
}
