//SHIVAAAAHHHHH

package com.iemms.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class EquipmentReadingRequestDto {

	
	private Long equipmentId;
	@NotNull(message="Temperature must not be null")
	@DecimalMin(value ="-273.15" ,message="Temperature must be greater than zero")
	private Double temperature;
	@NotNull(message="Voltage must not be null")
	@DecimalMin(value="0.0" ,message="Voltage must be greater than zero")
	private Double voltage;
	@NotNull(message="Current must not be null")
	@DecimalMin(value="0.0" ,message="Current must be greater than zero")
	private Double current;
	@NotNull(message="Vibration must not be null")
	@DecimalMin(value="0.0" ,message="Voltage must be greater than zero")
	private Double vibration;
	@NotNull(message="Power factor must not be null")
	@DecimalMin(value="0.0" ,message="Power factor must be between 0 and 1")
	@DecimalMax(value="1.0" ,message="Power factor must be between 0 and 1")
	private Double powerFactor;
	
	public EquipmentReadingRequestDto() {
		
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId=equipmentId;
	}
	public Long getEquipmentId() {
		return equipmentId;
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
}
