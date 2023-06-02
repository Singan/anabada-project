package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MessageVo;
import com.anabada.dto.request_dto.ChatStartDto;
import com.anabada.entity.SuccessfulBid;
import com.anabada.service.SuccessBidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private SuccessBidService successBidService;

    @MessageMapping("/receive")
    public void sendMessage(@Payload MessageVo chatMessage, Authentication authentication, StompHeaderAccessor accessor) {
        System.out.println("SendMessage");

        MemberDetailDTO sender = (MemberDetailDTO) authentication.getPrincipal();
        System.out.println("receiveMessage");
        chatMessage.setMemberName(sender.getUserNickname());
        chatMessage.setMemberNo(sender.getNo());
        System.out.println(sender.getUserNickname());
        simpMessagingTemplate.convertAndSend("/send",chatMessage); // 센드로 메시지를 쏴준다.
        simpMessagingTemplate.convertAndSend("/send2",chatMessage);

    }

    @PutMapping("/chat/start")
    @Operation(description ="채팅 시작")
    public void chatStart(@RequestBody ChatStartDto chatStartDto ,
                          @AuthenticationPrincipal MemberDetailDTO memberDetailDTO){
        System.out.println("아아");
        successBidService.startChat(chatStartDto.getSuccessBidNo());
    }
}