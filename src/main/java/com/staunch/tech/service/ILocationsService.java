package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.LocationsDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.Locations;

public interface ILocationsService {

	Locations createLocation(LocationsDto locationsDto);
	
	Locations getLocation(int locationId);
	
	List<Locations> getAllLocations();
	
	Locations UpdateLocations(int locationId, LocationsDto locationsDto);
	
	String deleteLocationById(int locationId);
}
