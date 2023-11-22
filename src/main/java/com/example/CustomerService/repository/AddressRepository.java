package com.example.CustomerService.repository;

import com.example.CustomerService.model.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT id FROM exam.address WHERE street = '?' AND number = ?", nativeQuery = true)
    Address checkAddress(@Param("street") String street, @Param("number") int number);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO exam.address (street, number) VALUES (?, ?)", nativeQuery = true)
    Address createAddress(@Param("street") String street, @Param("number") int number);


}
