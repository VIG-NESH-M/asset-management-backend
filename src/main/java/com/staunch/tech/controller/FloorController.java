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
import com.staunch.tech.dto.FloorDto;
import com.staunch.tech.service.impl.FloorService;

@RestController
@RequestMapping("/floor")
@CrossOrigin("*")
public class FloorController {

	@Autowired
	private FloorService floorService;
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addFloor(@RequestBody FloorDto floorDto) {
		var response = new ApiResponseDto("1200", "Success",floorService.createFloor(floorDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listFloor() {
		var response = new ApiResponseDto("1200", "Success", floorService.getAllFloors());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping("/{floorId}")
	public ResponseEntity<ApiResponseDto> deleteFloor(@PathVariable("floorId") int floorId) {
		var response = new ApiResponseDto("1200", "Success", floorService.deleteFloor(floorId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("building/{building}")
	public ResponseEntity<ApiResponseDto> listFloorByBuilding(@PathVariable("building") String buildingName) {
		var response = new ApiResponseDto("1200", "Success", floorService.getAllFloorByBuilding(buildingName));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{floorId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("floorId") int floorId) {
		var response = new ApiResponseDto("1200", "Success", floorService.getFloor(floorId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
