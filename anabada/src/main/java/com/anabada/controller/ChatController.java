package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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


    @MessageMapping("/receive")
    @SendTo("/send")
    public MessageVo sendMessage(@Payload MessageVo chatMessage, Principal principal) {
        System.out.println(chatMessage.getMessage());

        Authentication authentication = (Authentication) principal;
        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) authentication.getPrincipal();
        chatMessage.setMemberId(memberDetailDTO.getMemberName());
        return chatMessage;
    }
}