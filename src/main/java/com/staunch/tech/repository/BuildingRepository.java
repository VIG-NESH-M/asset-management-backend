package com.staunch.tech.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.Building;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

	Optional<Building> findByName(String buildingName);
	
	
}
