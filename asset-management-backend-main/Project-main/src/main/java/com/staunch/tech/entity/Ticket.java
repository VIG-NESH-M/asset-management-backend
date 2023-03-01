package com.staunch.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_tickets")
public class Ticket {
    @Id
    @Column(name ="uuid")
    private String uuid;
    @Column(name ="title")
    private String title;
    @Column(name ="description")
    private String description;
    @Column(name ="category")
    private String category;
    @Column(name ="status")
    private String status;
    @ManyToOne
    private Employee employeeId;
    @Column(name ="issue_type")
    private String issueType;
    @Column(name ="asset_id")
    private String assetId;
   
    
    @Column(name ="created_by")
    private String createdBy;
    @Column(name ="created_time")
    private long createdTime;
    @Column(name ="updated_by")
    private String updatedBy;
    @Column(name ="updated_time")
    private long updatedTime;
    @Column(name = "total_time_taken", nullable = true)
    private long timeTaken;
    @Column(name ="total_cost")
    private long totalCost;
    @Column(name = "expected_completion_time", nullable = true)
    private long expectedCompletionTime;
}
