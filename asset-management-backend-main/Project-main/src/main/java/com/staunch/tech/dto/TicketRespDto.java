package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespDto {
    private String uuid;
    private String title;
    private String description;
    private String category;
    private String status;
    private int employeeId;
    private String employeeName;
    private String employeeMail;
    private String employeeDepartment;
    private String issueTYpe;
    private String assetId;
    
    
    private String createdBy;
    private long createdTime;
    private String updatedBy;
    private long updatedTime;
    private long timeTaken;
    private boolean expand;
    private long expectedCompletionTime;

}
