package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.MessageVo;
import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.request_dto.ChatMessageDto;
import com.anabada.dto.request_dto.ChatStartDto;
import com.anabada.dto.response_dto.ChatMessageResDto;
import com.anabada.dto.response_dto.ChatRoomDto;
import com.anabada.entity.ChatMessage;
import com.anabada.entity.SuccessfulBid;
import com.anabada.service.ChatService;
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
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SuccessBidService successBidService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @PutMapping("/chat/start")
    @Operation(description ="채팅 시작")
    public void chatStart(@RequestBody ChatStartDto chatStartDto ,
                          @AuthenticationPrincipal MemberDetailDTO memberDetailDTO){
        successBidService.startChat(chatStartDto.getSuccessBidNo(),memberDetailDTO);
    }
    @GetMapping("/chat/list")
    @Operation(description = "내 채팅 목록")
    public List<ChatRoomDto> myChatList(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO){
        return chatService.findStatusChat(memberDetailDTO);
    }

    @MessageMapping("/chat")
    public void receiveMessage(@Payload ChatMessageDto chatMessageDto, Authentication authentication){
        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) authentication.getPrincipal();

        ChatMessageResDto ch = chatService.receiveMessage(memberDetailDTO,chatMessageDto);
        simpMessagingTemplate.convertAndSend("/chat/"+chatMessageDto.getSuccessNo(),ch);


    }
    @GetMapping("/chat/content")
    @Operation(description = "채팅방 채팅 목록")
    public List<ChatMessageResDto> chatRoomMessage(@RequestParam Long successNo){
        return chatService.findChatMessageList(successNo);
    }
}