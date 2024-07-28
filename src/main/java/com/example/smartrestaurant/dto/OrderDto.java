package com.example.smartrestaurant.dto;

import com.example.smartrestaurant.domain.Customer;
import com.example.smartrestaurant.domain.Orders;
import com.example.smartrestaurant.domain.OrderItem;
import com.example.smartrestaurant.domain.OrderStatus;
import com.example.smartrestaurant.domain.Store;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

	private Long id;
	private Long customerId;
	private Long storeId;
	private List<OrderItemDto> orderItems;
	private OrderStatus status;
	private LocalDateTime orderedAt;
	private LocalDateTime orderUpdatedAt;

	public static OrderDto fromEntity(Orders orders) {
		return OrderDto.builder()
			.id(orders.getId())
			.customerId(orders.getCustomer().getId())
			.storeId(orders.getStore().getId())
			.orderItems(orders.getOrderItems().stream().map(OrderItemDto::fromEntity).collect(Collectors.toList()))
			.status(orders.getStatus())
			.orderedAt(orders.getOrderedAt())
			.orderUpdatedAt(orders.getOrderUpdatedAt())
			.build();
	}

	public Orders toEntity(Customer customer, Store store, List<OrderItem> orderItemList) {
		return Orders.builder()
			.id(this.id)
			.customer(customer)
			.store(store)
			.orderItems(orderItemList)
			.status(this.status)
			.orderedAt(this.orderedAt)
			.orderUpdatedAt(this.orderUpdatedAt)
			.build();
	}

}
