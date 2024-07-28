package com.example.smartrestaurant.dto;

import com.example.smartrestaurant.domain.Menu;
import com.example.smartrestaurant.domain.Orders;
import com.example.smartrestaurant.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

	private Long id;
	private Long orderId;
	private Long menuId;
	private int quantity;

	public static OrderItemDto fromEntity(OrderItem orderItem) {
		return OrderItemDto.builder()
			.id(orderItem.getId())
			.orderId(orderItem.getOrders().getId())
			.menuId(orderItem.getMenu().getId())
			.quantity(orderItem.getQuantity())
			.build();
	}

	public OrderItem toEntity(Orders orders, Menu menu) {
		return OrderItem.builder()
			.id(this.id)
			.orders(orders)
			.menu(menu)
			.quantity(this.quantity)
			.build();
	}

}
