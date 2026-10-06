//SHIVAAAAAHHHH

package com.iemms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class EquipmentRequestDto {
	@NotBlank
	private String equipmentCode;
	@NotBlank
	private String equipmentName;
	@NotBlank
	private String type;
	private String manufacturer;
	private String model;
	private String location;
	private LocalDate installationDate;
	@NotBlank
	private String status;
	private String description;
	
	public EquipmentRequestDto() {
		
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode=equipmentCode;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName=equipmentName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer=manufacturer;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setModel(String model) {
		this.model=model;
	}
	public String getModel() {
		return model;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	public String getLocation() {
		return location;
	}
	public void setInstallationDate(LocalDate installationDate) {
		this.installationDate=installationDate;
	}
	public LocalDate getInstallationDate() {
		return installationDate;
	}
	public void getStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
}
