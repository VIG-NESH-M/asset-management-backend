package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.BuildingDto;
import com.staunch.tech.service.impl.BuildingService;


@RestController
@RequestMapping("/building")
@CrossOrigin("*")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addBuilding(@RequestBody BuildingDto buildingDto) {
		var response = new ApiResponseDto("1200", "Success",buildingService.createBuilding(buildingDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listBuilding() {
		var response = new ApiResponseDto("1200", "Success", buildingService.getAllBuildings());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping("/{buildingId}")
	public ResponseEntity<ApiResponseDto> deleteBuilding(@PathVariable("buildingId") int buildingId) {
		var response = new ApiResponseDto("1200", "Success", buildingService.deleteBuilding(buildingId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/location/{location}")
	public ResponseEntity<ApiResponseDto> listBuildingByLocation(@PathVariable("location") String location) {
		var response = new ApiResponseDto("1200", "Success", buildingService.getAllBuildingByLocation(location));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{buildingId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("buildingId") int buildingId) {
		var response = new ApiResponseDto("1200", "Success", buildingService.getBuilding(buildingId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
