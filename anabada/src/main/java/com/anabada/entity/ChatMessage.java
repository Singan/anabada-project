package com.anabada.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "chat_room_no")
    private ChatRoom chatRoom ;

    private String message;

    private LocalDateTime createDateTime;

}
