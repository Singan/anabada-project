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
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categoryNo;

    @Enumerated(EnumType.STRING)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Builder
    public Category(Long categoryNo, String categoryName, List<Product> productList) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.productList = productList;
    }
}