package com.staunch.tech.controller;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.service.IAssertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/assets")
@CrossOrigin("*")
public class AssetController {

	@Autowired
	private IAssertService assetService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addAsset(@RequestBody AssetDto assetDto) {
		var response = new ApiResponseDto("1200", "Success", assetService.registerAsset(assetDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listAssets() {
		var response = new ApiResponseDto("1200", "Success", assetService.getAllAssets());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> updateAsset(@PathVariable("assetId") int assetId,
			@RequestBody AssetDto assetDto) {
		var response = new ApiResponseDto("1200", "Success", assetService.updateAsset(assetId, assetDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> deleteAsset(@PathVariable("assetId") int assetId) {
		var response = new ApiResponseDto("1200", "Success", assetService.deleteAssetById(assetId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> getAssetById(@PathVariable("assetId") int assetId) {
		var response = new ApiResponseDto("1200", "Success", assetService.getAsset(assetId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	@GetMapping("/facilities/{location}")
	public ResponseEntity<ApiResponseDto> listAssetsByFacility(@PathVariable("location") String location) {
		var response = new ApiResponseDto("1200", "Success", assetService.getAllAssetsByFacility(location));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/csv/{userId}")
	public ResponseEntity<ApiResponseDto> addAssetByCsv(@RequestParam("file") MultipartFile file,
			@PathVariable("userId") int userId) {
		var response = new ApiResponseDto("1200", "Success", assetService.addAssetsByCsv(file, userId));
		return new ResponseEntity(response, HttpStatus.ACCEPTED);
	}

}
