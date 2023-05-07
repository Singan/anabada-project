package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.*;
import com.anabada.entity.Product;
import com.anabada.entity.ProductSocket;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProductInsertResDto productSave(ProductInsertDto productInsertDto,MemberDetailDTO memberDetailDTO){
        //ProductSocket productSocket = ProductSocket.builder().productSocketNo(socketNo).build();
        Product product = productInsertDto.getProduct(memberDetailDTO);

        productRepository.save(product);
        return new ProductInsertResDto(product.getProductNo(), product.getProductSocket().getProductSocketNo());
    }

    // product 세부 정보
    public ProductFindOneDto findProduct(Long productNo) {
        Optional<Product> productOptional = productRepository.findById(productNo);

        if (!productOptional.isEmpty()) {
            Product product = productOptional.get();
            product.upProductVisit();
            ProductFindOneDto productFindOneDto = new ProductFindOneDto(product);

            return productFindOneDto;
        }
        throw new  RuntimeException("에러");
    }

    // 모든 product
    //메인페이지 하단 부분
    public ResultList<String,List<ProductFindAllDto>> findAllByProductImageListIsNotEmpty(Pageable pageable) {
        List<Product> productList = productRepository.findAllByProductImageListIsNotEmpty(pageable);

        List<ProductFindAllDto> productDtoList =
                productList.stream().map(product -> new ProductFindAllDto(product)).collect(Collectors.toList());

        ResultList<String,List<ProductFindAllDto>> result = new ResultList<>("메인화면",productDtoList);
        return result;
    }

    // 카테고리별 product 리스트
    public ArrayList<FindProductToCategoryDto> findProductToCategory(Long categoryNo) {
        ArrayList<FindProductToCategoryDto> findProductToCategoryDtoArrayList = new ArrayList<>();
        List<Product> productByCategoryList = productRepository.findProductByCategory(categoryNo);

        productByCategoryList.forEach(products -> {
            FindProductToCategoryDto findProductToCategoryDto = new FindProductToCategoryDto(products);
            findProductToCategoryDtoArrayList.add(findProductToCategoryDto);
        });

        return findProductToCategoryDtoArrayList;
    }
}
