package com.anabada.controller;


import com.anabada.dto.MessageVo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic")
    public MessageVo sendMessage(@Payload MessageVo messageVo, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("왔다 채팅");
        String userName = messageVo.getUserName();
        String message = messageVo.getMessage();


        return messageVo;
    }
}