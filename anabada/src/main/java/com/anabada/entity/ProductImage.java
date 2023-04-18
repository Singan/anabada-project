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

    // Jpa에서 복합키 설정 찾아보기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product product;

    private String imageAddr;

    @Builder
    public ProductImage(Long productImageNo, Product product, String imageAddr) {
        this.productImageNo = productImageNo;
        this.product = product;
        this.imageAddr = imageAddr;
    }
}
