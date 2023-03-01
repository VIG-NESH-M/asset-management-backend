package com.staunch.tech.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.UpdateWorkOrderStatusDto;
import com.staunch.tech.dto.WorkOrderDto;
import com.staunch.tech.dto.WorkOrderRespDto;

public interface IWorkOrderService {
	WorkOrderRespDto createWorkOrder(WorkOrderDto workorderDto, MultipartFile file) throws IOException ;
	WorkOrderRespDto getWorkOrderByorder(int workorderorderNo);
	List<WorkOrderRespDto> getAllWorkOrders();
//	List<WorkOrderRespDto> getAllWorkOrderByStatus(String Status);
//	
//	WorkOrderRespDto updateWorkOrderStatus(UpdateWorkOrderStatusDto updateWorkOrderstatusDto);
//	WorkOrderRespDto updateWorkOrder(int workorderorderNo,WorkOrderDto workorderDto) throws IOException;
	String deleteWorkOrder(int workorderorderNo);
	byte[] downloadImage(String fileName);

	
	
	
	

	
	
	

}
