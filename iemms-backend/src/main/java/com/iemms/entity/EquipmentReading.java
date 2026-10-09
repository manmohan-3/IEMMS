//SHIVAAAAHHHH

package com.iemms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EquipmentReading {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	
	private Double temperature;
	private Double voltage;
	private Double current;
	private Double vibration;
	private Double powerFactor;
	private LocalDateTime readingTime;
	
	public EquipmentReading() {
		
	}
	public Long getId() {
		return Id;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment=equipment;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setTemperature(Double temperature) {
		this.temperature=temperature;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setVoltage(Double voltage) {
		this.voltage=voltage;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setCurrent(Double current) {
		this.current=current;
	}
	public Double getCurrent() {
		return current;
	}
	public void setVibration(Double vibration) {
		this.vibration=vibration;
	}
	public Double getVibration() {
		return vibration;
	}
	public void setPowerFactor(Double powerFactor) {
		this.powerFactor=powerFactor;
	}
	public Double getPowerFactor() {
		return powerFactor;
	}
	public void setReadingTime(LocalDateTime readingTime) {
		this.readingTime=readingTime;
	}
	public LocalDateTime getReadingTime() {
		return readingTime;
	}
	
	
	
	
	

}
