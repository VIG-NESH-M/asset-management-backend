package com.staunch.tech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeRepoDto {

	private int id;
	@NotBlank(message = "Asset name is empty")
	private String asset_name;
}
