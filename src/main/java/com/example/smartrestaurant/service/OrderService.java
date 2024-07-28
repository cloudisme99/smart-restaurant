package com.example.smartrestaurant.service;

import com.example.smartrestaurant.domain.Customer;
import com.example.smartrestaurant.domain.Menu;
import com.example.smartrestaurant.domain.Orders;
import com.example.smartrestaurant.domain.OrderItem;
import com.example.smartrestaurant.domain.Store;
import com.example.smartrestaurant.dto.OrderDto;
import com.example.smartrestaurant.repository.CustomerRepository;
import com.example.smartrestaurant.repository.MenuRepository;
import com.example.smartrestaurant.repository.OrderItemRepository;
import com.example.smartrestaurant.repository.OrderRepository;
import com.example.smartrestaurant.repository.StoreRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final CustomerRepository customerRepository;
	private final MenuRepository menuRepository;
	private final StoreRepository storeRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
		CustomerRepository customerRepository, MenuRepository menuRepository, StoreRepository storeRepository) {
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.customerRepository = customerRepository;
		this.menuRepository = menuRepository;
		this.storeRepository = storeRepository;
	}

	// 전체 order 조회 로직
	public List<OrderDto> getAllOrders() {
		return orderRepository.findAll().stream()
			.map(OrderDto::fromEntity)
			.collect(Collectors.toList());
	}

	// order 추가 로직
	@Transactional
	public OrderDto createOrder(OrderDto orderDto) {
		Customer customer = customerRepository.findById(orderDto.getCustomerId())
			.orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다."));
		Store store = storeRepository.findById(orderDto.getStoreId())
			.orElseThrow(() -> new RuntimeException("상점을 찾을 수 없습니다."));

		List<OrderItem> orderItems = orderDto.getOrderItems().stream()
			.map(orderItemDto -> {
				Menu menu = menuRepository.findById(orderItemDto.getMenuId())
					.orElseThrow(() -> new RuntimeException("메뉴를 찾을 수 없습니다."));
				return orderItemDto.toEntity(null, menu);
			})
			.collect(Collectors.toList());

		Orders orders = orderDto.toEntity(customer, store, orderItems);
		Orders finalOrders = orders;
		orderItems.forEach(item -> item.setOrders(finalOrders));
		orders.setOrderTime(LocalDateTime.now());
		orders = orderRepository.save(orders);
		orderItemRepository.saveAll(orderItems);

		return OrderDto.fromEntity(orders);
	}
}
