package com.anabada.controller;

import com.anabada.dto.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class ChatController {


    @MessageMapping("/receive")
    @SendTo("/send")
    public MessageVo sendMessage( MessageVo chatMessage) {
        System.out.println("챗 컨트롤러");
        System.out.println(chatMessage.getMessage());

        return chatMessage;
    }
}