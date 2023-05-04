package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "socket_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class SocketRelation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long socketRelationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_socket_no")
    private ProductSocket productSocket;

    @Builder
    public SocketRelation(Long socketRelationNo, Member member, ProductSocket productSocket) {
        this.socketRelationNo = socketRelationNo;
        this.member = member;
        this.productSocket = productSocket;
    }
}
