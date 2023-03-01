package com.staunch.tech.exception;

import com.staunch.tech.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AssetMgmtExceptionAdvisor {

    @ExceptionHandler(AssetManagementException.class)
    public ResponseEntity<ApiResponseDto> handleAssetMgmtException(AssetManagementException assetManagementException) {
        return new ResponseEntity<>(new ApiResponseDto("4000", assetManagementException.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
