package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindAllDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Long productSave(ProductInsertDto productInsertDto,MemberDetailDTO memberDetailDTO){
        Product product = productInsertDto.getProduct(memberDetailDTO);

        productRepository.save(product);
        return product.getProductNo();
    }

    // product 세부 정보
    public ProductFindOneDto findProduct(Long productNo) {
        Optional<Product> product = productRepository.findById(productNo);

        if (!product.isEmpty()) {

            ProductFindOneDto productFindOneDto = new ProductFindOneDto(product.get());

            return productFindOneDto;
        }
        throw new  RuntimeException("에러");
    }

    // 모든 product
    public ResultList<String,List<ProductFindAllDto>> findProductList() {
        List<Product> productList = productRepository.findAll();

        List<ProductFindAllDto> productDtoList =
                productList.stream().map(product -> new ProductFindAllDto(product)).collect(Collectors.toList());

        ResultList<String,List<ProductFindAllDto>> result = new ResultList<>("전자",productDtoList);
        return result;
    }
}
