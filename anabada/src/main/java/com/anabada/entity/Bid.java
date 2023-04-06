package com.anabada.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Bid {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product product;

    @Column(name = "bid_price")
    private Integer price;

    @Column(name = "bid_time")
    private LocalDateTime time;

    @Column(name = "bid_bidding")
    private boolean bidding;

    private String bidcol;
}
