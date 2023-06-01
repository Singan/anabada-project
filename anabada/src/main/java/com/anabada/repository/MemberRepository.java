package com.anabada.repository;


import com.anabada.entity.Member;
import com.anabada.entity.nativeQuery.MemberInfoInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    Member findByMemberIdAndMemberExistFalse(String id);

    boolean existsMemberByMemberId(String id);

    @Query(value = "select" +
            "    m.member_no memberNo," +
            "    m.member_name memberName," +
            "    m.member_image memberImage," +
            "    p.product_no productNo " +
            "from member m " +
            "left join product p on p.member_no = p.product_no and p.product_is_bid_complete = false " +
            "where m.member_no = :memberNo and m.member_exist = false", nativeQuery = true)
    MemberInfoInterface findMemberAndProductList(@Param("memberNo") Long memberNo);


}
