package com.staunch.tech.service;

import com.staunch.tech.dto.LoginRefreshDto;
import com.staunch.tech.dto.LoginRequestDto;
import com.staunch.tech.dto.LoginResponseDto;
import io.jsonwebtoken.Claims;

public interface IAuthenticateService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    LoginResponseDto getRefreshToken(LoginRefreshDto loginRefreshDto);

    Claims checkIfValid(String token);
}
