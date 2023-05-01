package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "socket")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocketTable {

    @Id
    private String socketRoomId;

    @OneToMany(mappedBy = "socketTable")
    private List<SocketRelation> socketRelationList;

    @Builder
    public SocketTable(String socketRoomId) {
        this.socketRoomId = socketRoomId;
    }
}
