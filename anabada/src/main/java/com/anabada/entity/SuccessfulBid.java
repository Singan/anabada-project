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
    private Long currentBid;

    @OneToOne
    private Bid bid;

    @OneToOne
    private Product product;
    @Builder
    public SuccessfulBid(Long currentBid, Bid bid, Product product) {
        this.currentBid = currentBid;
        this.bid = bid;
        this.product = product;
    }
}
