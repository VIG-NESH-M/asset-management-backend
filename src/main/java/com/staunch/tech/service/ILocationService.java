package com.staunch.tech.service;

import com.staunch.tech.entity.Location;

import java.util.List;

public interface ILocationService {

    Location createFacility(Location facility);
    Location deleteFacility(long facilityId);
    List<Location> getAllFacilities();

}
