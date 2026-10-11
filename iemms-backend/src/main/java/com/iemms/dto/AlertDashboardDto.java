//SHIVAAAHHHHH


package com.iemms.dto;

public class AlertDashboardDto {
	private Long totalAlerts;
	private Long openAlerts;
	private Long criticalAlerts;
	private Long resolvedAlerts;
	
	public AlertDashboardDto() {
		
	}

	public Long getTotalAlerts() {
		return totalAlerts;
	}

	public void setTotalAlerts(Long totalAlerts) {
		this.totalAlerts = totalAlerts;
	}

	public Long getOpenAlerts() {
		return openAlerts;
	}

	public void setOpenAlerts(Long openAlerts) {
		this.openAlerts = openAlerts;
	}

	public Long getCriticalAlerts() {
		return criticalAlerts;
	}

	public void setCriticalAlerts(Long criticalAlerts) {
		this.criticalAlerts = criticalAlerts;
	}

	public Long getResolvedAlerts() {
		return resolvedAlerts;
	}

	public void setResolvedAlerts(Long resolvedAlerts) {
		this.resolvedAlerts = resolvedAlerts;
	}
	
	
}
