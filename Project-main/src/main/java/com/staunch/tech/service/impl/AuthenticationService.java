package com.staunch.tech.service.impl;

import com.staunch.tech.dto.LoginRefreshDto;
import com.staunch.tech.dto.LoginRequestDto;
import com.staunch.tech.dto.LoginResponseDto;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.service.IAuthenticateService;
import com.staunch.tech.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticateService {

    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * @param loginRequestDto
     * @return
     */
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var employeeOpt = employeeRepository.findByUsername(loginRequestDto.getUsername());
        if(employeeOpt.isEmpty()){
            throw new AssetManagementException("Username not found!");
        }
        var employee = employeeOpt.get();
        if(passwordEncoder.matches(loginRequestDto.getPassword(), employee.getPassword())){
            String accessToken = JwtUtils.generateToken(employee);
            return new LoginResponseDto(accessToken, null, "1799");
        }else{
            throw new AssetManagementException("Invalid Password");
        }
    }

    /**
     * @param loginRefreshDto
     * @return
     */
    @Override
    public LoginResponseDto getRefreshToken(LoginRefreshDto loginRefreshDto) {
        return null;
    }

    @Override
    public Claims checkIfValid(String token){
        try {
            var claims = JwtUtils.getAllClaimsFromToken(token);
            return claims;
        }catch (SignatureException exception) {
            throw new AssetManagementException("Invalid Token");
        }
    }
}
