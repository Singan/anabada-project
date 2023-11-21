package com.anabada.dto.request_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BidInsertDto {

    private Integer bidPrice;
    private Long productNo;
    private String memberImage;
    private String productName;
    @JsonIgnore
    public Bid getBid(Member member) {
        //MemberDetailDTO > Member

        Product product = Product.builder().productName(productName).productNo(productNo).build();
        LocalDateTime bidTime = LocalDateTime.now();
        Bid bid = Bid.builder()
                .member(member)
                .price(bidPrice)
                .time(bidTime)
                .product(product)
                .build();
        return bid;
    }
}
