//SHIVAAAHHHHH


package com.iemms.service;

import org.springframework.stereotype.Service;

import com.iemms.dto.AlertDashboardDto;
import com.iemms.dto.DashboardResponseDto;
import com.iemms.dto.EquipmentDashboardDto;
import com.iemms.dto.MaintenanceDashboardDto;
import com.iemms.entity.AlertSeverity;
import com.iemms.entity.AlertStatus;
import com.iemms.entity.MaintenanceStatus;
import com.iemms.repository.AlertRepository;
import com.iemms.repository.EquipmentRepository;
import com.iemms.repository.MaintenanceTicketRepository;

@Service
public class DashboardService {
	
	private final EquipmentRepository equipmentRepository;
	private final AlertRepository alertRepository;
	private final MaintenanceTicketRepository maintenanceRepository;
	
	public DashboardService(EquipmentRepository equipmentRepository,AlertRepository alertRepository,MaintenanceTicketRepository
			maintenanceRepository) {
		this.equipmentRepository=equipmentRepository;
		this.alertRepository=alertRepository;
		this.maintenanceRepository=maintenanceRepository;
	}
	public EquipmentDashboardDto equipmentDashboard() {
		EquipmentDashboardDto dto=new EquipmentDashboardDto();
		dto.setTotalEquipment(equipmentRepository.count());
		dto.setActiveEquipment(equipmentRepository.countByStatus("ACTIVE"));
		dto.setInactiveEquipment(equipmentRepository.countByStatus("INACTIVE"));
		dto.setMaintenanceEquipment(equipmentRepository.countByStatus("MAINTENANCE"));
		return dto;
	}
	public AlertDashboardDto alertDashboard() {
		AlertDashboardDto dto=new AlertDashboardDto();
		dto.setTotalAlerts(alertRepository.count());
		dto.setCriticalAlerts(alertRepository.countBySeverity(AlertSeverity.CRITICAL));
		dto.setOpenAlerts(alertRepository.countByStatus(AlertStatus.OPEN));
		dto.setResolvedAlerts(alertRepository.countByStatus(AlertStatus.RESOLVED));
		return dto;	
		
	}
	public MaintenanceDashboardDto maintenanceDashboard() {
		MaintenanceDashboardDto dto=new MaintenanceDashboardDto();
		dto.setTotalTicket(maintenanceRepository.count());
		dto.setOpenTicket(maintenanceRepository.countByStatus(MaintenanceStatus.OPEN));
		dto.setAssignedTicket(maintenanceRepository.countByAssignedTechnicianIsNotNull());
		dto.setInprogressTicket(maintenanceRepository.countByStatus(MaintenanceStatus.IN_PROGRESS));
		dto.setCompletedTicket(maintenanceRepository.countByStatus(MaintenanceStatus.COMPLETED));
		dto.setCancelledTicket(maintenanceRepository.countByStatus(MaintenanceStatus.CANCELLED));
		return dto;
	}
	public DashboardResponseDto getDashboard() {
		DashboardResponseDto dto=new DashboardResponseDto();
		dto.setEquipment(equipmentDashboard());
		dto.setAlert(alertDashboard());
		dto.setMaintenance(maintenanceDashboard());
		return dto;
	}

}
