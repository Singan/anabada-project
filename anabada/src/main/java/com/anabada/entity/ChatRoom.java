package com.anabada.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Chat_Room",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "memberNoAndSuccessBid",
                        columnNames = {"success_no", "member_no"}
                )
        }
)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomNo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "success_no")
    private SuccessfulBid successfulBid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_no")
    private Member member;
    @OneToMany(mappedBy = "chatRoom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @Builder
    public ChatRoom(Long chatRoomNo, SuccessfulBid successfulBid, Member member, List<ChatMessage> chatMessageList) {
        this.chatRoomNo = chatRoomNo;
        this.successfulBid = successfulBid;
        this.member = member;
        this.chatMessageList = chatMessageList;
    }
}
