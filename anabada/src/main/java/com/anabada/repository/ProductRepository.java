package com.anabada.repository;

import com.anabada.entity.Product;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p join fetch p.member where p.productNo=:id")
    Optional<Product> findById(@Param("id") Long id);

    @Query("select distinct p from Product p join fetch p.productImageList join fetch p.member")
    @BatchSize(size = 3)
    List<Product> findAllByProductImageListIsNotEmpty(Pageable pageable);

    @Query("select p from Product p join p.category c where c.categoryNo = :categoryNo")
    List<Product> findProductByCategory(@Param("categoryNo") Long categoryNo);
}
