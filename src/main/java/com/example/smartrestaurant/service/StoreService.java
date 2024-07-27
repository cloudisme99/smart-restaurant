package com.example.smartrestaurant.service;

import com.example.smartrestaurant.domain.Store;
import com.example.smartrestaurant.dto.StoreDto;
import com.example.smartrestaurant.repository.StoreRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StoreService {

	private final StoreRepository storeRepository;

	@Autowired
	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Transactional
	public StoreDto addStore(StoreDto storeDto) {
		Store store = storeDto.toEntity();
		return StoreDto.fromEntity(storeRepository.save(store));
	}

	@Transactional(readOnly = true)
	public List<StoreDto> getAllStores() {
		return storeRepository.findAll().stream()
			.map(StoreDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Transactional
	public Optional<StoreDto> updateStore(Long id, StoreDto storeDto) {
		return storeRepository.findById(id)
			.map(existingStore -> {
				existingStore.setName(storeDto.getName());
				existingStore.setAddress(storeDto.getAddress());
				existingStore.setPhone(storeDto.getPhone());
				return StoreDto.fromEntity(storeRepository.save(existingStore));
			});
	}

	@Transactional
	public boolean deleteStore(Long id) {
		return storeRepository.findById(id)
			.map(store -> {
				storeRepository.delete(store);
				return true;
			})
			.orElse(false);
	}
}
