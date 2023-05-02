package com.anabada.dto.request_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BidInsertDto {

    private Integer bidPrice;
    private Long productNo;

    public Bid getBid(MemberDetailDTO principal) {
        //MemberDetailDTO > Member
        Member member = principal.getMember();
        Product product = Product.builder().productNo(productNo).build();
        LocalDateTime bidTime = LocalDateTime.now();
        Bid bid = Bid.builder()
                .member(member)
                .price(bidPrice)
                .bidding(false)
                .time(bidTime)
                .product(product)
                .build();
        return bid;
    }
}
