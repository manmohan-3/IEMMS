//SHIVAAAAHHH


package com.iemms.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;


@Entity
public class MaintenanceTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message="Ticket number cannot be empty")
	@Column(nullable=false,unique=true,length=30)
	private String ticketNumber;
	@ManyToOne
	@JoinColumn(name="equipment_id",nullable=false)
	private Equipment equipment;
	@ManyToOne
	@JoinColumn(name="alert_id",nullable=true)
	private Alert alert;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private MaintenancePriority priority;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private MaintenanceStatus status=MaintenanceStatus.OPEN;
	private String assignedTechnician;
	@Column(nullable=false,updatable=false)
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;
	
	public MaintenanceTicket() {
		
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
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

	public Long getId() {
		return Id;
	}
	@PrePersist
	protected void onCreate() {
	    if (status == null) {
	        status = MaintenanceStatus.OPEN;
	    }

	    if (createdAt == null) {
	        createdAt = LocalDateTime.now();
	    }
	}

	
	

}
