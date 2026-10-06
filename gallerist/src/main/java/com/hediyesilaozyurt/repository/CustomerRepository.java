package com.hediyesilaozyurt.repository;

import com.hediyesilaozyurt.entities.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUserUsername(@Param("username") String username);
    boolean existsByUserUsername(String username);
}
