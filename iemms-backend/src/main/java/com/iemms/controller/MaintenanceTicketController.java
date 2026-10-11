//SHIVAAAAAHHHH


package com.iemms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.AssignTechnicianRequestDto;
import com.iemms.dto.MaintenanceTicketRequestDto;
import com.iemms.dto.MaintenanceTicketResponseDto;
import com.iemms.entity.MaintenanceTicket;
import com.iemms.service.MaintenanceTicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/maintenance-tickets")
public class MaintenanceTicketController {
	
	private final MaintenanceTicketService maintenanceTicketService;
	public MaintenanceTicketController(MaintenanceTicketService maintenanceTicketService) {
		this.maintenanceTicketService=maintenanceTicketService;
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MaintenanceTicketResponseDto createTicket(@Valid @RequestBody MaintenanceTicketRequestDto dto) {
		return maintenanceTicketService.createTicket(dto);
	}
	@GetMapping
	public List<MaintenanceTicketResponseDto> getAllTickets(){
		return maintenanceTicketService.getAllTickets();
	}
	@GetMapping("/{ticketId}")
	public MaintenanceTicketResponseDto getTicketById(@PathVariable Long ticketId) {
		return maintenanceTicketService.getTicketById(ticketId);
	}
	@GetMapping("/equipment/{equipmentId}")
	public List<MaintenanceTicketResponseDto> getTicketByEquipmentId(@PathVariable Long equipmentId){
		return maintenanceTicketService.getAllTickets();
	}
	@PutMapping("/{ticketId}/assign")
	public MaintenanceTicketResponseDto assignTechnician(@PathVariable Long ticketId,@Valid @RequestBody AssignTechnicianRequestDto dto) {
//		System.out.println("Assign called");
		return maintenanceTicketService.assignTechnician(ticketId, dto);
	}
	@PutMapping("/{ticketId}/start")
	public MaintenanceTicketResponseDto startMaintenance(@PathVariable Long ticketId) {
		return maintenanceTicketService.startMaintenance(ticketId);
	}
	@PutMapping("/{ticketId}/complete")
	public MaintenanceTicketResponseDto completeMaintenance(@PathVariable Long ticketId) {
		return maintenanceTicketService.completeMaintenance(ticketId);
	}
	@PutMapping("/{ticketId}/cancel")
	public MaintenanceTicketResponseDto cancelMaintenance(@PathVariable Long ticketId) {
		return maintenanceTicketService.cancelMaintenance(ticketId);
	}

}
