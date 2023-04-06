package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @Column
    private String productName;

    @OneToOne(mappedBy = "order_no")
    private Order order;



    @Column
    private String productDetail;
    @Column
    private Integer productPrice;
    @Column
    private String productUseDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_no")
    private Category category;
    @Builder
    public Product(Long productNo, Member member, String productName, String productDetail, Integer productPrice, String productUseDate, Category category) {
        this.productNo = productNo;
        this.member = member;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productUseDate = productUseDate;
        this.category = category;
    }
}
