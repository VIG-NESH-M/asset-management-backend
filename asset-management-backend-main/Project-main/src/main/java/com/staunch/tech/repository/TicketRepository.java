package com.staunch.tech.repository;

import com.staunch.tech.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByEmployeeId(int employeeId);
    List<Ticket> findByUuid(String uuid);

    List<Ticket> findByStatus(String status);
    
}
