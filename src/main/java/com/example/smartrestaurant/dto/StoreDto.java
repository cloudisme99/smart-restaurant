package com.example.smartrestaurant.dto;

import com.example.smartrestaurant.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

	private Long id;
	private String name;
	private String address;
	private String phone;

	public static StoreDto fromEntity(Store store) {
		return StoreDto.builder()
			.id(store.getId())
			.name(store.getName())
			.address(store.getAddress())
			.phone(store.getPhone())
			.build();
	}

	public Store toEntity() {
		return new Store(
			this.id,
			this.name,
			this.address,
			this.phone
		);
	}
}
