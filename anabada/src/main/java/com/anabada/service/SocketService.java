package com.anabada.service;

import com.anabada.entity.Member;
import com.anabada.entity.SocketRelation;
import com.anabada.entity.SocketTable;
import com.anabada.repository.SocketRelationRepository;
import com.anabada.repository.SocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocketService {
    private final SocketRepository socketRepository;
    private final SocketRelationRepository socketRelationRepository;

    public void socketRoomCreate(String socketRoomType, Long no, Member member) {
        SocketTable socketTable = new SocketTable(socketRoomType + no);
        String roomId = socketRepository.save(socketTable).getSocketRoomId();

        SocketRelation socketRelation = SocketRelation
                .builder()
                .socketTable(socketTable)
                .member(member)
                .build();
        member.addSocketList(socketRelation);;
        socketRelationRepository.save(socketRelation);
    }

    public List<String> memberSocketList(Member member) {
        List<SocketRelation> socketList = socketRelationRepository.findSocketRelationByMember(member);

        List<String> memberSocketList = socketList.stream().map(socketRelation -> {
                    return socketRelation.getSocketTable().getSocketRoomId();
                }
        ).collect(Collectors.toList());

        return memberSocketList;
    }
}
