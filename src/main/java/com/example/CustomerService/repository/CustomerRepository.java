package com.example.CustomerService.repository;

import com.example.CustomerService.model.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);


    @Query(value = "select id from exam.address where (street) = :street and (number) = :number", nativeQuery = true)
    int getAddressId(@Param("street") String street, @Param("number") int number);
}