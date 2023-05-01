package com.anabada.repository;

import com.anabada.entity.SocketTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocketRepository extends JpaRepository<SocketTable,String> {


    SocketTable save(SocketTable socketTable);
}
