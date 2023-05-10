package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Column
    private String productName;

//    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
//    private Orders orders;

    @Column
    private String productDetail;
    @Column
    private Integer productPrice;
    @Column
    private String productUseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_no", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductImage> productImageList;

    @Column
    private LocalDateTime createDateTime;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_socket_no",nullable = false)
    private ProductSocket productSocket;
    @Column(columnDefinition = "bigint default 0")
    private Long productVisit;
//    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
//    private CurrentBid currentBid;
    public void upProductVisit(){
        productVisit=productVisit+1;
    }
    @Builder
    public Product(Long productNo, Member member, String productName, String productDetail, Integer productPrice,
                   String productUseDate, Category category,Long productVisit,
                   List<ProductImage> productImageList,ProductSocket productSocket) {
        this.productNo = productNo;
        this.member = member;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productUseDate = productUseDate;
        this.category = category;
        this.productImageList = productImageList;
        this.createDateTime = LocalDateTime.now();
        this.productSocket = productSocket;
        this.productVisit = productVisit;
    }
}
