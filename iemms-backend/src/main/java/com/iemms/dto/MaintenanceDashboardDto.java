//SHIVAAAAHHHHH


package com.iemms.dto;

public class MaintenanceDashboardDto {
	private Long totalTicket;
	private Long openTicket;
	private Long assignedTicket;
	private Long inprogressTicket;
	private Long completedTicket;
	private Long cancelledTicket;
	
	public MaintenanceDashboardDto() {
		
	}

	public Long getTotalTicket() {
		return totalTicket;
	}

	public void setTotalTicket(Long totalTicket) {
		this.totalTicket = totalTicket;
	}

	public Long getOpenTicket() {
		return openTicket;
	}

	public void setOpenTicket(Long openTicket) {
		this.openTicket = openTicket;
	}

	public Long getAssignedTicket() {
		return assignedTicket;
	}

	public void setAssignedTicket(Long assignedTicket) {
		this.assignedTicket = assignedTicket;
	}

	public Long getInprogressTicket() {
		return inprogressTicket;
	}

	public void setInprogressTicket(Long inprogressTicket) {
		this.inprogressTicket = inprogressTicket;
	}

	public Long getCompletedTicket() {
		return completedTicket;
	}

	public void setCompletedTicket(Long completedTicket) {
		this.completedTicket = completedTicket;
	}

	public Long getCancelledTicket() {
		return cancelledTicket;
	}

	public void setCancelledTicket(Long cancelledTicket) {
		this.cancelledTicket = cancelledTicket;
	}
	
}
