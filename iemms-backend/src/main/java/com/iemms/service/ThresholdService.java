//SHIVAAAHHHHHH


package com.iemms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iemms.dto.ThresholdRequestDto;
import com.iemms.entity.Equipment;
import com.iemms.entity.Threshold;
import com.iemms.repository.EquipmentRepository;
import com.iemms.repository.ThresholdRepository;
import com.iemms.exception.EquipmentNotFoundException;

@Service
public class ThresholdService {
	
	private final EquipmentRepository equipmentRepository;
	private final ThresholdRepository thresholdRepository;
	public ThresholdService(EquipmentRepository equipmentRepository,ThresholdRepository thresholdRepository) {
		this.equipmentRepository=equipmentRepository;
		this.thresholdRepository=thresholdRepository;
	}
	public Threshold saveThreshold(Long equipmentId, ThresholdRequestDto dto) { 
		//before adding to database(threshold values) first we need to check whether the equipment is registered or not...
//		System.out.println("Service layer");
		Equipment equipment=equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException(""
				+ "Equipment with ID "+ equipmentId+" not found"));
		//checking whether the warning limit is greater than critical limit...
		if(dto.getWarningLimit()>=dto.getCriticalLimit()) {
			throw new IllegalArgumentException("Warning limit must be greater than critical limit");
		}
		//checking whether the parameter for specific equipment is saved or not...
		boolean exists=thresholdRepository.existsByEquipmentIdAndParameter(equipmentId, dto.getParameter());
		if(exists) {
			throw new IllegalArgumentException("The parameter "+dto.getParameter()+" already exists for this equipment");
		}
		Threshold threshold=new Threshold();
		threshold.setEquipment(equipment);
		threshold.setParameter(dto.getParameter());
		threshold.setWarningLimit(dto.getWarningLimit());
		threshold.setCriticalLimit(dto.getCriticalLimit());
		return thresholdRepository.save(threshold);
		
		
	}
	public List<Threshold> getThreshold(Long equipmentId){
		//First check whether the equipment is there or not...
		Equipment equipment=equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException(""
				+ "Equioment with ID "+equipmentId+" not found"));
		
		return thresholdRepository.findByEquipmentId(equipmentId);		
		
	}
	public Threshold updateThreshold(Long equipmentId,Long thresholdId, ThresholdRequestDto dto) {
		//First check for Equipment
		equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID "+
		equipmentId+" not found"));
		//check for threshold for give equipment
		Threshold threshold=thresholdRepository.findByIdAndEquipmentId(thresholdId,equipmentId).orElseThrow(() -> new IllegalArgumentException(
				"Threshold Id "+thresholdId+" not found for equipmentId "+equipmentId));
		//validate the warning and critical limits
		if(dto.getWarningLimit()>=dto.getCriticalLimit()) {
			throw new IllegalArgumentException("Warning limit must be smaller than critical limit");
		}
		//check for threshold duplication other than passed threshold...
		boolean exists=thresholdRepository.existsByEquipmentIdAndParameterAndIdNot(equipmentId,dto.getParameter(), thresholdId);
		if(exists) {
			throw new IllegalArgumentException("The parameter "+dto.getParameter()+" already exists for this equipment");
		}
		
		threshold.setParameter(dto.getParameter());
		threshold.setWarningLimit(dto.getWarningLimit());
		threshold.setCriticalLimit(dto.getCriticalLimit());
		return thresholdRepository.save(threshold);		
		
		
	}
	public String deleteThreshold(Long equipmentId,Long thresholdId) {
		//First check for the equipment
		equipmentRepository.findById(equipmentId).orElseThrow(()-> new EquipmentNotFoundException("Equipment with ID "+equipmentId+" not found"));
		//Check for threshold id for that equipment
		Threshold threshold=thresholdRepository.findByIdAndEquipmentId(thresholdId,equipmentId).orElseThrow(()->new IllegalArgumentException(
				"ThresholdId "+thresholdId+" not found for equipmentID "+equipmentId));
		thresholdRepository.delete(threshold);
		return "Threshold with Id "+thresholdId+" and for equipmentId "+equipmentId+" successfully removed";
		
	}
	

}
