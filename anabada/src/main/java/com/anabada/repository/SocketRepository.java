package com.anabada.repository;

import com.anabada.entity.ProductSocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocketRepository extends JpaRepository<ProductSocket,Long> {


}
