//SHIVAAAHHHH


package com.iemms.dto;

import java.time.LocalDateTime;

import com.iemms.entity.MaintenancePriority;
import com.iemms.entity.MaintenanceStatus;

public class MaintenanceTicketResponseDto {

    private Long Id;
    private String ticketNumber;

    private Long equipmentId;
    private String equipmentCode;

    private Long alertId;

    private String title;
    private String description;

    private MaintenancePriority priority;
    private MaintenanceStatus status;

    private String assignedTechnician;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    
    public MaintenanceTicketResponseDto() {
    	
    }

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
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

	public MaintenanceStatus getStatus() {
		return status;
	}

	public void setStatus(MaintenanceStatus status) {
		this.status = status;
	}

	public String getAssignedTechnician() {
		return assignedTechnician;
	}

	public void setAssignedTechnician(String assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}
    

}