package com.anabada.controller;

import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.service.SuccessBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuccessBidController {

    private final SuccessBidService successBidService;

    // 낙찰 페이지. 입찰 번호를 받음
    @PostMapping("/success")
    public SuccessBidDto successBid(Long bidNo) {
        return successBidService.getSuccessBid(bidNo);
    }
}
