package com.staunch.tech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.staunch.tech.entity.Locations;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> {

	Optional<Locations> findByName(String name);
}
