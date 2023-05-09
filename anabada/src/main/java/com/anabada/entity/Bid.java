package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bid {//입찰내역

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Column(name = "bid_price", nullable = false)
    private Integer price;

    @Column(name = "bid_time", nullable = false)
    private LocalDateTime time;

    @Column(name = "bid_bidding", nullable = false)
    private boolean bidding;
    @OneToOne(mappedBy = "product")
    private CurrentBid currentBid;
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
