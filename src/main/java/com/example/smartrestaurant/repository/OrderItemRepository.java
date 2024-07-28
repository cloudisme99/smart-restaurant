package com.example.smartrestaurant.repository;

import com.example.smartrestaurant.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
