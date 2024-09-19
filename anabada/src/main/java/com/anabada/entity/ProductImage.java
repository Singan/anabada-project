package com.anabada.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageNo;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private Product product;

    private String imageAddr;

    @Builder
    public ProductImage(Long productImageNo, Product product, String imageAddr) {
        this.productImageNo = productImageNo;
        this.product = product;
        this.imageAddr = imageAddr;
    }
}
