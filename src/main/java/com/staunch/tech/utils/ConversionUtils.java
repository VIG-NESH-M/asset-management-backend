package com.staunch.tech.utils;

import com.staunch.tech.dto.*;
import com.staunch.tech.entity.Asset;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.KnowledgeRepo;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.Ticket;

import java.util.UUID;

public class ConversionUtils {

    /**
     *
     * @param assetDto
     * @param createdBy
     * @return
     */
    public static Asset convertDtoToNewEntity(AssetDto assetDto, Location location, String createdBy) {
        long createdTime = System.currentTimeMillis();
        return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(),
                assetDto.getPrice(), location, createdBy, createdTime, createdBy, createdTime, false);
    }

    /**
     *
     * @param assetDto
     * @param updatedBy
     * @param existingAsset
     * @return
     */
    public static Asset convertDtoToUpdateEntity(AssetDto assetDto, Location location, String updatedBy,
                                                 Asset existingAsset) {
        long updatedTime = System.currentTimeMillis();
        long createdTime = existingAsset.getCreatedTime();
        String createdBy = existingAsset.getCreatedBy();
        return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(),
                assetDto.getPrice(), location, createdBy, createdTime, updatedBy, updatedTime, false);
    }

    /**
     *
     * @param employeeDto
     * @param createdBy
     * @return
     */
    public static Employee convertDtoToNewEntity(EmployeeDto employeeDto, String createdBy) {
        long createdTime = System.currentTimeMillis();
        return new Employee(employeeDto.getId(), employeeDto.getName(),
                employeeDto.getEmail(), employeeDto.getDepartment(), employeeDto.getDesignation(), employeeDto.getPhoneNumber(), employeeDto.getUsername(),
                employeeDto.getPassword()
                , employeeDto.getRoles(), createdBy, createdTime, createdBy, createdTime);
    }

    /**
     *
     * @param employeeDto
     * @param updatedBy
     * @param employee
     * @return
     */
    public static Employee convertDtoToUpdateEntity(EmployeeUpdateReqDto employeeDto, String updatedBy, Employee employee) {
        long updatedTime = System.currentTimeMillis();
        long createdTime = employee.getCreatedTime();
        String createdBy = employee.getCreatedBy();
        String empName = employeeDto.getName() == null ? employee.getName() : employeeDto.getName();
        String empEmail = employeeDto.getEmail() == null ? employee.getEmail() : employeeDto.getEmail();
        String department = employeeDto.getDepartment() == null ? employee.getDepartment() : employeeDto.getDepartment();
        long empPhoneNumber = employeeDto.getPhoneNumber()!= null ? employee.getPhoneNumber() : employeeDto.getPhoneNumber();
        return new Employee(employee.getId(), empName,
               empEmail, department,  employee.getDesignation(),empPhoneNumber,employee.getUsername(), employee.getPassword(),
                employee.getRoles(), createdBy, createdTime, updatedBy, updatedTime);
    }

    /**
     *
     * @param ticketDto
     * @param createdBy
     * @return
     */
    public static Ticket convertDtoToNewEntity(TicketDto ticketDto, Employee employee, String createdBy) {
        long createdTime = System.currentTimeMillis();
        return new Ticket(UUID.randomUUID().toString(), ticketDto.getTitle(),
                ticketDto.getDescription(), ticketDto.getCategory(), "Open", employee,
                ticketDto.getIssueType(), ticketDto.getAssetId(),
                createdBy, createdTime, createdBy, createdTime, 0, 0,ticketDto.getExpectedCompletionTime());
    }

    /**
     *
     * @param ticketDto
     * @param updatedBy
     * @param employee
     * @return
     */
    public static Ticket convertDtoToUpdateEntity(TicketDto ticketDto, Employee employee, String updatedBy) {
        long updatedTime = System.currentTimeMillis();
        long createdTime = employee.getCreatedTime();
        String createdBy = employee.getCreatedBy();
        return new Ticket(ticketDto.getUuid(), ticketDto.getTitle(),
                ticketDto.getDescription(), ticketDto.getCategory(), ticketDto.getStatus(),
                employee,ticketDto.getIssueType(), ticketDto.getAssetId(),
                createdBy, createdTime, updatedBy, updatedTime, 0, 0,ticketDto.getExpectedCompletionTime());
    }


    /**
     *
     * @param ticket
     * @return
     */
    public static TicketRespDto convertEntityToRespDto(Ticket ticket) {
        return new TicketRespDto(ticket.getUuid(), ticket.getTitle(), ticket.getDescription(), ticket.getCategory(), ticket.getStatus(),
                ticket.getEmployeeId().getId(), ticket.getEmployeeId().getName(), ticket.getEmployeeId().getEmail(),
                ticket.getEmployeeId().getDepartment(),ticket.getIssueType(), ticket.getAssetId(), ticket.getCreatedBy(),
                ticket.getCreatedTime(), ticket.getUpdatedBy(), ticket.getUpdatedTime(), ticket.getTimeTaken(), false, ticket.getExpectedCompletionTime());
    }

    /**
     *
     * @param employee
     * @return
     */
    public static EmployeeRespDto convertEntityToDto(Employee employee) {
        return new EmployeeRespDto(employee.getId(), employee.getName(), employee.getEmail(),
                employee.getPhoneNumber(), employee.getDepartment(), employee.getDesignation(),
                employee.getUsername(), employee.getRoles());
    }

    public static KRResponseDto convertFilenameToDto(KnowledgeRepoDto dto, String filename, int id, long time) {
    	
    	return new KRResponseDto(dto.getId(),filename, dto.getAsset_name(),id , time);
    }
    
    public static KRResponseDto convertKnoledgeRepoToDto(KnowledgeRepo repo)
    {
    	return new KRResponseDto(repo.getId(), repo.getName(), repo.getAsset_name(), repo.getId(), repo.getUploaded_time());
    }
}
