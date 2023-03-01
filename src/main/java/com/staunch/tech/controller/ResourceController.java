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
import com.staunch.tech.dto.ResourceDto;
import com.staunch.tech.service.IResourceService;

@RestController
@RequestMapping("/resource")
@CrossOrigin("*")
public class ResourceController {
	@Autowired
	private IResourceService iResourceService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addResource(@RequestBody ResourceDto resourceDto) {
		var response = new ApiResponseDto("1200", "Success", iResourceService.registerResource(resourceDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping("/resourceId/{id}")
	public ResponseEntity<ApiResponseDto> updateResource(@PathVariable int id, @RequestBody ResourceDto resourceDto) {
		var response = new ApiResponseDto("1200", "Success", iResourceService.updateResource(id, resourceDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto> getResourceById(@PathVariable int id) {
		var response = new ApiResponseDto("1200", "Success", iResourceService.getResourceById(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> getAllResource() {
		var response = new ApiResponseDto("1200", "Success", iResourceService.getAllResource());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteById(@PathVariable int id) {
		var response = new ApiResponseDto("1200", "Success", iResourceService.deleteById(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
