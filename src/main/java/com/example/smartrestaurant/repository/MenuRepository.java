package com.example.smartrestaurant.repository;

import com.example.smartrestaurant.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}