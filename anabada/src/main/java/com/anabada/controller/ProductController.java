package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.*;
import com.anabada.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(description = "상품등록")
    public ProductInsertResDto productInsert(ProductInsertDto productInsertDto, @AuthenticationPrincipal MemberDetailDTO principal) {
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
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        ResultList<List<ProductFindAllDto>> result = productService.productMainFind(pageable);
        return result;
    }
    // 상품 리스트

    @GetMapping("/list")
    @Operation(description = "카테고리별 상품 리스트")
    public ResultList<ArrayList<FindProductToCategoryDto>> findProductToCategoryDto(
            Long categoryNo,
            @PageableDefault(sort = "createDateTime",direction = Sort.Direction.DESC) Pageable pageable
    ) {
        ResultList<ArrayList<FindProductToCategoryDto>>  findProductToCategoryDtoArrayList =
                productService.findProductToCategory(categoryNo,pageable);
        return findProductToCategoryDtoArrayList;
    }
}

