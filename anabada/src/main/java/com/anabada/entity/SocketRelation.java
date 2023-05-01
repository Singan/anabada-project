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
    @JoinColumn
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private SocketTable socketTable;

    @Builder
    public SocketRelation(Long socketRelationNo, Member member, SocketTable socketTable) {
        this.socketRelationNo = socketRelationNo;
        this.member = member;
        this.socketTable = socketTable;
    }
}
