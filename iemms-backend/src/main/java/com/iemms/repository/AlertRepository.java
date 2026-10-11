//SHIVAAAHHHHH


package com.iemms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.Alert;
import com.iemms.entity.AlertSeverity;
import com.iemms.entity.AlertStatus;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	List<Alert> findByEquipmentId(Long equipmentId);
	long countByStatus(AlertStatus status);
	long countBySeverity(AlertSeverity severity);
}
