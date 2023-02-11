package com.ddd.membership.domain.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddd.membership.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByBarcodeNo(String barcodeNo);
}
