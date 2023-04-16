package com.anabada.entity;


import lombok.AccessLevel;
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
    @JoinColumn(name = "productNo")
    private Product product;

    private String imageAddr;

}
