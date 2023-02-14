package com.staunch.tech.service;

import com.staunch.tech.dto.TicketDto;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateTicketStatusDto;
import com.staunch.tech.entity.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ITicketService {
    TicketRespDto sendMail(TicketDto ticketDto);
    TicketRespDto sendMailWithAttachment(TicketDto ticketDto, MultipartFile file);
    TicketRespDto getTicketById(String id);
    List<TicketRespDto> getAllTickets();
    List<TicketRespDto> getAllTicketsByStatus(String status);
    List<TicketRespDto> getAllTicketsByEmployeeId(int employeeId);

     TicketRespDto updateTicketStatus(UpdateTicketStatusDto updateTicketStatusDto);
     TicketRespDto updateTicket(String ticketId, TicketDto ticketDto);
}
