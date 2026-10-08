//SHIVAAAAHHHH


package com.iemms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.ThresholdRequestDto;
import com.iemms.entity.Threshold;
import com.iemms.service.ThresholdService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment/{equipmentId}/thresholds")
public class ThresholdController {
	
	private ThresholdService thresholdService;
	public ThresholdController(ThresholdService thresholdService) {
		this.thresholdService=thresholdService;
	}
	@PostMapping
	public Threshold saveThreshold(@PathVariable Long equipmentId, @RequestBody @Valid ThresholdRequestDto dto) {
//		System.out.println("Controller layer");
		return thresholdService.saveThreshold(equipmentId, dto);
		
	}
	@GetMapping
	public List<Threshold> getThreshold(@PathVariable Long equipmentId) {
		return thresholdService.getThreshold(equipmentId);
	}
	@PutMapping("/{thresholdId}")
	public Threshold updateThreshold(@PathVariable Long equipmentId, @PathVariable Long thresholdId, @RequestBody @Valid ThresholdRequestDto dto) {
		return thresholdService.updateThreshold(equipmentId,thresholdId, dto);
	}
	@DeleteMapping("/{thresholdId}")
	public String deleteThreshold(@PathVariable Long equipmentId,@PathVariable Long thresholdId) {
		return thresholdService.deleteThreshold(equipmentId, thresholdId);
	}

}
