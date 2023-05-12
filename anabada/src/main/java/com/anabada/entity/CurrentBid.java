package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentBid {

    @Id
    @Column(name = "product_no" )
    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no",nullable = false)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_no" ,referencedColumnName="product_no")
    private Product product;

    @Column(name = "create_time",nullable = false)
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "int default 0")
    private Integer price;
    @Builder
    public CurrentBid(Long productNo, Member member, LocalDateTime localDateTime, Integer price,Product product) {
        this.productNo = productNo;
        this.member = member;
        this.localDateTime = localDateTime;
        this.price = price;
        this.product = product;
    }


    public void updateCurrentBid(Member member,Integer price){
        this.member = member;
        this.localDateTime = LocalDateTime.now();
        this.price = price;
    }
}
