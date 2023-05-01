package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MessageVo;
import lombok.RequiredArgsConstructor;
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

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/receive")
    @SendTo("/send")
    public void sendMessage(@Payload MessageVo chatMessage, Authentication authentication, StompHeaderAccessor accessor) {

        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) authentication.getPrincipal();



            chatMessage.setMemberId(memberDetailDTO.getMemberName());
            simpMessagingTemplate.convertAndSend("/send",chatMessage);

    }
}