package com.staunch.tech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_inventory")
public class Inventory {
	@Id
	@Column(name = "itemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "itemName")
	private String name;
	
	@Column(name = "itemCode")
	private int code;
	
	@Column(name = "description")
	private String description;
	
	@Column
	private int quantity;
	
	@Column
	private float price;
	
    @Column(name ="created_by")
    private String createdBy;
    @Column(name ="created_time")
    private long createdTime;
    @Column(name ="updated_by")
    private String updatedBy;
    @Column(name ="updated_time")
    private long updatedTime;

    @Transient
    private boolean expand;
}
