package com.staunch.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.Reports2D;

@Repository
public interface Reports2DRepository extends JpaRepository<Reports2D, Integer> {

	List<Reports2D> findAllByType(String type);
}
