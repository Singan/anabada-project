package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index( name = "bid_price_index",columnList ="bid_price" ))
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



    @Builder
    public Bid(Long bidNo, Product product, Member member, Integer price, LocalDateTime time) {
        this.bidNo = bidNo;
        this.product = product;
        this.member = member;
        this.price = price;
        this.time = time;
    }
}
