//SHIVAVHHHHH

package com.iemms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.EquipmentReadingRequestDto;
import com.iemms.entity.EquipmentReading;
import com.iemms.service.EquipmentReadingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment/{equipmentId}/readings")
public class EquipmentReadingController {
	private final EquipmentReadingService equipmentReadingService;
	public EquipmentReadingController(EquipmentReadingService equipmentReadingService) {
		this.equipmentReadingService=equipmentReadingService;
	}
	@PostMapping
	public EquipmentReading saveEquipmentReading(@PathVariable Long equipmentId, @RequestBody @Valid EquipmentReadingRequestDto dto) {
		return equipmentReadingService.saveEquipmentReading(equipmentId, dto);
	}
	@GetMapping
	public List<EquipmentReading> getEquipmentReadingById(@PathVariable Long equipmentId) {
		return equipmentReadingService.getEquipmentReadingById(equipmentId);
	}
	

}
