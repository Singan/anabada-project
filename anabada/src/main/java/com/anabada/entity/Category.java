package com.anabada.entity;

import lombok.AccessLevel;
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

    @Column
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}