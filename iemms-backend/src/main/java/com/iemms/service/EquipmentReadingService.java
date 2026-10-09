//SHIVAAAHHHH

package com.iemms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iemms.dto.EquipmentReadingRequestDto;
import com.iemms.entity.Equipment;
import com.iemms.entity.EquipmentReading;
import com.iemms.exception.EquipmentNotFoundException;
import com.iemms.repository.EquipmentRepository;
import com.iemms.repository.EquipmentReadingRepository;

@Service
public class EquipmentReadingService {
	private final EquipmentReadingRepository equipmentReadingRepository;
	private final EquipmentRepository equipmentRepository;
	private final AlertService alertService;
	public EquipmentReadingService(EquipmentReadingRepository equipmentReadingRepository,EquipmentRepository equipmentRepository,AlertService alertService) {
		this.alertService=alertService;
		this.equipmentRepository=equipmentRepository;
		this.equipmentReadingRepository=equipmentReadingRepository;
	}
	public EquipmentReading saveEquipmentReading(Long equipmentId,EquipmentReadingRequestDto dto) {
		Equipment equipment=equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID " + equipmentId + " not found"));

		EquipmentReading equipmentReading=new EquipmentReading();
		equipmentReading.setEquipment(equipment);
		equipmentReading.setTemperature(dto.getTemperature());
		equipmentReading.setVoltage(dto.getVoltage());
		equipmentReading.setCurrent(dto.getCurrent());
		equipmentReading.setVibration(dto.getVibration());
		equipmentReading.setPowerFactor(dto.getPowerFactor());
		equipmentReading.setReadingTime(LocalDateTime.now());
		EquipmentReading savedReading=equipmentReadingRepository.save(equipmentReading);
		alertService.generateAlert(savedReading);
		return savedReading;
	}
	public List<EquipmentReading> getEquipmentReadingById(Long equipmentId) {
		equipmentRepository.findById(equipmentId).orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID " + equipmentId + " not found"));
		return equipmentReadingRepository.findByEquipmentId(equipmentId);
	}

}
