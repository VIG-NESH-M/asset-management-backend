package com.staunch.tech.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.EmailDetails;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateWorkOrderStatusDto;
import com.staunch.tech.dto.WorkOrderDto;
import com.staunch.tech.dto.WorkOrderRespDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.WorkOrder;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.WorkOrderRepository;
import com.staunch.tech.service.IEmailService;
import com.staunch.tech.service.IWorkOrderService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ImageUtils;
import com.staunch.tech.utils.ValidationUtils;
@Service
public class WorkOrderService implements IWorkOrderService {
	
	@Autowired
    private WorkOrderRepository workorderRepository;
	
	@Autowired
    private ValidationUtils validationUtils;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private IEmailService emailService;
	
	
	
	
	@Override
	public WorkOrderRespDto createWorkOrder(WorkOrderDto workorderDto, MultipartFile file) throws IOException {
		validationUtils.validate(workorderDto);
        var employeeOpt = employeeRepository.findById(workorderDto.getEmployeeId());
        if(employeeOpt.isEmpty()){
            throw new AssetManagementException("Employee Id is Invalid");
            }

        var userOpt = employeeRepository.findById(workorderDto.getEmployeeId());
        if(userOpt.isEmpty()) {
            throw new AssetManagementException("Employee Id is Invalid!");
            }
        var workorder = ConversionUtils.convertDtoToNewEntity(workorderDto, employeeOpt.get(),userOpt.get().getName(), file);
//        var employee = employeeOpt.get();
//        var emailDetails = new EmailDetails(workorder.getEmailId(),employee.getName(), workorder.getDescription(), workorder.getName(), null);
//        emailService.sendSimpleMail(emailDetails);
        return ConversionUtils.convertEntityToRespDto(workorderRepository.save(workorder));
	}
	
//	 public String uploadImage(MultipartFile file) throws IOException {
//
//	        WorkOrder data = WorkOrder.builder()
//	                .name(file.getOriginalFilename()).data(ImageUtils.compressImage(file.getBytes())).build();
//	        if (data != null) {
//	            return "file uploaded successfully : " + file.getOriginalFilename();
//	        }
//	        return null;
//	    }
//
//	    public byte[] downloadImage(String fileName){
//	        Optional<ImageData> dbImageData = repository.findByName(fileName);
//	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
//	        return images;
//	    }
//	

//	@Override
//	public WorkOrderRespDto sendMailWithAttachment(WorkOrderDto workorderDto, MultipartFile file) {
//		 File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
//	        try {
//	            file.transferTo(convFile);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            throw new AssetManagementException("Error While Handling the file." + e.getMessage());
//	        }
//	        validationUtils.validate(workorderDto);
//	        var employeeOpt = employeeRepository.findById(workorderDto.getEmployeeId());
//	        if(employeeOpt.isEmpty()){
//	            throw new AssetManagementException("Employee Id is Invalid");
//	        }
//	        var workorder = ConversionUtils.convertDtoToNewEntity(workorderDto, employeeOpt.get(), "Dhinesh");
//	        var employee = employeeOpt.get();
//	        var emailDetails = new EmailDetails(employee.getEmail(),employee.getName(), workorder.getDescription(), workorder.getName(), convFile);
//	        emailService.sendMailWithAttachment(emailDetails);
//	        return ConversionUtils.convertEntityToRespDto(workorderRepository.save(workorder));
//		
//	}

	@Override
	public WorkOrderRespDto getWorkOrderByorder(int workorderorderNo) {
		var workorderOpt  =workorderRepository.findById(workorderorderNo);
		if(workorderOpt.isEmpty()) {
			throw new AssetManagementException("WorkOrder orderNo is Invalid");
		}
		return ConversionUtils.convertEntityToRespDto(workorderOpt.get());
	}

	@Override
	public List<WorkOrderRespDto> getAllWorkOrders() {
		var workorderList = workorderRepository.findAll();
		if(workorderList.isEmpty())
		{
			throw new AssetManagementException("WorkOrder List is Empty");
		}
		var workorderDtoList = new ArrayList<WorkOrderRespDto>();
		for(WorkOrder workorder : workorderList) {
			workorderDtoList.add(ConversionUtils.convertEntityToRespDto(workorder));
		}
		return workorderDtoList;
	}

	
	public byte[] downloadImage(String fileName){
        Optional<WorkOrder> dbImageData = workorderRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getData());
        return images;
    }
//	@Override
//	public List<WorkOrderRespDto> getAllWorkOrderByStatus(String Status) {
//		var workorderList = workorderRepository.findByStatus(Status);
//		if(workorderList.isEmpty()) {
//			throw new AssetManagementException("WorkOrder List is Empty");
//		}
//		var workorderDtoList = new ArrayList<WorkOrderRespDto>();
//		for(WorkOrder workorder : workorderList) {
//			workorderDtoList.add(ConversionUtils.convertEntityToRespDto(workorder));
//		}
//		return workorderDtoList;
//	}

//	@Override
//	public WorkOrderRespDto updateWorkOrderStatus(UpdateWorkOrderStatusDto updateWorkOrderstatusDto) {
//		var workorderOpt = workorderRepository.findByOrderNo(updateWorkOrderstatusDto.getOrderNo());
//        var userOpt = employeeRepository.findById(updateWorkOrderstatusDto.getUserId());
//		if(workorderOpt.isEmpty()) {
//			throw new AssetManagementException("WorkOrder orderNo is Invalid");
//		}
//	   var workorder = workorderOpt.get(0);
//       if(updateWorkOrderstatusDto.getStatus().equalsIgnoreCase("FINISHED")){
//           var totalTimeTaken = System.currentTimeMillis() - workorder.getCreatedTime();
//           workorder.setTimeTaken(totalTimeTaken);
//       }
//       workorder.setStatus(updateWorkOrderstatusDto.getStatus());
//        var assignedToOpt = employeeRepository.findById(updateWorkOrderstatusDto.getAssignedTo());
//        if(assignedToOpt.isPresent()) {
//        	workorder.setName(assignedToOpt.get().getName());
//        }
//        workorder.setUpdatedBy(userOpt.get().getName());
//        workorder.setUpdatedTime(System.currentTimeMillis());
//      return ConversionUtils.convertEntityToRespDto(workorderRepository.save(workorder));
//	}
//
//	@Override
//	public WorkOrderRespDto updateWorkOrder(int workorderorderNo, WorkOrderDto workorderDto) throws IOException {
//		 if(workorderorderNo != workorderDto.getOrderNo()){
//	            throw new AssetManagementException("WorkOrder orderNo in body is different from path");
//	            }
//	        var workorderOpt = workorderRepository.findByOrderNo(workorderorderNo);
//	        if(workorderOpt.isEmpty()){
//	            throw new AssetManagementException("WorkOrder is Invalid");
//	        }
//	        var userOpt = employeeRepository.findById(workorderDto.getEmployeeId());
//	        if(userOpt.isEmpty()){
//	            throw new AssetManagementException("User Id is Invalid!");
//	        }
//	        var employee = userOpt.get();
//	        var workorder = workorderOpt.get(0);
//	        var employeeId = employeeRepository.findById(workorderDto.getEmployeeId());
//	        var updatedWorkOrder = ConversionUtils.convertDtoToUpdateEntity(workorderDto,employeeId.get(),employee.getName());
//	        var emailDetails = new EmailDetails(employee.getEmail(),employee.getName(),
//	        		workorder.getDescription(), workorder.getName(), null);
//	        emailService.sendSimpleMail(emailDetails);
//	        return ConversionUtils.convertEntityToRespDto(workorderRepository.save(updatedWorkOrder));	}


    @Override
    public String deleteWorkOrder(int workorderorderNo) {
        var assetOpt = workorderRepository.findById(workorderorderNo);
        if (assetOpt.isEmpty()) {
            throw new AssetManagementException("Asset id is Invalid");
        }
        workorderRepository.deleteById(workorderorderNo);
        return "Asset with id : " + workorderorderNo + " deleted successfully";
    }
	
}
