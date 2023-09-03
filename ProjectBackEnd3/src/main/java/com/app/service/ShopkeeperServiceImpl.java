package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Menu;
import com.app.entity.Order;
import com.app.repository.IMenuRepository;
import com.app.repository.IOrderRepository;

@Service
@Transactional
public class ShopkeeperServiceImpl implements IShopkeeperService {

	@Autowired
	private IMenuRepository menuRepo;

	@Autowired
	private IOrderRepository orderRepo;

	@Override
	public Menu changeAvailability(Long id) {

		Menu item = menuRepo.findById(id).orElseThrow(() -> new RuntimeException("Item with given id doesn't exist"));
		System.out.println("Before change : " + item);

		if (item.getAvailability() == true) {
			item.setAvailability(false);
		} else {
			item.setAvailability(true);
		}

		menuRepo.flush();
		System.out.println("After changes: " + item);
		return item;
	}

	@Override
	public List<Menu> showMenu() {

		return menuRepo.findAll();
	}

	@Override
	public List<Order> getPlacedOrders(String status) {

		return orderRepo.findByStatus(status);
	}

	
}
