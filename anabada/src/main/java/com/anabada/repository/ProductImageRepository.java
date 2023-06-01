package com.anabada.repository;

import com.anabada.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query("select pi from ProductImage pi where pi.product.productNo =:productNo")
    List<ProductImage> findByProductNo(@Param("productNo") Long productNo);
}
