package com.staunch.tech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.entity.Inventory;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.InventoryRepository;
import com.staunch.tech.service.IInventoryService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;

@Service
public class InventoryService implements IInventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ValidationUtils validationUtils;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Inventory createItem(InventoryDto inventoryDto) {
		validationUtils.validate(inventoryDto);
		var userOpt = employeeRepository.findById(inventoryDto.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		var item = ConversionUtils.convertDtoToNewEntity(inventoryDto, userOpt.get().getName());
		return inventoryRepository.save(item);
	}

	@Override
	public Inventory getItem(int itemId) {
		var itemOpt = inventoryRepository.findById(itemId);
		if (itemOpt.isEmpty()) {
			throw new AssetManagementException("Item Id is Invalid");
		}
		return itemOpt.get();
	}

	@Override
	public List<Inventory> getAllItems() {
		var itemList = inventoryRepository.findAll();
		if (itemList.isEmpty()) {
			throw new AssetManagementException("Item List Empty");
		}
		return itemList;
	}

	@Override
	public Inventory updateItem(int itemId, InventoryDto inventoryDto) {
		try {
			if (itemId != inventoryDto.getId()) {
				throw new AssetManagementException("item id in body and path are not same");
			}
			validationUtils.validate(inventoryDto);
			var itemOpt = inventoryRepository.findById(inventoryDto.getId());
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("Asset id is Invalid");
			}
			var userOpt = employeeRepository.findById(inventoryDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var item = ConversionUtils.convertDtoToUpdateEntity(inventoryDto,userOpt.get().getName(),itemOpt.get());
			return inventoryRepository.save(item);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	@Override
	public String deleteItemById(int id) {
		var assetOpt = inventoryRepository.findById(id);
		if (assetOpt.isEmpty()) {
			throw new AssetManagementException("Asset id is Invalid");
		}
		inventoryRepository.deleteById(id);
		return "Item with id : " + id + " deleted successfully";
	}

}
