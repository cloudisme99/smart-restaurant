package com.example.smartrestaurant.controller;

import com.example.smartrestaurant.dto.MenuDto;
import com.example.smartrestaurant.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	// menu 추가
	@PostMapping
	public ResponseEntity<MenuDto> addMenu(@RequestBody MenuDto menuDto) {
		MenuDto addMenu = menuService.addMenu(menuDto);
		return new ResponseEntity<>(addMenu, HttpStatus.CREATED);
	}

	// 모든 menu 조회
	@GetMapping
	public ResponseEntity<List<MenuDto>> getAllMenus() {
		List<MenuDto> menus = menuService.getAllMenus();
		return ResponseEntity.ok(menus);

	}

	// menu 수정
	@PutMapping("/{id}")
	public ResponseEntity<MenuDto> updateMenu(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
		return menuService.updateMenu(id, menuDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

}
