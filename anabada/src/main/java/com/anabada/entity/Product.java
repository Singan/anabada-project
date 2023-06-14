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
    @Column
    private String productDetail;
    @Column
    private Integer productPrice;
    @Column
    private String productUseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImageList;
    @OneToMany(mappedBy = "product")
    private List<Bid> productBidList;
    @Column
    private LocalDateTime createDateTime;


    @Column(columnDefinition = "bigint default 0")
    private Long productVisit;
    @Column(nullable = false)
    private String productThumbnail;


    @Column(nullable = false)
    private Boolean productIsBidComplete;


    public void upProductVisit() {
        productVisit = productVisit + 1;
    }

    @Builder
    public Product(Long productNo, Member member, String productName, String productDetail, Integer productPrice,
                   String productUseDate, Category category, Long productVisit, List<ProductImage> productImageList,
                   String productThumbnail, boolean productIsBidComplete,
                   List<Bid> productBidList, LocalDateTime localDateTime) {
        this.productNo = productNo;
        this.member = member;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.productUseDate = productUseDate;
        this.category = category;
        this.productImageList = productImageList;
        this.createDateTime = localDateTime;
        this.productVisit = productVisit;
        this.productThumbnail = productThumbnail;
        this.productIsBidComplete = productIsBidComplete;
        this.productBidList = productBidList;
    }
    @Override
    public boolean equals(Object obj) {
        Product otherProduct = (Product) obj;
        return productNo.equals(otherProduct.productNo);
    }
}
