package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessfulBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long successBidProductNo;

    @OneToOne
    @JoinColumn(nullable = false)
    private Bid bid;

    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    @Builder
    public SuccessfulBid(Long successBidProductNo, Bid bid, Product product) {
        this.successBidProductNo = successBidProductNo;
        this.bid = bid;
        this.product = product;
    }
}
