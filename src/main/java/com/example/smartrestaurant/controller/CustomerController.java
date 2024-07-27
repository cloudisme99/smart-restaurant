package com.example.smartrestaurant.controller;

import com.example.smartrestaurant.dto.CustomerDto;
import com.example.smartrestaurant.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// customer 추가
	@PostMapping
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
		CustomerDto addCustomer = customerService.addCustomer(customerDto);
		return new ResponseEntity<>(addCustomer, HttpStatus.CREATED);
	}

	// 모든 customer 조회
	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<CustomerDto> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);

	}

	// customer 정보 수정
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
		return customerService.updateCustomer(id, customerDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// customer 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("id") Long id) {
		boolean deleted = customerService.deleteCustomer(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
