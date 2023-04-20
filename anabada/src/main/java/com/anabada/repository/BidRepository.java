package com.anabada.repository;

import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select b from Bid b join fetch b.product where b.product = :product")
    List<Bid> findBidByProduct(@Param("product") Product product);
}
