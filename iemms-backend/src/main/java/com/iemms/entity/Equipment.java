//SHIVAAAHHHH

package com.iemms.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String equipmentCode;
	private String name;
	private String type;
	private String manufacturer;
	private String model;
	private String location;
	private LocalDate installationDate;
	private String status;
	private String description;
	
	public Equipment() {
		
	}
	public Long getId() {
		return Id;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode=equipmentCode;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
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
	public void setStatus(String status) {
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
