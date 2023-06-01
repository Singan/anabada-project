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
            "join fetch m.memberProductList p" +
            " where m.memberNo = :memberNo and m.memberExist = false")
    Member findMemberAndProductList(@Param("memberNo") Long memberNo);


}
