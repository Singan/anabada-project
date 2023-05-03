package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MessageVo;
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
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/receive")
    public void sendMessage(@Payload MessageVo chatMessage, Authentication authentication, StompHeaderAccessor accessor) {
        System.out.println("SendMessage");

        MemberDetailDTO sender = (MemberDetailDTO) authentication.getPrincipal();
        Authentication receiveAuthentication = (Authentication) accessor.getUser();
        MemberDetailDTO receiver = (MemberDetailDTO) receiveAuthentication.getPrincipal();
        System.out.println("receiveMessage");
        chatMessage.setMemberId(sender.getUsername());
        if (receiver.getNo() != sender.getNo()) {

        }
        simpMessagingTemplate.convertAndSend("/send",chatMessage);
        simpMessagingTemplate.convertAndSend("/send2",chatMessage);

    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}