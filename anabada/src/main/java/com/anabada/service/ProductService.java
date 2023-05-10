package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.*;
import com.anabada.entity.CurrentBid;
import com.anabada.entity.Product;
import com.anabada.entity.ProductImage;
import com.anabada.entity.ProductSocket;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.CurrentBidRepository;
import com.anabada.repository.ProductImageRepository;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final FileProcessor fileProcessor;
    @Value("${s3.bucket.endpoint}")
    private String s3EndPoint;
    @Transactional
    public ProductInsertResDto productSave(ProductInsertDto productInsertDto, MemberDetailDTO memberDetailDTO) {
        List<MultipartFile> multipartFiles = productInsertDto.getProductImages();
        if (multipartFiles.isEmpty()) {
            throw new RuntimeException("하나이상의 사진을 등록해야 합니다.");
        }
        String thumbnail = fileProcessor.fileSave(multipartFiles.get(0), "product");
        Product product = productInsertDto.getProduct(memberDetailDTO, thumbnail);
        productRepository.save(product);
        if (multipartFiles.size() > 1) {
            multipartFiles.remove(0);
            List<String> images = fileProcessor.fileSave(multipartFiles, "product", product.getProductNo());

            List<ProductImage> productImageList = images.stream().map(s -> {
                return ProductImage.builder().imageAddr(s).product(product).build();
            }).collect(Collectors.toList());
            productImageRepository.saveAll(productImageList);
        }



        return new ProductInsertResDto(product.getProductNo(), product.getProductSocket().getProductSocketNo());
    }

    // product 세부 정보
    @Transactional
    public ProductFindOneDto findProduct(Long productNo) {
        Product product = productRepository.findByProductNo(productNo);

        if (product != null) {
            product.upProductVisit();
            ProductFindOneDto productFindOneDto = new ProductFindOneDto(product ,s3EndPoint);
            return productFindOneDto;
        }
        throw new RuntimeException("에러");
    }

    // 모든 product
    //메인페이지 하단 부분
    public ResultList<List<ProductFindAllDto>> findAllByProductImageListIsNotEmpty(Pageable pageable) {
        List<Product> productList = productRepository.findByProductAndMember(pageable);

        List<ProductFindAllDto> productDtoList =
                productList.stream().map(product -> new ProductFindAllDto(product,s3EndPoint)).collect(Collectors.toList());

        ResultList<List<ProductFindAllDto>> result = new ResultList<>(productDtoList);
        return result;
    }

    // 카테고리별 product 리스트
    public ArrayList<FindProductToCategoryDto> findProductToCategory(Long categoryNo) {
        ArrayList<FindProductToCategoryDto> findProductToCategoryDtoArrayList = new ArrayList<>();
        System.out.println("findProductToCategory -- 시작");
        List<Product> productByCategoryList = productRepository.findProductByCategory();
        System.out.println("findProductToCategory -- 쿼리실행");
        productByCategoryList.forEach(product -> {
            FindProductToCategoryDto findProductToCategoryDto = new FindProductToCategoryDto(product, s3EndPoint);
            findProductToCategoryDtoArrayList.add(findProductToCategoryDto);
        });

        return findProductToCategoryDtoArrayList;
    }
}
