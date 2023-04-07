package com.anabada.controller;

import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping("/product")
    @Operation(description = "상품등록")
    public Long productInsert(@RequestBody ProductInsertDto productInsertDto){
        Product product = productInsertDto.getProduct();
        productRepository.save(product);
        return product.getProductNo();
    }

    @GetMapping("/product")
    @Operation(description = "상품 상세 조회")
    public ProductFindOneDto productFindOne(@RequestParam Long productNo){
        Product product = productRepository.findById(productNo).get();

        ProductFindOneDto resProduct = ProductFindOneDto.builder()
                .productName(product.getProductName())
                .productDetail(product.getProductDetail())
                .productNo(product.getProductNo())
                .memberName(product.getMember().getMemberName())
                .productPrice(product.getProductPrice())
                .build();
        return resProduct;
    }
}
