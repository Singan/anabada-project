package com.anabada.service;

import com.anabada.dto.ProductInsertDto;
import com.anabada.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        assertThat(productInsertDto.getName()).isEqualTo("아이폰");
        assertThat(productInsertDto.getDetail()).isEqualTo("아이폰 팝니다");
        assertThat(productInsertDto.getPrice()).isEqualTo(1400000);
    }

}