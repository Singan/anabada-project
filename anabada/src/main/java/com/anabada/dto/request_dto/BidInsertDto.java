package com.anabada.dto.request_dto;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BidInsertDto {

//    private String memberName;
    private Integer bidPrice;
    private LocalDateTime insertDate;
//    private Long productNo;
    private boolean bidding;

    public Bid getBid() {

        Member member = Member.builder().memberNo(1L).build();  // member 인위적으로 생성
        Product product = Product.builder().productNo(1L).build();  // productName 인위적으로 생성
        Bid bid = Bid.builder()
                .member(member)
                .price(bidPrice)
                .bidding(bidding)
                .time(insertDate)
                .product(product)
                .build();
        return bid;
    }
}
