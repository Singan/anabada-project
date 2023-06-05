package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ChatMessageDto;
import com.anabada.dto.response_dto.ChatMessageResDto;
import com.anabada.dto.response_dto.ChatRoomDto;
import com.anabada.entity.ChatMessage;
import com.anabada.entity.ChatRoom;
import com.anabada.entity.Member;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.ChatRoomInterface;
import com.anabada.repository.ChatMessageRepository;
import com.anabada.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
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
                        .memberImage(prefix + chatRoom.getMemberImage())
                        .build()
        ).collect(Collectors.toList());
        return chatRoomDtos;
    }

    public List<ChatMessageResDto> findChatMessageList(Long successNo) {
        List<ChatMessageResDto> chatMessageDtos = chatMessageRepository.findChatRoomMessage(successNo)
                .stream().map(chat -> new ChatMessageResDto(
                        chat.getChatRoom().getMember().getMemberImage(),
                        chat.getChatRoom().getMember().getMemberImage(),
                        chat.getChatRoom().getMember().getMemberNo(),
                        chat.getMessage(),
                        chat.getCreateDateTime(),
                        chat.getChatRoom().getSuccessfulBid().getSuccessBidProductNo()
                )).collect(Collectors.toList());
        return chatMessageDtos;
    }

    public ChatMessageResDto receiveMessage(MemberDetailDTO memberDetailDTO, ChatMessageDto chatMessageDto) {
        Member member = Member.builder().memberNo(memberDetailDTO.getNo()).build();
        SuccessfulBid successfulBid = SuccessfulBid.builder().successBidProductNo(chatMessageDto.getSuccessNo()).build();
        ChatRoom chatRoom = chatRoomRepository.findChatRoomBySuccessfulBidAndMember(successfulBid, member);
        LocalDateTime now = LocalDateTime.now();
        ChatMessage ch = ChatMessage
                .builder()
                .chatRoom(chatRoom)
                .message(chatMessageDto.getMessage())
                .createDateTime(now)
                .build();
        chatMessageRepository.save(ch);
        return new ChatMessageResDto(chatMessageDto,now, memberDetailDTO.getNo());
    }
}
