package com.anabada.repository;

import com.anabada.entity.Product;
import com.anabada.entity.nativeQuery.ProductFindOneInterface;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true,
            value = "select p.product_no as productNo , p.product_name as productName, " +
                    "p.product_detail as productDetail , p.product_use_date as productUseDate," +
                    "p.product_visit as productVisit , p.product_price as productPrice,"+
                    "DATE_FORMAT(p.create_date_time,'%Y년% %m월 %d일 %h시 %i분 %s초') as productTime ,"+
                    "m.member_name as memberName , m.member_addr as memberAddr,"+
                    "pi.image_addr as productImageList, p.product_thumbnail as productThumbnail, " +
                    "max(b.bid_time) as bidTime ,"+
                    "max(b.bid_price) as productHighPrice, m.member_no as memberNo from product p" +
                    " join member m on p.member_no = m.member_no" +
            " left join product_image pi on p.product_no = pi.product_no" +
            " left join bid b on p.product_no = b.product_no" +
            " where p.product_no = :productNo"+
            " order by productHighPrice desc;")
    ProductFindOneInterface findProductDetail(@Param("productNo") Long productNo); // 프로덕트 상세

    @Modifying
    @Query("update Product p set p.productVisit=p.productVisit+1 where p.productNo = :productNo")
    void upProductVisit(@Param("productNo") Long productNo);

    @Query("select distinct p from Product p join fetch p.member ")
    @BatchSize(size = 3)
    List<Product> findByProductAndMember(Pageable pageable); //메인에 나가는 목록 조회 조정할 예정

    @Query("select  p from Product p " +
            "where p.category.categoryNo = :categoryNo and p.productIsBidComplete = false")
    List<Product> findProductByCategory(@Param("categoryNo") Long categoryNo); //상품 카테고리 조회

    // 판매 내역
    @Query("select p from Product p where p.member.memberNo = :memberNo order by p.createDateTime desc")
    List<Product> findByMemberNo(@Param("memberNo") Long memberNo);

    @Query("select p.productIsBidComplete from Product p where p.productNo = :productNo")
    boolean readProductByProductIsBidComplete(@Param("productNo") Long productNo);

//    @Query("select b.* from bid b " +
//            "inner join (select max(b.bid_price) bid_price from bid b group by b.product_no) b2 " +
//            "on b.bid_price = b2.bid_price and b.member_no = :memberNo;")
//    List<Product> findProductListWithBid();
//
//    Product findProductDetail();

}
