package com.anabada.service;

import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.SocketRelation;
import com.anabada.entity.ProductSocket;
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

    public Long socketRoomCreate() {
        ProductSocket productSocket = new ProductSocket();
        //socketRepository.save(productSocket);
        return productSocket.getProductSocketNo();
    }

    public List<Long> memberSocketList(Member member) {
        List<SocketRelation> socketList = socketRelationRepository.findSocketRelationByMember(member);

        List<Long> memberSocketList = socketList.stream().map(socketRelation -> {
                    return socketRelation.getProductSocket().getProductSocketNo();
                }
        ).collect(Collectors.toList());

        return memberSocketList;
    }
}
