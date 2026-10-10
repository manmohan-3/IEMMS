//SHIVAAAHHHHH


package com.iemms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.iemms.entity.MaintenancePriority;

public class MaintenanceTicketRequestDto {

    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;

    private Long alertId;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Priority is required")
    private MaintenancePriority priority;

    private String assignedTechnician;

    public MaintenanceTicketRequestDto() {
    	
    }

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MaintenancePriority getPriority() {
		return priority;
	}

	public void setPriority(MaintenancePriority priority) {
		this.priority = priority;
	}

	public String getAssignedTechnician() {
		return assignedTechnician;
	}

	public void setAssignedTechnician(String assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}
    
}