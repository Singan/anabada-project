package com.anabada.repository;

import com.anabada.entity.SuccessfulBid;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuccessBidRepository extends JpaRepository<SuccessfulBid,Long> {


    @Query("select s from SuccessfulBid  s " +
            "join   s.bid b " +
            "join   s.product p " +
            "join fetch  p.member " +
            "where b.member.memberNo = :memberNo")
    @BatchSize(size = 5)
    Page<SuccessfulBid> successfulBidList(@Param("memberNo") Long memberNo, Pageable pageable);

}
