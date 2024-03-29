package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.repository.MemberRepository;
import com.anabada.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void productFindName() {
        ProductInsertDto productInsertDto = new ProductInsertDto();
        productInsertDto.setName("아이폰");
        productInsertDto.setDetail("아이폰 팝니다");
        productInsertDto.setPrice(1400000);
        Member member = memberRepository.findById(1L).get();
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO(member);

        productService.productSave(productInsertDto, memberDetailDTO);

        Optional<Product> product = productRepository.findById(1L);

        assertThat(productInsertDto.getName()).isEqualTo(product.get().getProductName());
        assertThat(productInsertDto.getDetail()).isEqualTo(product.get().getProductDetail());
        assertThat(productInsertDto.getPrice()).isEqualTo(product.get().getProductPrice());
    }

}