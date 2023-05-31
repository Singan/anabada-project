package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MypageConfirmDto;
import com.anabada.dto.request_dto.PayDto;
import com.anabada.dto.response_dto.*;
import com.anabada.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final MemberService memberService;
    private final PayService payService;
    private final ProductService productService;
    private final BidService bidService;
    private final MyPageService myPageService;

    @GetMapping
    public MyPageFindDto myPageFindDto(@AuthenticationPrincipal MemberDetailDTO principal) {
        return memberService.myPage(principal);
    }

    @PostMapping("/pay")
    public void pay(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO, @RequestBody PayDto payDto) {
        payService.addMoney(memberDetailDTO, payDto);
    }

    @PostMapping("/confirm")
    public boolean mypageConfirm(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO, @RequestBody MypageConfirmDto mypageConfirmDto) {
        return memberService.confirmPassword(memberDetailDTO, mypageConfirmDto);
    }

    @GetMapping("/sales")
    @Operation(description = "내 판매내역")
    public ResultList<List<SalesListSelectDto>> findSales(
            @AuthenticationPrincipal MemberDetailDTO memberDetailDTO,
            @PageableDefault(sort = "productPrice",direction = Sort.Direction.DESC) Pageable pageable

    ) {
        return productService.findSales(memberDetailDTO,pageable);
    }

    @GetMapping("/bidDetail")
    @Operation(description = "내 입찰내역")
    public ResultList<List<MyBidDto>> findBiddingDetail(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO, Pageable pageable) {
        return myPageService.findBiddingDetail(memberDetailDTO, pageable);
    }

    @GetMapping("/buys")
    @Operation(description = "내 구매내역")
    public ResultList<List<MyBuyDto>> findBuyList(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO,Pageable pageable) {
        return myPageService.myBuyList(memberDetailDTO,pageable);
    }
}
