package com.example.smartrestaurant.repository;

import com.example.smartrestaurant.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
