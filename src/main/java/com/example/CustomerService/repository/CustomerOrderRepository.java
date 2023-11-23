package com.example.CustomerService.repository;

import com.example.CustomerService.model.entity.CustomerOrder;
import com.example.CustomerService.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    Optional<CustomerOrder> findCustomerOrderBySystemOrderId(int systemOrderId);

    @Modifying
    @Query("UPDATE CustomerOrder c SET c.status = :orderStatus WHERE c.id = :id")
    int setCustomerOrderStatus(OrderStatus orderStatus, int id);
}
