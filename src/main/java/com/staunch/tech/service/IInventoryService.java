package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.entity.Inventory;

public interface IInventoryService {
	
	Inventory createItem(InventoryDto inventoryDto);
	
	Inventory getItem(int itemId);
	
	List<Inventory> getAllItems();
	
	Inventory updateItem(int itemId, InventoryDto inventoryDto);
	
	String deleteItemById(int id);
}
