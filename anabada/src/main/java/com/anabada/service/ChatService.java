package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.ChatRoomDto;
import com.anabada.entity.ChatMessage;
import com.anabada.entity.nativeQuery.ChatRoomInterface;
import com.anabada.repository.ChatMessageRepository;
import com.anabada.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${s3.bucket.endpoint}")
    private String prefix;
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatRoomDto> findStatusChat(MemberDetailDTO memberDetailDTO) {
        List<ChatRoomInterface> chatRoomList = chatRoomRepository.findChatRoomByMember(memberDetailDTO.getNo());
        List<ChatRoomDto> chatRoomDtos = chatRoomList.stream().map(
                chatRoom -> ChatRoomDto.builder()
                        .chatRoomNo(chatRoom.getChatRoomNo())
                        .memberNo(chatRoom.getMemberNo())
                        .successNo(chatRoom.getSuccessNo())
                        .memberName(chatRoom.getMemberName())
                        .memberImage(prefix+chatRoom.getMemberImage())
                        .build()
        ).collect(Collectors.toList());
        return chatRoomDtos;
    }
    public List<ChatMessage> findChatMessageList(Long successNo){
        return null;
    }
}
