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
    @JoinColumn(nullable = false)
    private ChatRoom chatRoom ;

    @ManyToOne
    @JoinColumn
    private Member member;

    private LocalDateTime createDateTime;

}