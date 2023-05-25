package com.anabada.repository;

import com.anabada.entity.SuccessfulBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuccessBidRepository extends JpaRepository<SuccessfulBid,Long> {


    @Query("select s from SuccessfulBid  s " +
            "join fetch s.bid b " +
            "join fetch s.product p " +
            "join fetch p.member " +
            "where b.member.memberNo = :memberNo")
    List<SuccessfulBid> successfulBidList(@Param("memberNo") Long memberNo);

}
