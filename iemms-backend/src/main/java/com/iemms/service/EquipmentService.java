//SHIVAAAHHHH


package com.iemms.service;
import com.iemms.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iemms.dto.EquipmentRequestDto;
import com.iemms.entity.Equipment;
import com.iemms.exception.EquipmentNotFoundException;
@Service
public class EquipmentService {
	private final EquipmentRepository equipmentRepository;
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository=equipmentRepository;
	}
	public Equipment saveEquipment(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}
	public List<Equipment> getAllEquipment(){
		return equipmentRepository.findAll();
	}
	public Equipment getEquipmentById(Long Id){
		return equipmentRepository.findById(Id).orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID " + Id + " not found"));
	}
	public Equipment updateEquipment(Long Id,EquipmentRequestDto dto) {
		Equipment equipment=this.getEquipmentById(Id);
		equipment.setEquipmentCode(dto.getEquipmentCode());
		equipment.setName(dto.getEquipmentName());
		equipment.setType(dto.getType());
		equipment.setManufacturer(dto.getManufacturer());
		equipment.setModel(dto.getModel());
		equipment.setLocation(dto.getLocation());
		equipment.setInstallationDate(dto.getInstallationDate());
		equipment.setStatus(dto.getStatus());
		equipment.setDescription(dto.getDescription());
		return equipmentRepository.save(equipment);
		
	}
	public String deleteEquipment(Long Id){
		Equipment equipment=this.getEquipmentById(Id);
		equipment.setStatus("INACTIVE");
		equipmentRepository.save(equipment);
		return "Equipment with ID "+Id+" made INACTIVE";
	}

}
