package com.anabada.repository;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select b from Bid b join fetch b.member where b.product = :product order by b.time asc")
    List<Bid> findBidListByProduct(@Param("product") Product product);

    Bid findFirstByProductOrderByTimeDesc(Product product);

    Bid findBidByProductAndMember(Product product, Member member);

    // 입찰 내역
    @Query("select b from Bid b join fetch b.product where b.member.memberNo = :memberNo order by b.time desc")
    List<Bid> findBidListByMemberNo(@Param("memberNo") Long memberNo);
}
