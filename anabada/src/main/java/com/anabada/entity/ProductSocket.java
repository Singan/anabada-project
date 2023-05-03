package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product_socket")
@Getter
@NoArgsConstructor
public class ProductSocket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSocketNo;


    @OneToMany(mappedBy = "productSocket")
    private List<SocketRelation> socketRelationList;

    @OneToOne(mappedBy = "productSocket")
    private Product product;
    @Builder
    public ProductSocket(Long productSocketNo,List<SocketRelation> socketRelationList,Product product) {
        this.productSocketNo = productSocketNo;
        this.socketRelationList = socketRelationList;
        this.product = product;
    }
}
