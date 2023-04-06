package com.anabada.controller;

import com.anabada.dto.ProductInsertDto;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping("/product")
    public Product productInsert(@RequestBody ProductInsertDto productInsertDto){
        Product product = productInsertDto.getProduct();
        return productRepository.save(product);

    }

}
