//SHIVAAAHHHH


package com.iemms.service;
import com.iemms.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Equipment> getAllEquipment(int page,int size,String sortBy,String direction,String status){
		if(page<0) {
			throw new IllegalArgumentException("Page number must be zero or greater than zero");
		}
		if(size<=0) {
			throw new IllegalArgumentException("Page size must be greater then zero");
		}
		if(size>100) {
			throw new IllegalArgumentException("Page size must not exceed 100");
		}
		if(!sortBy.equals("name")&&!sortBy.equals("installationDate")) {
			throw new IllegalArgumentException("Sorting must be done by using name and installationDates");
		}
		if(!direction.equalsIgnoreCase("asc")&&!direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("Sorting must be done in ascending or descending order");
		}
		
		Sort sort=direction.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable=PageRequest.of(page, size,sort);
		if(status!=null && !status.isBlank()) {
			status=status.trim().toUpperCase(java.util.Locale.ROOT);
			if(!status.equals("ACTIVE")&&!status.equals("INACTIVE")&&!status.equals("MAINTENANCE")) {
				throw new IllegalArgumentException("Invalid equipment status.. allowed ACTIVE,INACTIVE,MAINTENANCE");
			}
			return equipmentRepository.findByStatus(status,pageable);
			
		}
		return equipmentRepository.findAll(pageable);
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
