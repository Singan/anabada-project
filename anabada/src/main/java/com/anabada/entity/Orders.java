package com.anabada.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    private boolean orderTake;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private Product product;

    @Builder
    public Orders(Long orderNo, boolean orderTake, Member member, Product product) {
        this.orderNo = orderNo;
        this.orderTake = orderTake;
        this.member = member;
        this.product = product;
    }
}
