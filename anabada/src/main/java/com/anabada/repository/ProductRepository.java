package com.anabada.repository;

import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.nativeQuery.ProductFindOneInterface;
import com.anabada.entity.nativeQuery.SalesListSelectInterface;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true,
            value = "select p.product_no as productNo , p.product_name as productName,"+
                    "p.product_detail as productDetail , p.product_use_date as productUseDate,"+
                    "p.product_visit as productVisit , p.product_price as productPrice,"+
                    "DATE_FORMAT(p.create_date_time,'%Y년% %m월 %d일 %h시 %i분') as productTime,"+
                    "m.member_name as memberName , m.member_addr as memberAddr," +
                    " m.member_image as memberImage,"+
                    "p.product_thumbnail as productThumbnail,"+
                    "b.bid_time as bidTime ,"+
                    "b.bid_price as productHighPrice, m.member_no as memberNo from product p "+
                    "join member m on p.member_no = m.member_no "+
                    //"left join product_image pi on p.product_no = pi.product_no "+
                    "left join bid b on p.product_no = b.product_no "+
                    "where p.product_no = :productNo "+
                    "order by productHighPrice desc limit 1;")
    ProductFindOneInterface findProductDetail(@Param("productNo") Long productNo); // 프로덕트 상세

    @Modifying
    @Query("update Product p set p.productVisit=p.productVisit+1 where p.productNo = :productNo")
    void upProductVisit(@Param("productNo") Long productNo);

    @Query("select distinct p from Product p join fetch p.member where p.productIsBidComplete = false order by p.productVisit desc")
    List<Product> findByProductAndMember(Pageable pageable); //메인에 나가는 목록 조회 조정할 예정

    @Query("select  p from Product p " +
            "where p.category.categoryNo = :categoryNo and p.productIsBidComplete = false")
    Page<Product> findProductByCategory(@Param("categoryNo") Long categoryNo, Pageable pageable); //상품 카테고리 조회

    // 판매 내역
    @Query("select p.productNo from Product p " +
            "where p.member.memberNo = :memberNo and p.productIsBidComplete = false " +
            "order by p.createDateTime desc")
    List<Long> findByMemberNo(@Param("memberNo") Long memberNo);

    @Query("select p.productIsBidComplete from Product p where p.productNo = :productNo")
    boolean readProductByProductIsBidComplete(@Param("productNo") Long productNo);

    @Query(value = "select p.product_name as productName," +
            "b.bid_price         as bidPrice," +
            "p.product_thumbnail as productThumbnail," +
            "p.product_no        as productNo," +
            "p.product_price as productPrice ," +
            "p.product_is_bid_complete as productIsBidComplete," +
            "p.create_date_time  as createDateTime," +
            "b.bid_time          as bidTime," +
            "m.member_name       as memberName," +
            "m.member_no         as memberNo," +
            "sb.success_bid_product_no as successNo" +
            " from product p" +
            " left join successful_bid sb on p.product_no = sb.product_no" +
            " left join bid b on sb.bid_no = b.bid_no" +
            " left join member m on b.member_no = m.member_no" +
            " where p.member_no = :memberNo", nativeQuery = true) // 판매 내역 리스트 인데 사용할지 고민중
    Page<SalesListSelectInterface> findProductListWithBid(@Param("memberNo") Long memberNo,Pageable pageable);

}
