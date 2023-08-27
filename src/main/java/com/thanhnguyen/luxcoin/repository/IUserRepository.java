package com.thanhnguyen.luxcoin.repository;

import com.thanhnguyen.luxcoin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

//    @Query(value = "Select u from User u ")
//    Page<User> getAll(String name, Pageable pageable);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
