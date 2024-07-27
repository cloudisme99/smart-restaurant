package com.example.smartrestaurant.controller;

import com.example.smartrestaurant.dto.StoreDto;
import com.example.smartrestaurant.service.StoreService;
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
@RequestMapping("/store")
public class StoreController {

	private final StoreService storeService;

	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	// store 추가
	@PostMapping
	public ResponseEntity<StoreDto> addStore(@RequestBody StoreDto storeDto) {
		StoreDto addStore = storeService.addStore(storeDto);
		return new ResponseEntity<>(addStore, HttpStatus.CREATED);
	}

	// 모든 store 조회
	@GetMapping
	public ResponseEntity<List<StoreDto>> getAllStores() {
		List<StoreDto> stores = storeService.getAllStores();
		return ResponseEntity.ok(stores);

	}

	// store 정보 수정
	@PutMapping("/{id}")
	public ResponseEntity<StoreDto> updateStore(@PathVariable("id") Long id, @RequestBody StoreDto storeDto) {
		return storeService.updateStore(id, storeDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// store 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<StoreDto> deleteStore(@PathVariable("id") Long id) {
		boolean deleted = storeService.deleteStore(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
