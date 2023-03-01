package com.staunch.tech.service.impl;

import com.staunch.tech.dto.AssetByCsvDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.entity.Asset;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.AssetRepository;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.LocationRepository;
import com.staunch.tech.service.IAssertService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService implements IAssertService {

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ValidationUtils validationUtils;

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @param assetDto
	 * @return
	 */
	@Override
	public Asset registerAsset(AssetDto assetDto) {
		try {
			validationUtils.validate(assetDto);
			var locationOpt = locationRepository.findByFacilityCode(assetDto.getFacilityCode());
			if (locationOpt.isEmpty()) {
				throw new AssetManagementException("The given Facility Code is Invalid");
			}
			var userOpt = employeeRepository.findById(assetDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var asset = ConversionUtils.convertDtoToNewEntity(assetDto, locationOpt.get(), userOpt.get().getName());
			return assetRepository.save(asset);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	/**
	 * @param assetId
	 * @return
	 */
	@Override
	public Asset getAsset(int assetId) {
		var assetOpt = assetRepository.findById(assetId);
		if (assetOpt.isEmpty()) {
			throw new AssetManagementException("Asset Id is Invalid");
		}
		return assetOpt.get();
	}

	/**
	 * @return
	 */
	@Override
	public List<Asset> getAllAssets() {
		var assetList = assetRepository.findAll();
		if (assetList.isEmpty()) {
			throw new AssetManagementException("Asset List Empty");
		}
		return assetList;
	}

	/**
	 * @param facilityCode
	 * @return
	 */
	@Override
	public List<Asset> getAllAssetsByFacility(String facilityCode) {
		var assetList = assetRepository.findAll();
		var assetListByLocationCode = assetList.stream()
				.filter(asset -> asset.getLocation().getFacilityCode().equals(facilityCode))
				.collect(Collectors.toList());
		if (assetListByLocationCode.isEmpty()) {
			throw new AssetManagementException("Asset List is Empty in the given Facility Code!");
		}
		return assetList;
	}

	/**
	 * @param assetDto
	 * @return
	 */
	@Override
	public Asset updateAsset(int assetId, AssetDto assetDto) {
		try {
			if (assetId != assetDto.getId()) {
				throw new AssetManagementException("Asset id in body and path are not same");
			}
			validationUtils.validate(assetDto);
			var assetOpt = assetRepository.findById(assetDto.getId());
			if (assetOpt.isEmpty()) {
				throw new AssetManagementException("Asset id is Invalid");
			}
			var locationOpt = locationRepository.findByFacilityCode(assetDto.getFacilityCode());
			if (locationOpt.isEmpty()) {
				throw new AssetManagementException("The given Facility Code is Invalid");
			}
			var userOpt = employeeRepository.findById(assetDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var asset = ConversionUtils.convertDtoToUpdateEntity(assetDto, locationOpt.get(), userOpt.get().getName(),
					assetOpt.get());
			return assetRepository.save(asset);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	/**
	 * @param assetId
	 * @return
	 */
	@Override
	public String deleteAssetById(int assetId) {
		var assetOpt = assetRepository.findById(assetId);
		if (assetOpt.isEmpty()) {
			throw new AssetManagementException("Asset id is Invalid");
		}
		assetRepository.deleteById(assetId);
		return "Asset with id : " + assetId + " deleted successfully";
	}

	/**
	 *
	 * @param file
	 * @return
	 */
	@Override
	public AssetByCsvDto addAssetsByCsv(MultipartFile file, int userId) {
		if (file.isEmpty()) {
			throw new AssetManagementException("File is Empty");
		}
		var userOpt = employeeRepository.findById(userId);
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {
			List<Asset> assets = new ArrayList<>();
			List<AssetDto> failedAssets = new ArrayList<>();
			Iterable<CSVRecord> csvRecords1 = CSVFormat.RFC4180
					.withHeader("name", "description", "price", "facilityCode").parse(fileReader).getRecords();
			for (CSVRecord csvRecord : csvRecords1) {
				AssetDto assetDto = new AssetDto();
				assetDto.setName(csvRecord.get("name").replaceAll("[^a-zA-Z0-9]", ""));
				assetDto.setDescription(csvRecord.get("description"));
				assetDto.setPrice(Float.parseFloat(csvRecord.get("price").replaceAll("[^a-zA-Z0-9]", "")));
				assetDto.setFacilityCode(csvRecord.get("facilityCode").replaceAll("[^a-zA-Z0-9]", ""));
				var locationOpt = locationRepository.findByFacilityCode(assetDto.getFacilityCode());
				if (locationOpt.isEmpty()) {
					failedAssets.add(assetDto);
					continue;
				}
				assets.add(ConversionUtils.convertDtoToNewEntity(assetDto, locationOpt.get(), "Dhinesh"));
			}
			System.out.println(assets);
			var savedRecords = assetRepository.saveAll(assets);
			return new AssetByCsvDto(savedRecords, "Facility Code is Invalid", failedAssets);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AssetManagementException("Error while handling the file.");
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}
}
