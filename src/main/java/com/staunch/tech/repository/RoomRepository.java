package com.staunch.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staunch.tech.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
 
}
