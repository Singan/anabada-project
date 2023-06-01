package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.service.SuccessBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/success")
public class SuccessBidController {
    private final SuccessBidService successBidService;

    @GetMapping
    public SuccessBidDto successBidDto(@RequestParam Long successBidNo) {
        return successBidService.successBidDto(successBidNo);
    }
}
