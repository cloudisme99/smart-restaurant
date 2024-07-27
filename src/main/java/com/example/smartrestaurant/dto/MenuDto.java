package com.example.smartrestaurant.dto;

import com.example.smartrestaurant.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {

	private Long id;
	private String name;
	private String category;
	private Double price;
	private String description;

	public static MenuDto fromEntity(Menu menu) {
		return MenuDto.builder()
			.id(menu.getId())
			.name(menu.getName())
			.category(menu.getCategory())
			.price(menu.getPrice())
			.description(menu.getDescription())
			.build();
	}

	public Menu toEntity() {
		return new Menu(
			this.id,
			this.name,
			this.category,
			this.price,
			this.description
			);
	}

}
