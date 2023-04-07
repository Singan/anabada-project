package com.anabada.controller;

import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import com.anabada.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(description = "상품등록")
    public Long productInsert(@RequestBody ProductInsertDto productInsertDto){
        Product product = productInsertDto.getProduct();
        return product.getProductNo();
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
        return productService.findOneProduct(productNo);
    }
}
