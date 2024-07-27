package com.example.smartrestaurant.repository;

import com.example.smartrestaurant.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
