//SHIVAAAAHHHHHH


package com.iemms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	
	@ManyToOne
	@JoinColumn(name="reading_id")
	private EquipmentReading equipmentReading;
	
	@Enumerated(EnumType.STRING)
	private ThresholdParameter parameter;
	
	private Double actualValue;
	private Double thresholdValue;
	
	@Enumerated(EnumType.STRING)
	private AlertSeverity severity;
	
	@Enumerated(EnumType.STRING)
	private AlertStatus status;
	
	private String message;
	private LocalDateTime createdAt;
	
	public Alert() {
		
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public EquipmentReading getEquipmentReading() {
		return equipmentReading;
	}

	public void setEquipmentReading(EquipmentReading equipmentReading) {
		this.equipmentReading = equipmentReading;
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

	public Long getId() {
		return Id;
	}
	
}
