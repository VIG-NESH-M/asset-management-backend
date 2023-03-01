package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.service.IInventoryService;

@RestController
@RequestMapping("/inventory")
@CrossOrigin("*")
public class InventoryController {
	
	@Autowired
	private IInventoryService inventoryService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addItem(@RequestBody InventoryDto inventoryDto) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.createItem(inventoryDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listItem() {
		var response = new ApiResponseDto("1200", "Success", inventoryService.getAllItems());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> updateItem(@PathVariable("itemId") int id,
			@RequestBody InventoryDto inventoryDto) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.updateItem(id, inventoryDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> deleteItem(@PathVariable("itemId") int itemId) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.deleteItemById(itemId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("itemId") int itemId) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.getItem(itemId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

}
