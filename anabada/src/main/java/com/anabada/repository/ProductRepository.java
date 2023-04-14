package com.anabada.repository;

import com.anabada.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p join fetch p.member where p.productNo=:id")
    Optional<Product> findById(@Param("id") Long id);
}
