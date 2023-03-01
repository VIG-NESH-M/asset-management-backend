package com.staunch.tech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reports3D {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "chart_type")
	private String type;
	
	@Column(name = "chart_labels")
	private String labels;
	
	@Column(name = "chart_label")
	private String label;
	
	@Column(name = "chart_values")
	private long values;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Ticket ticket_id;
}