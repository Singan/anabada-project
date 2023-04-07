package com.anabada.service;

import com.anabada.dto.request_dto.ProductInsertDto;
import com.anabada.dto.response_dto.ProductFindOneDto;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Long productSave(ProductInsertDto productInsertDto){
        Product product = productInsertDto.getProduct();
        productRepository.save(product);
        return product.getProductNo();
    };

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
}
