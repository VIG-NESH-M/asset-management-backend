package com.staunch.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.Reports3D;

@Repository
public interface Reports3DRepository extends JpaRepository<Reports3D, Integer> {

	List<Reports3D> findAllByType(String type);

}
