package com.example.smartrestaurant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column
	private String phone;
}
