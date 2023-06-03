package com.anabada.entity;

import com.anabada.etc.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class SuccessfulBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long successBidProductNo;

    @OneToOne
    @JoinColumn(nullable = false,name="bid_no")
    private Bid bid;

    @OneToOne
    @JoinColumn(nullable = false,name = "product_no")
    private Product product;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Builder
    public SuccessfulBid(Long successBidProductNo, Bid bid, Product product,Status status) {
        this.successBidProductNo = successBidProductNo;
        this.bid = bid;
        this.product = product;
        this.status = status;
    }
}
