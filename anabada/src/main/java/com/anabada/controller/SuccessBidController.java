package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ChatStartDto;
import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.service.SuccessBidService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/success")
public class SuccessBidController {
    private final SuccessBidService successBidService;

    @GetMapping
    @Operation(description = "낙찰 결과")
    public SuccessBidDto successBidDto(@RequestParam Long successBidNo,@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return successBidService.successBidDto(successBidNo,memberDetailDTO);
    }

}
