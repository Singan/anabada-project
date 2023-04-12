package com.anabada.controller;

import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.entity.Bid;
import com.anabada.service.BidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    @PostMapping
    @Operation(description = "입찰 정보 등록")
    public Long bidInsert(@RequestBody BidInsertDto bidinsertDto) {
        Bid bid = bidinsertDto.getBid();
        bidService.bidSave(bidinsertDto);
        // pk를 리턴해주는 이유?
        return bid.getBidNo();
    }

}
