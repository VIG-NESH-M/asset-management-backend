package com.staunch.tech.service.impl;

import com.staunch.tech.dto.EmailDetails;
import com.staunch.tech.dto.TicketDto;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateTicketStatusDto;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.Reports2D;
import com.staunch.tech.entity.Reports3D;
import com.staunch.tech.entity.Ticket;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.Reports2DRepository;
import com.staunch.tech.repository.Reports3DRepository;
import com.staunch.tech.repository.TicketRepository;
import com.staunch.tech.service.IEmailService;
import com.staunch.tech.service.ITicketService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ValidationUtils validationUtils;

    @Autowired
    private IEmailService emailService;
    
    @Autowired
    private Reports2DRepository reportsRepo;
    
    @Autowired
    private Reports3DRepository reports3Repo;
    /**
     * @param ticketDto
     * @return
     */
    @Override
    public TicketRespDto sendMail(TicketDto ticketDto) {
        validationUtils.validate(ticketDto);
        var employeeOpt = employeeRepository.findById(ticketDto.getEmployeeId());
        if(employeeOpt.isEmpty()){
            throw new AssetManagementException("Employee Id is Invalid");
        }
        var userOpt = employeeRepository.findById(ticketDto.getUserId());
        if(userOpt.isEmpty()) {
            throw new AssetManagementException("User Id is Invalid!");
        }
        var ticket = ConversionUtils.convertDtoToNewEntity(ticketDto, employeeOpt.get(),userOpt.get().getName());
        var employee = employeeOpt.get();
        var emailDetails = new EmailDetails(employee.getEmail(),employee.getName(), ticket.getDescription(), ticket.getTitle(), null);
        emailService.sendSimpleMail(emailDetails);
        var newTicket = ConversionUtils.convertEntityToRespDto(ticketRepository.save(ticket));
        var currentWeek = ConversionUtils.convertTimestampToWeek(newTicket.getCreatedTime());
		reportsRepo.save(new Reports2D(ticketDto.getId(),"weekly", currentWeek, 1,ticket));
        return newTicket;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TicketRespDto getTicketById(String id) {
        var ticketOpt = ticketRepository.findById(id);
        if(ticketOpt.isEmpty()){
            throw new AssetManagementException("Ticket id is Invalid");
        }
        return ConversionUtils.convertEntityToRespDto(ticketOpt.get());
    }

    /**
     * @return
     */
    @Override
    public List<TicketRespDto> getAllTickets() {
        var ticketList = ticketRepository.findAll();
        if(ticketList.isEmpty()){
            throw new AssetManagementException("Ticket List is Empty");
        }
        var ticketDtoList = new ArrayList<TicketRespDto>();
        for(Ticket ticket : ticketList){
            ticketDtoList.add(ConversionUtils.convertEntityToRespDto(ticket));
        }
        return ticketDtoList;
    }

    /**
     * @return
     */
    @Override
    public List<TicketRespDto> getAllTicketsByStatus(String status) {
        var ticketList = ticketRepository.findByStatus(status);
        if(ticketList.isEmpty()){
            throw new AssetManagementException("Ticket List is Empty");
        }
        var ticketDtoList = new ArrayList<TicketRespDto>();
        for(Ticket ticket : ticketList){
            ticketDtoList.add(ConversionUtils.convertEntityToRespDto(ticket));
        }
        return ticketDtoList;
    }


    /**
     * @return
     */
    @Override
    public List<TicketRespDto> getAllTicketsByEmployeeId(int employeeId) {
        var ticketList = ticketRepository.findAll();
        if(ticketList.isEmpty()){
            throw new AssetManagementException("Ticket List is Empty");
        }
        var ticketDtoList = ticketList.stream()
                .filter(ticket -> ticket.getEmployeeId().getId() == employeeId)
                .map(ConversionUtils::convertEntityToRespDto).collect(Collectors.toList());
        return ticketDtoList;
    }

    /**
     * @param updateTicketStatusDto
     * @return
     */
    @Override
    public TicketRespDto updateTicketStatus(UpdateTicketStatusDto updateTicketStatusDto) {
       var ticketOpt = ticketRepository.findById(updateTicketStatusDto.getTicketId());
       if(ticketOpt.isEmpty()){
           throw new AssetManagementException("Ticket Id is Invalid");
       }
        var userOpt = employeeRepository.findById(updateTicketStatusDto.getUserId());
        if(userOpt.isEmpty()) {
            throw new AssetManagementException("User Id is Invalid!");
        }

       var ticket = ticketOpt.get();
       if(updateTicketStatusDto.getStatus().equalsIgnoreCase("CLOSED")){
           var totalTimeTaken = System.currentTimeMillis() - ticket.getCreatedTime();
           ticket.setTimeTaken(totalTimeTaken);
           ticket.setTotalCost(updateTicketStatusDto.getAmountSpent());
       }
       ticket.setStatus(updateTicketStatusDto.getStatus());
        var assignedToOpt = employeeRepository.findById(updateTicketStatusDto.getAssignedTo());
        if(assignedToOpt.isPresent()) {
            ticket.setEmployeeId(assignedToOpt.get());
        }
       ticket.setUpdatedBy(userOpt.get().getName());
       ticket.setUpdatedTime(System.currentTimeMillis());
       var updatedTicket = ConversionUtils.convertEntityToRespDto(ticketRepository.save(ticket));
       var uTicket = ConversionUtils.convertTimestampToWeek(updatedTicket.getCreatedTime());
       var mReport = ConversionUtils.convertTimestampToMonth(updatedTicket.getCreatedTime());
       if(ticket.getTotalCost()>0) {
	       reportsRepo.save(new Reports2D(updateTicketStatusDto.getId(),"colour", ticket.getCategory(), ticket.getTotalCost(),ticket));
	       reports3Repo.save(new Reports3D(updateTicketStatusDto.getId(), "radar", mReport, ticket.getIssueType(), ticket.getTotalCost(), ticket));
	       reports3Repo.save(new Reports3D(updateTicketStatusDto.getId(), "bubble", uTicket, ticket.getIssueType(), ticket.getTotalCost(), ticket));
	   }
       return updatedTicket; 
    }

    /**
     * @param ticketId
     * @param
     * @return
     */
    @Override
    public TicketRespDto updateTicket(String ticketId, TicketDto ticketDto) {
        if(!ticketId.equals(ticketDto.getUuid())){
            throw new AssetManagementException("Ticket Id in body is different from path");
        }
        var ticketOpt = ticketRepository.findById(ticketId);
        if(ticketOpt.isEmpty()){
            throw new AssetManagementException("Ticket Id is Invalid");
        }
        var userOpt = employeeRepository.findById(ticketDto.getUserId());
        if(userOpt.isEmpty()) {
            throw new AssetManagementException("User Id is Invalid!");
        }
        var employee = userOpt.get();
        var ticket = ticketOpt.get();
        var updatedTicket = ConversionUtils.convertDtoToUpdateEntity(ticketDto,
                ticket.getEmployeeId(),employee.getName());

        var emailDetails = new EmailDetails(employee.getEmail(),employee.getName(),
                ticket.getDescription(), ticket.getTitle(), null);
        emailService.sendSimpleMail(emailDetails);
//        return ConversionUtils.convertEntityToRespDto(ticketRepository.save(updatedTicket));
        var updateTicket = ConversionUtils.convertEntityToRespDto(ticketRepository.save(updatedTicket));
        var uTicket = ConversionUtils.convertTimestampToWeek(updatedTicket.getCreatedTime());
        var mReport = ConversionUtils.convertTimestampToMonth(updatedTicket.getCreatedTime());
        if(ticket.getTotalCost() > 0) {
	        reportsRepo.save(new Reports2D(ticketDto.getId(),"colour", ticket.getCategory(), ticket.getTotalCost(),ticket));
	        reports3Repo.save(new Reports3D(ticketDto.getId(), "radar", mReport, updateTicket.getIssueTYpe(), ticket.getTotalCost(), ticket));
	        reports3Repo.save(new Reports3D(ticketDto.getId(), "bubble", uTicket, updateTicket.getIssueTYpe(), ticket.getTotalCost(), ticket));
        }
        return updateTicket;
    }

    /**
     * @param ticketDto
     * @return
     */
    @Override
    public TicketRespDto sendMailWithAttachment(TicketDto ticketDto, MultipartFile file){
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssetManagementException("Error While Handling the file." + e.getMessage());
        }
        validationUtils.validate(ticketDto);
        var employeeOpt = employeeRepository.findById(ticketDto.getEmployeeId());
        if(employeeOpt.isEmpty()){
            throw new AssetManagementException("Employee Id is Invalid");
        }
        var ticket = ConversionUtils.convertDtoToNewEntity(ticketDto, employeeOpt.get(), "Dhinesh");
        var employee = employeeOpt.get();
        var emailDetails = new EmailDetails(employee.getEmail(),employee.getName(), ticket.getDescription(), ticket.getTitle(), convFile);
        emailService.sendMailWithAttachment(emailDetails);
        var newTicket = ConversionUtils.convertEntityToRespDto(ticketRepository.save(ticket));
        var currentWeek = ConversionUtils.convertTimestampToWeek(newTicket.getCreatedTime());
		reportsRepo.save(new Reports2D(ticketDto.getId(),"weekly", currentWeek, 1,ticket));
        return newTicket;
    }

	@Override
	public List<TicketRespDto> getAllTicketsById(List<String> listId) {
		List<TicketRespDto> tickets = new ArrayList<>();
		for(var id : listId) {
			var ticketOpt = ticketRepository.findById(id);
	        if(ticketOpt.isEmpty()){
	            throw new AssetManagementException("Ticket id is Invalid");
	        }
	       tickets.add(ConversionUtils.convertEntityToRespDto(ticketOpt.get()));
		}	
		return tickets;
	} 
}
