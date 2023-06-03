package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomNo;
    @OneToOne
    @JoinColumn(nullable = false,name = "success_no")
    private SuccessfulBid successfulBid;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @Builder
    public ChatRoom(Long chatRoomNo, SuccessfulBid successfulBid, List<ChatMessage> chatMessageList) {
        this.chatRoomNo = chatRoomNo;
        this.successfulBid = successfulBid;
        this.chatMessageList = chatMessageList;
    }
}
