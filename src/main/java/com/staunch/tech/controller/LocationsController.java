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
import com.staunch.tech.dto.LocationsDto;
import com.staunch.tech.service.impl.LocationsService;

@RestController
@RequestMapping("/locations")
@CrossOrigin("*")
public class LocationsController {
	
	@Autowired
	private LocationsService locationSevice;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addItem(@RequestBody LocationsDto locationDto) {
		var response = new ApiResponseDto("1200", "Success", locationSevice.createLocation(locationDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listItem() {
		var response = new ApiResponseDto("1200", "Success", locationSevice.getAllLocations());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/{locationId}")
	public ResponseEntity<ApiResponseDto> updateItem(@PathVariable("locationId") int locationId,
			@RequestBody LocationsDto locationDto) {
		var response = new ApiResponseDto("1200", "Success", locationSevice.UpdateLocations(locationId, locationDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{locationId}")
	public ResponseEntity<ApiResponseDto> deleteItem(@PathVariable("locationId") int locationId) {
		var response = new ApiResponseDto("1200", "Success", locationSevice.deleteLocationById(locationId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{locationId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("locationId") int locationId) {
		var response = new ApiResponseDto("1200", "Success", locationSevice.getLocation(locationId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

}
