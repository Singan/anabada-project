package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.*;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import com.anabada.service.ProductService;
import com.anabada.service.SocketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final SocketService socketService;

    @PostMapping
    @Operation(description = "상품등록")
    public ProductInsertResDto productInsert(ProductInsertDto productInsertDto, @AuthenticationPrincipal MemberDetailDTO principal) {
        //스레드에 MemberDetail을 넣는다.
        ProductInsertResDto productInsertResDto = productService.productSave(productInsertDto, principal);

        return productInsertResDto;
    }

    @GetMapping
    @Operation(description = "상품 상세 조회") // 필수
    public ProductFindOneDto productFindOne(@RequestParam Long productNo) {
        return productService.findProduct(productNo);
    }

    // 요구사항 분석에 맞춰 보여주는 값 주기 order, all, bid

    @GetMapping("/main")
    @Operation(description = "메인페이지 하단 상품 조회")
    public ResultList<List<ProductFindAllDto>> productList(
            @PageableDefault(sort = "id", size = 4, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        ResultList<List<ProductFindAllDto>> result = productService.productMainFind(pageable);
        return result;
    }
    // 상품 리스트

    @GetMapping("/list")
    @Operation(description = "카테고리별 상품 리스트")
    public ArrayList<FindProductToCategoryDto> findProductToCategoryDto(
            Long categoryNo,
            @PageableDefault(sort = "create_date_time",direction = Sort.Direction.DESC) Pageable pageable
    ) {
        ArrayList<FindProductToCategoryDto> findProductToCategoryDtoArrayList =
                productService.findProductToCategory(categoryNo,pageable);
        return findProductToCategoryDtoArrayList;
    }
}

