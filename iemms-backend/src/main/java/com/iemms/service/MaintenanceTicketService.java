//SHIVAAAHHHH


package com.iemms.service;

import java.time.Year;

import org.springframework.stereotype.Service;

import com.iemms.repository.MaintenanceTicketRepository;
import com.iemms.repository.EquipmentRepository;
import com.iemms.dto.MaintenanceTicketRequestDto;
import com.iemms.dto.MaintenanceTicketResponseDto;
import com.iemms.entity.Alert;
import com.iemms.entity.Equipment;
import com.iemms.entity.MaintenanceStatus;
import com.iemms.entity.MaintenanceTicket;
import com.iemms.exception.EquipmentNotFoundException;
import com.iemms.repository.AlertRepository;

@Service
public class MaintenanceTicketService {

    private final MaintenanceTicketRepository maintenanceTicketRepository;
    private final EquipmentRepository equipmentRepository;
    private final AlertRepository alertRepository;

    public MaintenanceTicketService(
            MaintenanceTicketRepository maintenanceTicketRepository,
            EquipmentRepository equipmentRepository,
            AlertRepository alertRepository) {

        this.maintenanceTicketRepository = maintenanceTicketRepository;
        this.equipmentRepository = equipmentRepository;
        this.alertRepository = alertRepository;
    }
    public MaintenanceTicketResponseDto createTicket(MaintenanceTicketRequestDto dto) {
    	Equipment equipment=equipmentRepository.findById(dto.getEquipmentId()).orElseThrow(()->new EquipmentNotFoundException("Equipment with ID "
    			+dto.getEquipmentId()+" not found"));
    	Alert alert=null;
    	if(dto.getAlertId()!=null) {
    		alert=alertRepository.findById(dto.getAlertId()).orElseThrow(()->new IllegalArgumentException("Alert with ID "+
    	dto.getAlertId()+" not found"));
    	}
    	if(!alert.getEquipment().getId().equals(equipment.getId())) {
    		throw new IllegalArgumentException("Provide a valid alertID and equipmentID");
    	}
    	MaintenanceTicket ticket=new MaintenanceTicket();
    	ticket.setEquipment(equipment);
    	ticket.setAlert(alert);
    	ticket.setAssignedTechnician(dto.getAssignedTechnician());
    	ticket.setDescription(dto.getDescription());
    	ticket.setPriority(dto.getPriority());
    	ticket.setStatus(MaintenanceStatus.OPEN);
    	ticket.setTitle(dto.getTitle());
    	ticket=maintenanceTicketRepository.save(ticket);
    	ticket.setTicketNumber(generateTicketNumber(dto.getEquipmentId()));
    	ticket=maintenanceTicketRepository.save(ticket);
    	MaintenanceTicketResponseDto maintenanceTicketResponseDto=convertToDto(ticket);
    	
    	return maintenanceTicketResponseDto;
    }
    private String generateTicketNumber(Long Id) {
    	return String.format("MT-%d-%04d", Year.now().getValue(), Id);
    }
    private MaintenanceTicketResponseDto convertToDto(MaintenanceTicket ticket) {

        MaintenanceTicketResponseDto dto = new MaintenanceTicketResponseDto();

        dto.setId(ticket.getId());
        dto.setTicketNumber(ticket.getTicketNumber());

        dto.setEquipmentId(ticket.getEquipment().getId());
        dto.setEquipmentCode(ticket.getEquipment().getEquipmentCode());

        if (ticket.getAlert() != null) {
            dto.setAlertId(ticket.getAlert().getId());
        }

        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());

        dto.setPriority(ticket.getPriority());
        dto.setStatus(ticket.getStatus());

        dto.setAssignedTechnician(ticket.getAssignedTechnician());

        dto.setCreatedAt(ticket.getCreatedAt());
        dto.setCompletedAt(ticket.getCompletedAt());

        return dto;
    }
}