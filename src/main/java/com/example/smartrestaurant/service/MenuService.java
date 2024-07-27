package com.example.smartrestaurant.service;

import com.example.smartrestaurant.domain.Menu;
import com.example.smartrestaurant.dto.MenuDto;
import com.example.smartrestaurant.repository.MenuRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MenuService {

	private final MenuRepository menuRepository;

	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Transactional
	public MenuDto addMenu(MenuDto menuDto) {
		Menu menu = menuDto.toEntity();
		return MenuDto.fromEntity(menuRepository.save(menu));
	}

	@Transactional(readOnly = true)
	public List<MenuDto> getAllMenus() {
		return menuRepository.findAll().stream()
			.map(MenuDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Transactional
	public Optional<MenuDto> updateMenu(Long id, MenuDto menuDto) {
		return menuRepository.findById(id)
			.map(existingMenu -> {
				existingMenu.setName(menuDto.getName());
				existingMenu.setCategory(menuDto.getCategory());
				existingMenu.setPrice(menuDto.getPrice());
				existingMenu.setDescription(menuDto.getDescription());
				return MenuDto.fromEntity(menuRepository.save(existingMenu));
			});
	}

	@Transactional
	public boolean deleteMenu(Long id) {
		return menuRepository.findById(id)
			.map(menu -> {
				menuRepository.delete(menu);
				return true;
			})
			.orElse(false);
	}
}
