package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketStatusDto {
	private int id;
	private String ticketId;
	private String status;
	private int userId;
	private long amountSpent;
	private int assignedTo;

}
