package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.staunch.tech.dto.AreaDto;
import com.staunch.tech.entity.Area;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.AreaRepository;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.service.IAreaService;

public class AreaService implements IAreaService {

	private AreaRepository areaRepository;
	
	@Autowired 
	private FloorRepository floorRepository;
	
	@Override
	public Area createRoom(AreaDto areaDto) {
		var floor = floorRepository.findByName(areaDto.getFloorName());
		return areaRepository.save(new Area(areaDto.getId(),areaDto.getName(), floor.get()));
	}

	@Override
	public Area getRoom(int areaId) {
		var areaOpt = areaRepository.findById(areaId);
		if (areaOpt.isEmpty()) {
			throw new AssetManagementException("Area Id is Invalid");
		}
		return areaOpt.get();
	}

	@Override
	public String deleteRoom(int areaId) {
		var roomOpt = areaRepository.findById(areaId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Area id is Invalid");
		}
		areaRepository.deleteById(areaId);
		return "Area with id : " + areaId + " deleted successfully";
	}

	@Override
	public List<Area> gellAllRoomByFloor(String floorName) {
		var areaList = areaRepository.findAll();
		var areaListByBuilding = areaList.stream().filter(area->area.getFloor().getName().equals(floorName)).collect(Collectors.toList());
		if (areaListByBuilding.isEmpty()) {
			throw new AssetManagementException("Area List is Empty in the given Location!");
		}
		return areaList;
	}

	@Override
	public List<Area> getAllRoom() {
		var area = areaRepository.findAll();
		if (area.isEmpty()) {
			throw new AssetManagementException("Area List is Empty");
		}
		return area;
	}

}
