package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.MypageConfirmDto;
import com.anabada.dto.request_dto.PayDto;
import com.anabada.dto.response_dto.*;
import com.anabada.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/sales")
    @Operation(description = "내 판매내역")
    public ResultList<List<SalesListSelectDto>> findSales(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return productService.findSales(memberDetailDTO);
    }

    @PostMapping("/bidDetail")
    @Operation(description = "내 입찰내역")
    public ResultList<List<FindBiddingDetailDto>> findBiddingDetail(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return bidService.findBiddingDetail(memberDetailDTO);
    }

    @GetMapping("/buys")
    @Operation(description = "내 구매내역")
    public ResultList<List<MyBuyDto>> findBuyList(@AuthenticationPrincipal MemberDetailDTO memberDetailDTO) {
        return myPageService.myBuyList(memberDetailDTO);
    }
}
