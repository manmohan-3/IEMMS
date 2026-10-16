//SHIVAAAAHHH


package com.iemms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iemms.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
	long countByStatus(String status);
	Page<Equipment> findByStatus(String status,Pageable pegeable);
}
