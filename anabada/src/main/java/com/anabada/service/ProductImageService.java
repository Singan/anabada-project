package com.anabada.service;

import com.anabada.entity.Product;
import com.anabada.entity.ProductImage;
import com.anabada.etc.FileProcessor;
import com.anabada.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository productRepository;
    private final FileProcessor fileProcessor;

    public void productImageSave(Long no,MultipartFile... multipartFiles){
        if(multipartFiles == null)
            return;
        for (MultipartFile multipartFile : multipartFiles) {
            if(multipartFile == null || multipartFile.isEmpty())
                return;
        }


        List<String> imagePathList = fileProcessor.fileSave(multipartFiles);
        Product product = Product.builder().productNo(no).build();
        List<ProductImage> productImageList = imagePathList.stream().map
                (s -> ProductImage.
                        builder()
                        .product(product)
                        .build()
                ).collect(Collectors.toList());
        productRepository.saveAll(productImageList);
    }
}
