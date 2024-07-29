package com.example.smartrestaurant.controller;

import com.example.smartrestaurant.dto.OrderDto;
import com.example.smartrestaurant.service.OrderService;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// 전체 order 조회
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	// order 추가
	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
		OrderDto createdOrder = orderService.createOrder(orderDto);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	// order 수정
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
		OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
		return ResponseEntity.ok(updatedOrder);
	}

	// order 취소
	@PostMapping("/{id}/cancel")
	public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
		return ResponseEntity.ok().build();
	}

}

