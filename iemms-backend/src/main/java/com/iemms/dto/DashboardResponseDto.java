//SHIVAAAAHHHHHH


package com.iemms.dto;

public class DashboardResponseDto {
	private EquipmentDashboardDto equipment;
	private AlertDashboardDto alert;
	private MaintenanceDashboardDto maintenance;
	
	public DashboardResponseDto() {
		
	}

	public EquipmentDashboardDto getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentDashboardDto equipment) {
		this.equipment = equipment;
	}

	public AlertDashboardDto getAlert() {
		return alert;
	}

	public void setAlert(AlertDashboardDto alert) {
		this.alert = alert;
	}

	public MaintenanceDashboardDto getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(MaintenanceDashboardDto maintenance) {
		this.maintenance = maintenance;
	}
	
}
