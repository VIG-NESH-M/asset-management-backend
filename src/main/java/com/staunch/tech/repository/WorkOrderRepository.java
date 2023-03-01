package com.staunch.tech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.WorkOrder;
@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {
    List<WorkOrder> findByEmployeeId(int employeeId);


    List<WorkOrder> findByStatus(String status);
    
    Optional<WorkOrder> findByName(String fileName);

}
