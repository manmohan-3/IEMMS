//SHIVAAAHHHHH


package com.iemms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.MaintenanceStatus;
import com.iemms.entity.MaintenanceTicket;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket,Long>{

	MaintenanceTicket save(MaintenanceTicket ticket);
	List<MaintenanceTicket> findByEquipmentId(Long equipmentId);
	long countByStatus(MaintenanceStatus status);
	long countByAssignedTechnicianIsNotNull();
	

}
