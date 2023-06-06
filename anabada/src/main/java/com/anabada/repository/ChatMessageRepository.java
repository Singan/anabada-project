package com.anabada.repository;

import com.anabada.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage , Long> {

    @Query("select cm from ChatMessage cm " +
            "join fetch cm.chatRoom cr " +
            "join fetch cr.member " +
            "where cr.successfulBid.successBidProductNo = :successNo " +
            "order by cm.createDateTime asc")
    List<ChatMessage> findChatRoomMessage(@Param("successNo") Long successNo);

}
