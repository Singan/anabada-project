package com.anabada.controller;

import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.response_dto.BidInfoFindDto;
import com.anabada.entity.Bid;
import com.anabada.service.BidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    @PostMapping
    @Operation(description = "입찰 정보 등록")
    public Long bidInsert(@RequestBody BidInsertDto bidinsertDto) {
        return bidService.bidSave(bidinsertDto);
    }

    @GetMapping
    @Operation(description = "입찰 정보 보내기")
    public BidInfoFindDto bidInfo(@RequestParam Long bidNo) {
        BidInfoFindDto responseBidInfo = bidService.findBidInfo(bidNo);
        return responseBidInfo;
    }

}
