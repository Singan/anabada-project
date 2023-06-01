package com.anabada.repository;


import com.anabada.entity.Member;
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

    @Query("select m from Member m " +
            "left join Product p on p.member.memberNo = m.memberNo and p.productIsBidComplete = false " +
            " where m.memberNo = :memberNo and m.memberExist = false")
    Member findMemberByMemberNo(@Param("memberNo") Long memberNo);


}
