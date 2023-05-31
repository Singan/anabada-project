package com.anabada.repository;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.entity.nativeQuery.MyBidHistory;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select b from Bid b join fetch b.member where b.product = :product order by b.price asc")
    List<Bid> findBidListByProduct(@Param("product") Product product);

    Bid findFirstByProductOrderByTimeDesc(Product product);

    Bid findBidByProductAndMember(Product product, Member member);

    // 입찰 내역
    @Query(value = "select" +
            "    b.bid_no bidNo," +
            "    b.bid_price  bidPrice," +
            "    b.bid_time bidTime," +
            "    p.product_no productNo," +
            "    m.member_name productMemberName," +
            "    p.create_date_time productTime," +
            "    p.product_name productName," +
            "    p.product_price productPrice," +
            "    p.product_is_bid_complete productSuccessIs," +
            "    p.product_thumbnail productThumbnail," +
            "    m.member_wish_addr memberAddr ," +
            "    m.member_no memberNo " +
            "from bid b " +
            "inner join product p on b.product_no = p.product_no " +
            "inner join member m on p.member_no = m.member_no " +
            "where b.member_no = :memberNo",nativeQuery = true)
    Page<MyBidHistory> findBidListByMemberNo(@Param("memberNo") Long memberNo, Pageable pageable);

    @Query(value = "SELECT " +
            " p2.product_name as productName," +
            "b1.product_no as productNo," +
            " b1.bid_no as bidNo FROM bid b1" +
            " INNER JOIN(SELECT b.product_no, MAX(b.bid_price) AS bid_price FROM bid b" +
            " GROUP BY b.product_no) b2 ON b1.product_no = b2.product_no AND b1.bid_price = b2.bid_price " +
            " INNER JOIN product p2 ON b1.product_no = p2.product_no" +
            " WHERE b1.bid_time <= NOW() - INTERVAL 10 MINUTE AND p2.product_is_bid_complete = false;", nativeQuery = true)
    List<MaxBidProductNoInterface> bidList();

    @Query("UPDATE Product SET productIsBidComplete = true WHERE productNo IN :productNoList")
    @Modifying
    void updateProductSuccessBid(@Param("productNoList") List<Long> productNoList);

}
