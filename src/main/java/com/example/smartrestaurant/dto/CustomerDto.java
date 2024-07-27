package com.example.smartrestaurant.dto;

import com.example.smartrestaurant.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

	private Long id;
	private String name;
	private String address;
	private String phone;

	public static CustomerDto fromEntity(Customer customer) {
		return CustomerDto.builder()
			.id(customer.getId())
			.name(customer.getName())
			.address(customer.getAddress())
			.phone(customer.getPhone())
			.build();
	}

	public Customer toEntity() {
		return new Customer(
			this.id,
			this.name,
			this.address,
			this.phone
		);
	}

}
