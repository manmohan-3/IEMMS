//SHIVAAAAHHHHH

package com.iemms.dto;

public class EquipmentReadingRequestDto {

	
	private Long equipmentId;
	private double temperature;
	private double voltage;
	private double current;
	private double vibration;
	private double powerFactor;
	
	public EquipmentReadingRequestDto() {
		
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId=equipmentId;
	}
	public Long getEquipmentId() {
		return equipmentId;
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
}
