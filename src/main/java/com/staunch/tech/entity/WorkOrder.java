package com.staunch.tech.entity;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name  = "t_workOrders")
@Builder
public class WorkOrder {

	@Id
	@Column(name = "order_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderNo;
	@Column(name = "status")
	private String status;
	@Column(name = "name")
	private String name;
	@Column(name = "email", nullable = false)
	private String emailId;
	@ManyToOne
	private Employee employeeId;
	@Column(name = "phone")
	private long phoneNumber;
	@Column(name = "description")
	private String description;
	@Column(name = "work_subject")
	private String workSubject;
	@Column(name = "taskdetails")
	private String taskDetails;
	@Column(name = "date")
	private Date date;
	@Column(name = "workorder_cost")
	private int workOrderCost;
	@Lob
	@Column(name = "data",length = 1000)
	private byte[] data;
	@Column (name = "file_name")
	private String fileName;
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
    @Column(name = "expected_completion_time", nullable = true)
    private long expectedCompletionTime;
	
	
	
	

}
