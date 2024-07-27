package com.example.smartrestaurant.controller;

import com.example.smartrestaurant.dto.MenuDto;
import com.example.smartrestaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

}
