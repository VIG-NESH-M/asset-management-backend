package com.staunch.tech.dto;

import com.staunch.tech.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetByCsvDto {
	private List<Asset> savedRecords;
	private String failedReason;
	private List<AssetDto> failedRecords;

}
