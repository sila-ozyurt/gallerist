package com.hediyesilaozyurt.repository;

import com.hediyesilaozyurt.entities.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = """
            select * 
            from gallery_management.customer
            where username= :username
            """,nativeQuery = true)
    Optional<Customer> findByUsername(@Param("username") String username);


    @Query(value = """
            select count(*)>0
            from gallery_management.customer
            where username= :username
            """,nativeQuery = true)
    boolean existsByUsername(String username);
}
