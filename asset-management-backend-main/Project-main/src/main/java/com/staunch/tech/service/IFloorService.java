package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.FloorDto;
import com.staunch.tech.entity.Floor;



public interface IFloorService {

	Floor createFloor(FloorDto floorDto);
	Floor getFloor(int floorId);
	String deleteFloor(int floorId);
	List<Floor> getAllFloorByBuilding(String floorName);
    List<Floor> getAllFloors();
}
