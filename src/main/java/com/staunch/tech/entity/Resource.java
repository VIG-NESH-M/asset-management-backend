package com.staunch.tech.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Resource_id")
	private int resourceId;
	@Column(name = "Resource_name")
	private String resourceName;
	@Column(name = "Resource_type")
	private String resourceType;
	@Column(name = "Start_date")
	private Date startDate;
	@Column(name = "End_date")
	private Date endDate;
	@Column(name = "Resource_Availability")
	private String availability;
	@Column(name = "user_Id")
	private int userId;
	@Column(name = "WorkerOrder_Id")
	private int workOrderId;
	@Column(name = "Inventory_Id")
	private int inventoryId;
}
