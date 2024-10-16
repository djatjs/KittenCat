package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.b01.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
//    Page<Store> findByMidMid(String username, Pageable pageable);
    Page<Store> findByMid_Mid(String mid, Pageable pageable);
}

