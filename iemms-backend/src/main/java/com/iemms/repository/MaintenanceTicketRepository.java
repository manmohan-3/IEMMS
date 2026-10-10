//SHIVAAAHHHHH


package com.iemms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.MaintenanceTicket;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket,Long>{

	MaintenanceTicket save(MaintenanceTicket ticket);
	

}
