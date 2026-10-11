//SHIVAAAHHHH


package com.iemms.service;


import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Service;

import com.iemms.repository.MaintenanceTicketRepository;
import com.iemms.repository.EquipmentRepository;
import com.iemms.dto.AssignTechnicianRequestDto;
import com.iemms.dto.MaintenanceTicketRequestDto;
import com.iemms.dto.MaintenanceTicketResponseDto;
import com.iemms.entity.Alert;
import com.iemms.entity.AlertStatus;
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
    	ticket.setTicketNumber(generateTicketNumber());
    	ticket=maintenanceTicketRepository.save(ticket);
    	
//    	ticket=maintenanceTicketRepository.save(ticket);
    	MaintenanceTicketResponseDto maintenanceTicketResponseDto=convertToDto(ticket);
    	
    	return maintenanceTicketResponseDto;
    }
    private String generateTicketNumber() {
        return "MT-" + Year.now().getValue() + "-"+ UUID.randomUUID().toString().substring(0, 8);
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
    public List<MaintenanceTicketResponseDto> getAllTickets(){
    	return maintenanceTicketRepository.findAll().stream().map(this::convertToDto).toList();
    }
    public MaintenanceTicketResponseDto getTicketById(Long ticketId) {
    	MaintenanceTicket ticket=maintenanceTicketRepository.findById(ticketId).orElseThrow(()-> new IllegalArgumentException(""
    			+ "Ticket with ID "+ticketId+" not found"));
    	
    	return convertToDto(ticket);
    }
    public List<MaintenanceTicketResponseDto> getTicketByEquipmentId(Long equipmentId){
    	equipmentRepository.findById(equipmentId).orElseThrow(()->new EquipmentNotFoundException("Equipment with ID "+equipmentId+" not found"));
    	return maintenanceTicketRepository.findByEquipmentId(equipmentId).stream().map(this::convertToDto).toList();
    }
    public MaintenanceTicketResponseDto assignTechnician(Long ticketId,AssignTechnicianRequestDto dto) {
    	MaintenanceTicket ticket=maintenanceTicketRepository.findById(ticketId).orElseThrow(()->new IllegalArgumentException(""
    			+ "MaintenanceTicket with ID "+ticketId+" not found"));
    	ticket.setAssignedTechnician(dto.getTechnicianName());
    	ticket.setStatus(MaintenanceStatus.ASSIGNED);
    	ticket=maintenanceTicketRepository.save(ticket);
    	return convertToDto(ticket);
    }
    public MaintenanceTicketResponseDto startMaintenance(Long ticketId) {
    	MaintenanceTicket ticket=maintenanceTicketRepository.findById(ticketId).orElseThrow(()->new IllegalArgumentException(""
    			+ "MaintenanceTicket With ID "+ticketId+" not found"));
    	if(ticket.getStatus()!=MaintenanceStatus.ASSIGNED) {
    		throw new IllegalArgumentException("Only ASSIGNED tickets can be started");
    	}
    	ticket.setStatus(MaintenanceStatus.IN_PROGRESS);
    	ticket=maintenanceTicketRepository.save(ticket);
    	return convertToDto(ticket);
    }
    public MaintenanceTicketResponseDto completeMaintenance(Long ticketId) {
    	MaintenanceTicket ticket= maintenanceTicketRepository.findById(ticketId).orElseThrow(()->new IllegalArgumentException(""
    			+ "MaintenanceTicket With ID "+ticketId+" not found"));
    	if(ticket.getStatus()!=MaintenanceStatus.IN_PROGRESS) {
    		throw new IllegalArgumentException("Only STARTED tickets can be completed");
    	}
    	if(ticket.getAlert()!=null) {
    		Alert alert=ticket.getAlert();
    		
    		alert.setStatus(AlertStatus.RESOLVED);
    		alertRepository.save(alert);
    	}
    	ticket.setStatus(MaintenanceStatus.COMPLETED);
    	ticket.setCompletedAt(LocalDateTime.now());
    	ticket=maintenanceTicketRepository.save(ticket);
    	return convertToDto(ticket);
    }
    public MaintenanceTicketResponseDto cancelMaintenance(Long ticketId) {
    	MaintenanceTicket ticket= maintenanceTicketRepository.findById(ticketId).orElseThrow(()->new IllegalArgumentException(""
    			+ "MaintenanceTicket With ID "+ticketId+" not found"));
    	if(ticket.getStatus()==MaintenanceStatus.COMPLETED||ticket.getStatus()==MaintenanceStatus.CANCELLED) {
    		throw new IllegalArgumentException("COMPLETED or already CANCELLED tickets cannot be cancelled");
    	}
    	ticket.setStatus(MaintenanceStatus.CANCELLED);
    	ticket=maintenanceTicketRepository.save(ticket);
    	return convertToDto(ticket);
    }
    
    
    
    
    
    
    
    
    
    
}