package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.entity.Room;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.repository.RoomRepository;
import com.staunch.tech.service.IRoomService;

@Service
public class RoomService implements IRoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired 
	private FloorRepository floorRepository;
	
	@Override
	public Room createRoom(RoomDto roomDto) {
		var floor = floorRepository.findByName(roomDto.getFloorName());
		return roomRepository.save(new Room(roomDto.getId(),roomDto.getName(), floor.get()));
	}

	@Override
	public Room getRoom(int roomId) {
		var roomOpt = roomRepository.findById(roomId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Room Id is Invalid");
		}
		return roomOpt.get();
	}

	@Override
	public String deleteRoom(int roomId) {
		var roomOpt = roomRepository.findById(roomId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Room id is Invalid");
		}
		roomRepository.deleteById(roomId);
		return "Building with id : " + roomId + " deleted successfully";
	}

	@Override
	public List<Room> getAllRoomByFloor(String floorName) {
		var roomList = roomRepository.findAll();
		var roomListByBuilding = roomList.stream().filter(room->room.getFloor().getName().equals(floorName)).collect(Collectors.toList());
		if (roomListByBuilding.isEmpty()) {
			throw new AssetManagementException("Room List is Empty in the given Floor Name!");
		}
		return roomList;
	}

	@Override
	public List<Room> getAllRoom() {
		var rooms = roomRepository.findAll();
		if (rooms.isEmpty()) {
			throw new AssetManagementException("Room List is Empty");
		}
		return rooms;
	}

}
