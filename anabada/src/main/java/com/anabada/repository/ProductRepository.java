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
    @Query("select distinct p from Product p inner join fetch p.member " +
            "left join fetch p.productImageList " +
            "left join fetch p.currentBid" +
            " where p.productNo=:productNo")
    Product findByProductNo(@Param("productNo") Long productNo); // 프로덕트 상세

    @Query("select distinct p from Product p join fetch p.member left join fetch p.currentBid ")
    @BatchSize(size = 3)
    List<Product> findByProductAndMember(Pageable pageable); //메인에 나가는 목록 조회 조정할 예정

    @Query("select  p from Product p " +
            "left join fetch p.currentBid " +
            "where p.category.categoryNo = :categoryNo  and p.productIsBidComplete = false")
    List<Product> findProductByCategory(@Param("categoryNo") Long categoryNo); //상품 카테고리 조회
}
