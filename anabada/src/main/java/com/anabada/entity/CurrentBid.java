package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentBid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currentBidNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no",nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no",nullable = false)
    private Member member;

    @Column(name = "create_time",nullable = false)
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "int default 0")
    private Integer price;
    @Builder
    public CurrentBid(Long currentBidNo, Product product, Member member, LocalDateTime localDateTime, Integer price) {
        this.currentBidNo = currentBidNo;
        this.product = product;
        this.member = member;
        this.localDateTime = localDateTime;
        this.price = price;
    }
}
