package com.staunch.tech.controller;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.LoginRefreshDto;
import com.staunch.tech.dto.LoginRequestDto;
import com.staunch.tech.service.IAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private IAuthenticateService authenticateService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
		var response = new ApiResponseDto("1200", "Success", authenticateService.login(loginRequestDto));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/refresh")
	public ResponseEntity<ApiResponseDto> refreshToken(@RequestBody LoginRefreshDto loginRefreshDto) {
		var response = new ApiResponseDto("1200", "Success", authenticateService.getRefreshToken(loginRefreshDto));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/verify")
	public ResponseEntity<ApiResponseDto> verify(@RequestBody LoginRequestDto loginRequestDto) {
		var response = new ApiResponseDto("1200", "Success",
				authenticateService.checkIfValid(loginRequestDto.getUsername()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
