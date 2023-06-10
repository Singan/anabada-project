package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.*;
import com.anabada.entity.Product;
import com.anabada.entity.ProductImage;
import com.anabada.entity.nativeQuery.ProductFindOneInterface;
import com.anabada.dto.response_dto.SalesListSelectDto;
import com.anabada.entity.nativeQuery.SalesListSelectInterface;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.BidRepository;
import com.anabada.repository.ProductImageRepository;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
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


        return new ProductInsertResDto(product.getProductNo());
    }

    // product 세부 정보
    @Transactional
    public ProductFindOneDto findProduct(Long productNo) {

        ProductFindOneInterface product = productRepository.findProductDetail(productNo);

        if (product != null) {
            productRepository.upProductVisit(productNo);


            List<ProductImage> piList = productImageRepository.findByProductNo(productNo);

            ProductFindOneDto productFindOneDto = new ProductFindOneDto(product, piList,s3EndPoint);
            return productFindOneDto;
        }
        throw new RuntimeException("에러");
    }


    //메인페이지 하단 부분
    public ResultList<List<ProductFindAllDto>> productMainFind(Pageable pageable) {
        List<Product> productList = productRepository.findByProductAndMember(pageable);

        List<ProductFindAllDto> productDtoList =
                productList.stream().map(product -> new ProductFindAllDto(product, s3EndPoint)).collect(Collectors.toList());

        ResultList<List<ProductFindAllDto>> result = new ResultList<>(productDtoList);
        return result;
    }

    // 카테고리별 product 리스트
    public ResultList<ArrayList<FindProductToCategoryDto>> findProductToCategory(Long categoryNo,Pageable pageable) {
        ArrayList<FindProductToCategoryDto> findProductToCategoryDtoArrayList = new ArrayList<>();
        Page<Product> productPage = productRepository.findProductByCategory(categoryNo,pageable);
        productPage.getContent().forEach(product -> {
            FindProductToCategoryDto findProductToCategoryDto = new FindProductToCategoryDto(product, s3EndPoint);
            findProductToCategoryDtoArrayList.add(findProductToCategoryDto);
        });

        return new ResultList(productPage.getTotalPages(),findProductToCategoryDtoArrayList);
    }

    // 판매 내역
    public ResultList<List<SalesListSelectDto>> findSales(MemberDetailDTO memberDetailDTO,Pageable pageable) {
        Page<SalesListSelectInterface> productList = productRepository.
                findProductListWithBid(memberDetailDTO.getNo(),pageable);
        List<SalesListSelectDto> productDtoList = productList.stream().map(s ->
                SalesListSelectDto.builder()
                        .memberNo(s.getMemberNo())
                        .productThumbnail(s3EndPoint+s.getProductThumbnail())
                        .bidTime(s.getBidTime())
                        .createDateTime(s.getCreateDateTime())
                        .memberName(s.getMemberName())
                        .bidPrice(s.getBidPrice())
                        .productPrice(s.getProductPrice())
                        .productName(s.getProductName())
                        .productNo(s.getProductNo())
                        .isBidComplete(s.getProductIsBidComplete())
                        .build()
                ).collect(Collectors.toList());
        return new ResultList<>(productList.getTotalPages(),productDtoList);
    }
}
