package com.anabada.repository;

import com.anabada.dto.response_dto.SuccessBidDto;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.MyBuyListInterface;
import com.anabada.etc.Status;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuccessBidRepository extends JpaRepository<SuccessfulBid,Long> {


    @Query(value = "select " +
            "b.bid_no as bidNo," +
            "p.product_no as productNo ," +
            "m.member_name as productMemberName," +
            "m.member_no as memberNo," +
            "b.bid_time as successTime," +
            "p.create_date_time as productTime," +
            "p.product_name as productName," +
            "b.bid_price as bidPrice," +
            "p.product_thumbnail as productThumbnail," +
            "s.success_bid_product_no as successBidNo" +
            " from successful_bid  s " +
            "join bid b on s.bid_no = b.bid_no " +
            "join product p on s.product_no = p.product_no " +
            "join member m on p.member_no = m.member_no " +
            "where b.member_no = :memberNo",nativeQuery = true)
    Page<MyBuyListInterface> successfulBidList(@Param("memberNo") Long memberNo, Pageable pageable);


    // 낙찰 상품 조회
    @Query("select s from SuccessfulBid s where s.successBidProductNo = :no")
    @EntityGraph(attributePaths = {"bid","bid.product"})
    SuccessfulBid findSuccessfulBid(@Param("no") Long no);

    @Query("update SuccessfulBid s set s.status = :status where s.successBidProductNo = :sno")
    @Modifying
    void updateSuccessfulBidByStatus(@Param("sno") Long no ,@Param("status") Status status);

}
