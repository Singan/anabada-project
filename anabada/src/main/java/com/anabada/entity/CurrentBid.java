package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currentBid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no" , nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no",nullable = false)
    private Member member;



    @Column(name = "create_time",nullable = false)
    private LocalDateTime localDateTime;

    @Column(columnDefinition = "int default 0")
    private Integer price;
    @Builder
    public CurrentBid(Long currentBid, Member member, LocalDateTime localDateTime, Integer price,Product product) {
        this.currentBid = currentBid;
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
