package com.anabada.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Bid {//입찰내역

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private Member member;

    @Column(name = "bid_price")
    private Integer price;

    @Column(name = "bid_time")
    private LocalDateTime time;

    @Column(name = "bid_bidding")
    private boolean bidding;

    @Builder

    public Bid(Long bidNo, Product product, Member member, Integer price, LocalDateTime time, boolean bidding) {
        this.bidNo = bidNo;
        this.product = product;
        this.member = member;
        this.price = price;
        this.time = time;
        this.bidding = bidding;
    }
}
