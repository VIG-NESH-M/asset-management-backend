package com.staunch.tech.service.impl;

import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.LocationRepository;
import com.staunch.tech.service.ILocationService;
import com.staunch.tech.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ValidationUtils validationUtils;

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @param facility
	 * @return
	 */
	@Override
	public Location createFacility(Location facility) {
		validationUtils.validate(facility);
		var userOpt = employeeRepository.findById(facility.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		facility.setCreatedBy(userOpt.get().getName());
		facility.setCreatedTime(System.currentTimeMillis());
		facility.setLastUpdatedBy(userOpt.get().getName());
		facility.setLastUpdatedTime(System.currentTimeMillis());
		try {
			return locationRepository.save(facility);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	/**
	 * @param facilityId
	 * @return
	 */
	@Override
	public Location deleteFacility(long facilityId) {
		var location = locationRepository.findById(facilityId);
		if (location.isEmpty()) {
			throw new AssetManagementException("Facility Id is Invalid");
		}
		try {
			locationRepository.deleteById(facilityId);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
		return location.get();
	}

	/**
	 * @param
	 * @return
	 */
	@Override
	public List<Location> getAllFacilities() {
		var facilities = locationRepository.findAll();
		if (facilities.isEmpty()) {
			throw new AssetManagementException("Facility List is Empty");
		}
		return facilities;
	}
}
