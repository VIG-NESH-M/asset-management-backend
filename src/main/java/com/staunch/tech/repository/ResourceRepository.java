package com.staunch.tech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.staunch.tech.entity.Resource;
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

	List<Resource> findByWorkOrderId(int workOrderId);
}
