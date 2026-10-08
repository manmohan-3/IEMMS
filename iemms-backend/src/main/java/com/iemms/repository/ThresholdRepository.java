//SHIVAAAAHHHHH


package com.iemms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iemms.entity.Threshold;
import com.iemms.entity.ThresholdParameter;
//Here we no need to mention @repository bcuz anyhow the interface is already extending JpaRepository...
public interface ThresholdRepository extends JpaRepository<Threshold, Long>{
	
	boolean existsByEquipmentIdAndParameter(Long equipmentId,ThresholdParameter parameter);
	List<Threshold> findByEquipmentId(Long equipmentId);
	Optional<Threshold> findByIdAndEquipmentId(Long thresholdId,Long equipmentId);
	boolean existsByEquipmentIdAndParameterAndIdNot(Long equipmentId,ThresholdParameter parameter,Long thresholdId);
}
