package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.response_dto.BidInfoFindDto;
import com.anabada.dto.response_dto.BidInsertResponseDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Bid;
import com.anabada.service.BidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/bid")
    @Operation(description = "입찰 정보 등록")
    public void bidInsert(@Payload BidInsertDto bidInsertDto, Authentication authentication) {
        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) authentication.getPrincipal();
        System.out.println("입찰 정보 등록 실행");
        BidInsertResponseDto bidRes = bidService.bidSave(bidInsertDto, memberDetailDTO);

        simpMessagingTemplate.convertAndSend(
                "/product/" + bidInsertDto.getProductNo(), bidRes);

        simpMessagingTemplate.convertAndSend(
                "/product/myProduct/" + bidInsertDto.getProductNo(), bidRes);
    }

    @GetMapping
    @Operation(description = "입찰 정보 보내기")
    @ResponseBody
    public ResultList<List<BidInfoFindDto>> bidInfo(@RequestParam Long productNo) {
        return bidService.findBidList(productNo);
    }

}
