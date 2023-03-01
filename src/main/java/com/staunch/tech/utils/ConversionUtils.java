package com.staunch.tech.utils;

import com.staunch.tech.dto.*;
import com.staunch.tech.entity.Asset;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.KnowledgeRepo;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.Resource;
import com.staunch.tech.entity.Ticket;
import java.util.UUID;

public class ConversionUtils {
	private static String createdBy;

	public static Asset convertDtoToUpdateEntity(AssetDto assetDto, Location location, String updatedBy,
			Asset existingAsset) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = existingAsset.getCreatedTime();
		String createdBy = existingAsset.getCreatedBy();
		return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(), assetDto.getPrice(), location,
				createdBy, createdTime, updatedBy, updatedTime, false);
	}

	public static Employee convertDtoToNewEntity(EmployeeDto employeeDto, String createdBy) {
		long createdTime = System.currentTimeMillis();
		return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getEmail(),
				employeeDto.getDepartment(), employeeDto.getDesignation(), employeeDto.getPhoneNumber(),
				employeeDto.getUsername(), employeeDto.getPassword(), employeeDto.getAddress(),
				employeeDto.getLocation(), employeeDto.getRoles(), createdBy, createdTime, createdBy, createdTime);
	}

	public static Employee convertDtoToUpdateEntity(EmployeeUpdateReqDto employeeDto, String updatedBy,
			Employee employee) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = employee.getCreatedTime();
		String createdBy = employee.getCreatedBy();
		String empName = employeeDto.getName() == null ? employee.getName() : employeeDto.getName();
		String empEmail = employeeDto.getEmail() == null ? employee.getEmail() : employeeDto.getEmail();
		String department = employeeDto.getDepartment() == null ? employee.getDepartment()
				: employeeDto.getDepartment();
		long empPhoneNumber = employeeDto.getPhonenumber() != null ? employee.getPhoneNumber()
				: employeeDto.getPhonenumber();
		return new Employee(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment(),
				employee.getDesignation(), employee.getPhoneNumber(), employee.getUsername(), employee.getPassword(),
				employee.getAddress(), employee.getLocation(), employee.getRoles(), createdBy, createdTime, createdBy,
				createdTime);
	}

	public static Ticket convertDtoToNewEntity(TicketDto ticketDto, Employee employee, String createdBy) {
		long createdTime = System.currentTimeMillis();
		return new Ticket(UUID.randomUUID().toString(), ticketDto.getTitle(), ticketDto.getDescription(),
				ticketDto.getCategory(), "Open", employee, ticketDto.getIssueType(), ticketDto.getAssetId(), createdBy,
				createdTime, createdBy, createdTime, 0, 0, ticketDto.getExpectedCompletionTime());
	}

	public static Ticket convertDtoToUpdateEntity(TicketDto ticketDto, Employee employee, String updatedBy) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = employee.getCreatedTime();
		String createdBy = employee.getCreatedBy();
		return new Ticket(ticketDto.getUuid(), ticketDto.getTitle(), ticketDto.getDescription(),
				ticketDto.getCategory(), ticketDto.getStatus(), employee, ticketDto.getIssueType(),
				ticketDto.getAssetId(), createdBy, createdTime, updatedBy, updatedTime, 0, 0,
				ticketDto.getExpectedCompletionTime());
	}

	public static TicketRespDto convertEntityToRespDto(Ticket ticket) {
		return new TicketRespDto(ticket.getUuid(), ticket.getTitle(), ticket.getDescription(), ticket.getCategory(),
				ticket.getStatus(), ticket.getEmployeeId().getId(), ticket.getEmployeeId().getName(),
				ticket.getEmployeeId().getEmail(), ticket.getEmployeeId().getDepartment(), ticket.getIssueType(),
				ticket.getAssetId(), ticket.getCreatedBy(), ticket.getCreatedTime(), ticket.getUpdatedBy(),
				ticket.getUpdatedTime(), ticket.getTimeTaken(), false, ticket.getExpectedCompletionTime());
	}

	public static EmployeeRespDto convertEntityToDto(Employee employee) {
		return new EmployeeRespDto(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment(),
				employee.getDesignation(), employee.getPhoneNumber(), employee.getUsername(), employee.getPassword(),
				employee.getAddress(), employee.getLocation(), employee.getRoles());
	}

	public static KRResponseDto convertFilenameToDto(KnowledgeRepoDto dto, String filename, int id, long time) {

		return new KRResponseDto(dto.getId(), filename, dto.getAsset_name(), id, time);
	}

	public static KRResponseDto convertKnoledgeRepoToDto(KnowledgeRepo repo) {
		return new KRResponseDto(repo.getId(), repo.getName(), repo.getAsset_name(), repo.getId(),
				repo.getUploaded_time());
	}

	public static Resource convertDtoToNewEntity(ResourceDto resourceDto, Integer userId) {
		return new Resource(resourceDto.getResourceId(), resourceDto.getResourceName(), resourceDto.getResourceType(),
				resourceDto.getStartDate(), resourceDto.getEndDate(),
				resourceDto.getAvailability(),resourceDto.getWorkOrderId(),resourceDto.getInventoryId(), userId);
	}

	public static ResourceDto convertEntityToDto(Resource resource) {
		return new ResourceDto(resource.getWorkOrderId(),resource.getResourceId(), resource.getResourceName(), resource.getResourceType(),
				resource.getStartDate(), resource.getEndDate(), 
				resource.getAvailability(), resource.getUserId(),resource.getInventoryId());

	}

	public static Asset convertDtoToNewEntity(AssetDto assetDto, Location location, String name) {
		long createdTime = System.currentTimeMillis();
		return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(), assetDto.getPrice(), location,
				createdBy, createdTime, createdBy, createdTime, false);
	}


//	public static ResourceRespDto convertEntityToDto1(Resource resource) {
//		return new ResourceRespDto(resource.getId(), resource.getEmployeeName(), 0, resource.getProductName(),
//				resource.getPhoneNumber(), resource.getLocation(), resource.getAssignedBy());
//	}

//	public static Asset convertDtoToNewEntity(AssetDto assetDto, Location location, String createdBy) {
//  long createdTime = System.currentTimeMillis();
//  return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(),
//          assetDto.getPrice(), location, createdBy, createdTime, createdBy, createdTime,false);
//  
//    }
}
