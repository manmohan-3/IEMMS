//SHIVAAAAHHH

package com.iemms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iemms.entity.EquipmentReading;

@Repository
public interface EquipmentReadingRepository extends JpaRepository<EquipmentReading, Long> {
	List<EquipmentReading> findByEquipmentId(Long equipmentId);

}
