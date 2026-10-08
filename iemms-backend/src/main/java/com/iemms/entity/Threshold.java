//SHIVAAAAAHHHH

package com.iemms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Threshold {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	
	@Enumerated(EnumType.STRING)
	private ThresholdParameter parameter;
	
	private Double warningLimit;
	private Double criticalLimit;
	
	public Threshold() {
		
	}

	public Long getId() {
		return Id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public ThresholdParameter getParameter() {
		return parameter;
	}

	public void setParameter(ThresholdParameter parameter) {
		this.parameter = parameter;
	}

	public Double getWarningLimit() {
		return warningLimit;
	}

	public void setWarningLimit(Double warningLimit) {
		this.warningLimit = warningLimit;
	}

	public Double getCriticalLimit() {
		return criticalLimit;
	}

	public void setCriticalLimit(Double criticalLimit) {
		this.criticalLimit = criticalLimit;
	}

	
	
	

}
