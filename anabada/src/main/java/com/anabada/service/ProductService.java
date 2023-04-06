package com.anabada.service;

import com.anabada.dto.ProductInsertDto;
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

}
