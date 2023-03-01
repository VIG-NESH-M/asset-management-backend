package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.AreaDto;
import com.staunch.tech.entity.Area;



public interface IAreaService {

	Area createRoom(AreaDto areaDto);
	Area getRoom(int roomId);
	String deleteRoom(int roomId);
	List<Area> gellAllRoomByFloor(String floorName);
	List<Area> getAllRoom();
}
