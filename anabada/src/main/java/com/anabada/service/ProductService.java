package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindAllDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Long productSave(ProductInsertDto productInsertDto,@AuthenticationPrincipal MemberDetailDTO memberDetailDTO){
        Product product = productInsertDto.getProduct(memberDetailDTO);
        productRepository.save(product);
        return product.getProductNo();
    };

    // product 세부 정보
    public ProductFindOneDto findOneProduct(Long productNo) {
        Product product = productRepository.findById(productNo).get();

        ProductFindOneDto productFindOneDto = ProductFindOneDto.builder()
                .productNo(product.getProductNo())
                .productPrice(product.getProductPrice())
                .productName(product.getProductName())
                .productDetail(product.getProductDetail())
                .productUseDate(product.getProductUseDate())
                .memberName(product.getMember().getMemberName())
                .build();

        return productFindOneDto;
    }

    // 모든 product
    public List<ProductFindAllDto> getProducts() {
        List<Product> products = productRepository.findAll();
        ArrayList<ProductFindAllDto> productDtoList = new ArrayList<>();

        for (Product product : products) {
            ProductFindAllDto productFindAllDto = ProductFindAllDto.builder()
                    .productNo(product.getProductNo())
                    .productName(product.getProductName())
                    .memberName(product.getMember().getMemberName())
                    .price(product.getProductPrice())
                    .build();
            productDtoList.add(productFindAllDto);
        }
        return productDtoList;
    }
}
