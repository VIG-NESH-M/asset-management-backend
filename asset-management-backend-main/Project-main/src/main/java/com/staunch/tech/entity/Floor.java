package com.staunch.tech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_floor")
public class Floor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "floor_id")
	private int id;
	
	@Column(name = "floor_name")
	private String name;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Building building;

}
