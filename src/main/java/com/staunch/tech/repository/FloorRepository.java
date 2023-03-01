package com.staunch.tech.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.Floor;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

	Optional<Floor> findByName(String floorName);
}
