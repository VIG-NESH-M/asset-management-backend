package com.staunch.tech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.LocationsDto;
import com.staunch.tech.entity.Inventory;
import com.staunch.tech.entity.Locations;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.LocationsRepository;
import com.staunch.tech.service.ILocationsService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;

@Service
public class LocationsService implements ILocationsService {
	
	@Autowired
	private LocationsRepository locationRepository;
	
	@Autowired
	private ValidationUtils validationUtils;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Locations createLocation(LocationsDto locationsDto) {
		validationUtils.validate(locationsDto);
		var userOpt = employeeRepository.findById(locationsDto.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		var item = ConversionUtils.convertDtoToNewEntity(locationsDto, userOpt.get().getName());
		return locationRepository.save(item);
	}
	

	@Override
	public Locations getLocation(int locationId) {
		var locationOpt = locationRepository.findById(locationId);
		if (locationOpt.isEmpty()) {
			throw new AssetManagementException("Location Id is Invalid");
		}
		return locationOpt.get();
	}

	@Override
	public List<Locations> getAllLocations() {
		var locationList = locationRepository.findAll();
		if (locationList.isEmpty()) {
			throw new AssetManagementException("Location List Empty");
		}
		return locationList;
	}

	@Override
	public Locations UpdateLocations(int locationId, LocationsDto locationsDto) {
		try {
			if (locationId != locationsDto.getId()) {
				throw new AssetManagementException("item id in body and path are not same");
			}
			validationUtils.validate(locationsDto);
			var itemOpt = locationRepository.findById(locationsDto.getId());
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("Asset id is Invalid");
			}
			var userOpt = employeeRepository.findById(locationsDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var item = ConversionUtils.convertDtoToUpdateEntity(locationsDto,userOpt.get().getName(),itemOpt.get());
			return locationRepository.save(item);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	@Override
	public String deleteLocationById(int locationId) {
		var locationOpt = locationRepository.findById(locationId);
		if (locationOpt.isEmpty()) {
			throw new AssetManagementException("Asset id is Invalid");
		}
		locationRepository.deleteById(locationId);
		return "Item with id : " + locationId + " deleted successfully";
	}
}
