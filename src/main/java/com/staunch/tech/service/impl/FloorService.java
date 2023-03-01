package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.FloorDto;
import com.staunch.tech.entity.Building;
import com.staunch.tech.entity.Floor;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.BuildingRepository;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.service.IFloorService;

@Service
public class FloorService implements IFloorService {

	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public Floor createFloor(FloorDto floorDto) {
		var building = buildingRepository.findByName(floorDto.getBuildingName());
		return floorRepository.save(new Floor(floorDto.getId(),floorDto.getName(), building.get()));
	}

	@Override
	public String deleteFloor(int floorId) {
		var floorOpt = floorRepository.findById(floorId);
		if (floorOpt.isEmpty()) {
			throw new AssetManagementException("Floor id is Invalid");
		}
		floorRepository.deleteById(floorId);
		return "Building with id : " + floorId + " deleted successfully";
	}
	@Override
	public List<Floor> getAllFloorByBuilding(String buildingName) {
		var floorList = floorRepository.findAll();
		var floorListByBuilding = floorList.stream().filter(floor->floor.getBuilding().getName().equals(buildingName)).collect(Collectors.toList());
		if (floorListByBuilding.isEmpty()) {
			throw new AssetManagementException("Building List is Empty in the given Location!");
		}
		return floorList;
	}

	@Override
	public List<Floor> getAllFloors() {
		var floors = floorRepository.findAll();
		if (floors.isEmpty()) {
			throw new AssetManagementException("Buildings List is Empty");
		}
		return floors;
	}
	@Override
	public Floor getFloor(int floorId) {
		var floorOpt = floorRepository.findById(floorId);
		if (floorOpt.isEmpty()) {
			throw new AssetManagementException("Location Id is Invalid");
		}
		return floorOpt.get();
	}

}
