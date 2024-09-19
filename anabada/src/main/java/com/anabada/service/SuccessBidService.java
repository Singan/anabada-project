package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.ChatRoomDto;
import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.entity.ChatRoom;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.ChatRoomInterface;
import com.anabada.etc.Status;
import com.anabada.repository.ChatRoomRepository;
import com.anabada.repository.SuccessBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuccessBidService {
    private final SuccessBidRepository successBidRepository;
    @Value("${s3.bucket.endpoint}")
    private String s3EndPoint;
    private final ChatRoomRepository chatRoomRepository;
    public SuccessBidDto successBidDto(Long successBidNo,MemberDetailDTO memberDetailDTO) {
        Long memberNo = memberDetailDTO.getNo();
        SuccessfulBid successfulBid = successBidRepository.findSuccessfulBid(successBidNo);
        if(successfulBid.getBid().getMember().getMemberNo() != memberNo&&
                successfulBid.getBid().getProduct().getMember().getMemberNo()!=memberNo){
            throw new RuntimeException("낙찰 또는 판매와 관련없는 회원은 해당 페이지에 접근할 수 없습니다.");
        }
        return new SuccessBidDto(successfulBid, s3EndPoint);
    }

    @Transactional
    public void startChat(Long successBidNo,MemberDetailDTO memberDetailDTO){
        SuccessfulBid s = successBidRepository.findSuccessfulBid(successBidNo);
        if(s.getStatus() == Status.채팅){
            return;
        }
        s.setStatus(Status.채팅);
        ChatRoom chatRoom1 = ChatRoom.
                builder().
                successfulBid(s).
                member(s.getBid().getMember()).
                build();
        ChatRoom chatRoom2 = ChatRoom.
                builder().
                successfulBid(s).
                member(s.getBid().getProduct().getMember()).
                build();
        chatRoomRepository.saveAll(Arrays.asList(chatRoom2,chatRoom1));
    }

}
