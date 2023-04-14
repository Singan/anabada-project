package com.anabada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuccessBidController {

    private final SuccessBidService successBidService;

    // 낙찰 페이지. 입찰 번호를 받음
//    @PostMapping("/success")
//    public SuccessBidDto successBid(Long bidNo) {
//        return successBidService.getSuccessBid(bidNo);
//    }
}
