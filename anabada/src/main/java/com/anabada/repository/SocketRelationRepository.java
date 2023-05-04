package com.anabada.repository;

import com.anabada.entity.Member;
import com.anabada.entity.SocketRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocketRelationRepository extends JpaRepository<SocketRelation, Long> {

    @Query("select s from socket_relation s join fetch s.productSocket where s.member = :member")
    List<SocketRelation> findSocketRelationByMember(Member member);
}
