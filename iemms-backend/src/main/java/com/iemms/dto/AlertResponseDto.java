//SHIVAAAHHHHH


package com.iemms.dto;

import java.time.LocalDateTime;

import com.iemms.entity.AlertSeverity;
import com.iemms.entity.AlertStatus;
import com.iemms.entity.ThresholdParameter;

public class AlertResponseDto {
	
	private Long Id;
	private Long equipmentId;
	private String equipmentCode;
	private Long readingId;
	private ThresholdParameter parameter;
	private Double actualValue;
	private Double thresholdValue;
	private AlertSeverity severity;
	private AlertStatus status;
	private String message;
	private LocalDateTime createdAt;
	
	public AlertResponseDto() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public Long getReadingId() {
		return readingId;
	}

	public void setReadingId(Long readingId) {
		this.readingId = readingId;
	}

	public ThresholdParameter getParameter() {
		return parameter;
	}

	public void setParameter(ThresholdParameter parameter) {
		this.parameter = parameter;
	}

	public Double getActualValue() {
		return actualValue;
	}

	public void setActualValue(Double actualValue) {
		this.actualValue = actualValue;
	}

	public Double getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(Double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public AlertSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(AlertSeverity severity) {
		this.severity = severity;
	}

	public AlertStatus getStatus() {
		return status;
	}

	public void setStatus(AlertStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

}
