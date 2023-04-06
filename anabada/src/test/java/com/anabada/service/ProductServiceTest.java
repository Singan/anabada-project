package com.anabada.service;

import com.anabada.dto.ProductInsertDto;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void productFindName() {
        ProductInsertDto productInsertDto = new ProductInsertDto();
        productInsertDto.setName("아이폰");
        productInsertDto.setDetail("아이폰 팝니다");
        productInsertDto.setPrice(1400000);

        productService.productSave(productInsertDto);

        Optional<Product> product = productRepository.findById(1L);

        assertThat(productInsertDto.getName()).isEqualTo(product.get().getProductName());
        assertThat(productInsertDto.getDetail()).isEqualTo(product.get().getProductDetail());
        assertThat(productInsertDto.getPrice()).isEqualTo(product.get().getProductPrice());
    }

}