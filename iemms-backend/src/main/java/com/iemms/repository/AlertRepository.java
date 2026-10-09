//SHIVAAAHHHHH


package com.iemms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	List<Alert> findByEquipmentId(Long equipmentId);
}
