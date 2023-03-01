package com.staunch.tech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "knowledge_repo")
public class KnowledgeRepo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	private int id;

	@Column(name = "document_name")
	private String name;

	@Column(name = "asset_name")
	private String asset_name;

	@ManyToOne
	private Employee user;

	@Column(name = "uploaded_time")
	private long uploaded_time;
}
