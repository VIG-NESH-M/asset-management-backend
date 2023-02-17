package com.staunch.tech.controller;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.EmployeeRespDto;
import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/facilities")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> createFacility(
            @RequestBody Location facility) {
        var response = new ApiResponseDto("1200", "Success",locationService.createFacility(facility));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto> getAllFacilities() {
        var response = new ApiResponseDto("1200", "Success",locationService.getAllFacilities());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{facilityId}")
    public ResponseEntity<ApiResponseDto> getAllFacilities(
            @PathVariable("facilityId") long facilityId) {
        var response = new ApiResponseDto("1200", "Success",locationService.deleteFacility(facilityId));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
