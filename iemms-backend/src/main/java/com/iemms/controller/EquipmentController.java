//SHIVAAAHHHHH

package com.iemms.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.entity.Equipment;
import com.iemms.service.EquipmentService;

import jakarta.validation.Valid;

import com.iemms.dto.EquipmentRequestDto;


@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
	private final EquipmentService equipmentService;
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService=equipmentService;
	}
	@PostMapping
	public Equipment saveEquipment(@RequestBody @Valid EquipmentRequestDto dto) {
		Equipment equipment=new Equipment();
		equipment.setEquipmentCode(dto.getEquipmentCode());
		equipment.setName(dto.getEquipmentName());
		equipment.setType(dto.getType());
		equipment.setManufacturer(dto.getManufacturer());
		equipment.setModel(dto.getModel());
		equipment.setLocation(dto.getLocation());
		equipment.setInstallationDate(dto.getInstallationDate());
		equipment.setStatus(dto.getStatus());
		equipment.setDescription(dto.getDescription());
		return equipmentService.saveEquipment(equipment);
	}
	@GetMapping
	public List<Equipment> getAllEquipment(){
		return equipmentService.getAllEquipment();
	}
	@GetMapping("/{Id}")
	public Equipment getEquipmentById(@PathVariable Long Id) {
		return equipmentService.getEquipmentById(Id);
	}
	@PutMapping("/{Id}")
	public Equipment updateEquipment(@PathVariable Long Id, @Valid @RequestBody EquipmentRequestDto dto) {
		return equipmentService.updateEquipment(Id,dto);
	}
	@DeleteMapping("/{Id}")
	public ResponseEntity<String> deleteEquipment(@PathVariable Long Id) {
		String message=equipmentService.deleteEquipment(Id);
		return ResponseEntity.status(200).body(message);
	}

}
