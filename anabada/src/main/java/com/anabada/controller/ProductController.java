package com.anabada.controller;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindAllDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import com.anabada.service.ProductImageService;
import com.anabada.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductImageService imageService;
    @PostMapping
    @Operation(description = "상품등록")
    public Long productInsert(ProductInsertDto productInsertDto, @AuthenticationPrincipal MemberDetailDTO principal){
        //스레드에 MemberDetail을 넣는다.
        Long productNo = productService.productSave(productInsertDto,principal);
        imageService.productImageSave(productNo,productInsertDto.getProductImages());

        return productNo;
    }

    @GetMapping
    @Operation(description = "상품 상세 조회") // 필수
    /*
       1. productNo를 넘겨받음 우리가 보여줄 값은 product 전체의 값이 아님 ProductFindOneDto가 우리가 줘야할 부분
       2. productNo를 service 로직으로 넘겨서 ProductFindOneDto로 만들어주어야한다.
       3. service 로직으로 넘겼으면 ProductRepository에서 productNo(pk)를 가지고 해당 Product를 가져와야 한다.
       4. ProductFindOneDto를 builder.build()를 통해 만들고 반환해준다.
     */
    public ProductFindOneDto productFindOne(@RequestParam Long productNo) {
        return productService.findProduct(productNo);
    }

    // 요구사항 분석에 맞춰 보여주는 값 주기 order, all, bid

    // 상품 리스트
    @GetMapping("/list")
    @Operation(description = "상품 전체 조회")
    public ResultList<String,List<ProductFindAllDto>> productList(
            @PageableDefault(sort = "id", size = 3, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        ResultList<String,List<ProductFindAllDto>> result = productService.findProductList(pageable);
        return result;
    }
}

