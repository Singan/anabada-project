package com.anabada.controller;

import com.anabada.dto.ProductInsertDto;
import com.anabada.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/")
    public Long productSave(@RequestBody ProductInsertDto productInsertDto){
        System.out.printf("하이");

        return productService.productSave(productInsertDto);
    }

}
