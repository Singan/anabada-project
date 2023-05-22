package com.anabada.repository;

import com.anabada.entity.SuccessfulBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessBidRepository extends JpaRepository<SuccessfulBid,Long> {

}
