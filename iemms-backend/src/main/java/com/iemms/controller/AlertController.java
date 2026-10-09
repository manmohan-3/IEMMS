//SHIVAAAHHHHH

package com.iemms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.iemms.repository.AlertRepository;
import com.iemms.service.AlertService;
import com.iemms.dto.AlertResponseDto;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
	private final AlertService alertService;
	public AlertController(AlertService alertService) {
		this.alertService=alertService;
	}
	//Get all alerts
	@GetMapping
	public List<AlertResponseDto> getAllAlerts(){
		return alertService.getAllAlerts();
	}
	@GetMapping("/{alertId}")
	public AlertResponseDto getAlertById(@PathVariable Long alertId) {
		return alertService.getByAlertId(alertId);
	}
	//Get by equipment id
	@GetMapping("/equipment/{equipmentId}")
	public List<AlertResponseDto> getAlertByEquipmentId(@PathVariable Long equipmentId){
		return alertService.getAlertByEquipmentId(equipmentId);
	}
	@PutMapping("/{alertId}/acknowledge")
	public AlertResponseDto acknowledgeAlert(@PathVariable("alertId") Long alertId) {
		return alertService.acknowledgeAlert(alertId);
	}
	@PutMapping("/{alertId}/resolve")
	public AlertResponseDto resolveAlert(@PathVariable("alertId") Long alertId) {

	    return alertService.resolveAlert(alertId);
	}
	

}
