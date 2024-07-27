package com.example.smartrestaurant.service;

import com.example.smartrestaurant.domain.Customer;
import com.example.smartrestaurant.dto.CustomerDto;
import com.example.smartrestaurant.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customer = customerDto.toEntity();
		return CustomerDto.fromEntity(customerRepository.save(customer));
	}

	@Transactional(readOnly = true)
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll().stream()
			.map(CustomerDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Transactional
	public Optional<CustomerDto> updateCustomer(Long id, CustomerDto customerDto) {
		return customerRepository.findById(id)
			.map(existingCustomer -> {
				existingCustomer.setName(customerDto.getName());
				existingCustomer.setAddress(customerDto.getAddress());
				existingCustomer.setPhone(customerDto.getPhone());
				return CustomerDto.fromEntity(customerRepository.save(existingCustomer));
			});
	}

	@Transactional
	public boolean deleteCustomer(Long id) {
		return customerRepository.findById(id)
			.map(customer -> {
				customerRepository.delete(customer);
				return true;
			})
			.orElse(false);
	}
}
