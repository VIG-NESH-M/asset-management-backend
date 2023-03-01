package com.staunch.tech.utils;

import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.dto.EmployeeDto;
import com.staunch.tech.dto.TicketDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.exception.AssetManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

@Component
public class ValidationUtils {

    static Logger logger = LoggerFactory.getLogger(ValidationUtils.class);
    @Autowired
    private Validator validator;

    /**
     *
     * @param employeeDto
     */
    public void validate(EmployeeDto employeeDto){
        logger.info("Validating the Employee Object");
        var employeeConstraints = validator.validate(employeeDto);
        String employeeViolations = employeeConstraints.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        if(!employeeViolations.isBlank()){
            throw new AssetManagementException(employeeViolations);
        }
        logger.info("No Errors Found in EmployeeDto");
    }

    /**
     *
     * @param assetDto
     */
    public void validate(AssetDto assetDto){
        logger.info("Validating the Ticket Object");
        var assetConstraints = validator.validate(assetDto);
        String assetViolations = assetConstraints.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        if(!assetViolations.isBlank()){
            throw new AssetManagementException(assetViolations);
        }
        logger.info("No Errors Found in Asset Dto");
    }

    /**
     *
     * @param ticketDto
     */
    public void validate(TicketDto ticketDto){
        logger.info("Validating the Ticket Object");
        var ticketConstraints = validator.validate(ticketDto);
        String ticketViolations = ticketConstraints.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        if(!ticketViolations.isBlank()){
            throw new AssetManagementException(ticketViolations);
        }
        logger.info("No Errors Found in Ticket Dto");
    }

    public void validate(Location location){
        logger.info("Validating the Facility Object");
        var ticketConstraints = validator.validate(location);
        String ticketViolations = ticketConstraints.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        if(!ticketViolations.isBlank()){
            throw new AssetManagementException(ticketViolations);
        }
        logger.info("No Errors Found in Ticket Dto");
    }

}
