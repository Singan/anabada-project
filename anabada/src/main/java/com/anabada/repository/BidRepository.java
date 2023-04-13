package com.anabada.repository;

import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findBidByProduct(Product product);
}
