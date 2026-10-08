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
	
	private double temperature;
	private double voltage;
	private double current;
	private double vibration;
	private double powerFactor;
	private LocalDateTime readingTime;
	
	public EquipmentReading() {
		
	}
	public void setEquipment(Equipment equipment) {
		this.equipment=equipment;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setTemperature(double temperature) {
		this.temperature=temperature;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setVoltage(double voltage) {
		this.voltage=voltage;
	}
	public double getVoltage() {
		return voltage;
	}
	public void setCurrent(double current) {
		this.current=current;
	}
	public double getCurrent() {
		return current;
	}
	public void setVibration(double vibration) {
		this.vibration=vibration;
	}
	public double getVibration() {
		return vibration;
	}
	public void setPowerFactor(double powerFactor) {
		this.powerFactor=powerFactor;
	}
	public double getPowerFactor() {
		return powerFactor;
	}
	public void setReadingTime(LocalDateTime readingTime) {
		this.readingTime=readingTime;
	}
	public LocalDateTime getReadingTime() {
		return readingTime;
	}
	
	
	
	
	

}
