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

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM exam.customer", nativeQuery = true)
    List<Customer> getCustomers();


    @Query(value = "select id from exam.address where (street) = :street and (number) = :number", nativeQuery = true)
    int getAddressId(@Param("street") String street, @Param("number") int number);

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT into exam.customer (firstName, lastName, email, phone, addressId, role, password)" +
//            "values (:firstName, :lastName, :email, :phone, :addressId, :role, :password)", nativeQuery = true)
//    Customer registerCustomer(@Param("firstName") String firstName,
//                              @Param("lastName") String lastName,
//                              @Param("email") String email,
//                              @Param("phone") String phone,
//                              @Param("addressId") int addressId,
//                              @Param("role") String role,
//                              @Param("password") String password);
}