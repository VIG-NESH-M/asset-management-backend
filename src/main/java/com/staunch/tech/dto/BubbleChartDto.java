package com.staunch.tech.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BubbleChartDto {

	private String category;

	private List<String> label;

	private List<Long> values;
	
	private List<Long> number;

}
