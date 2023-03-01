package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.BuildingDto;
import com.staunch.tech.entity.Building;


public interface IBuildingService {

	
	Building createBuilding(BuildingDto buildingDto);
	Building getBuilding(int id);
	String deleteBuilding(int buildingId);
	List<Building> getAllBuildingByLocation(String buildingName);
    List<Building> getAllBuildings();
    
}
