package com.staunch.tech.repository;

import com.staunch.tech.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByFacilityCode(String facilityCode);
    Optional<Location> findByFacilityName(String facilityName);
}
