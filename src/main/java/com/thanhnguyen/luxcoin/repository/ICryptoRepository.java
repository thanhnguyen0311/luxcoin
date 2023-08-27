package com.thanhnguyen.luxcoin.repository;

import com.thanhnguyen.luxcoin.model.entity.Crypto;
import com.thanhnguyen.luxcoin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICryptoRepository extends JpaRepository<Crypto,Long> {
    Optional<Crypto> findByName(String name);
}
