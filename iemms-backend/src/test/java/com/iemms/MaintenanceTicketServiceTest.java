////SHIVAAAAHHHHHH
//
//
//package com.iemms;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import com.iemms.dto.MaintenanceTicketResponseDto;
//import com.iemms.entity.MaintenanceTicket;
//import com.iemms.repository.AlertRepository;
//import com.iemms.repository.EquipmentRepository;
//import com.iemms.repository.MaintenanceTicketRepository;
//import com.iemms.service.MaintenanceTicketService;
//
//public class MaintenanceTicketServiceTest {
//	
//	@Test
//	public void getAllTicketsTest() {
//		MaintenanceTicketRepository maintenanceRepo=mock(MaintenanceTicketRepository.class);
//		AlertRepository alertRepo=mock(AlertRepository.class);
//		EquipmentRepository equipmentRepo=mock(EquipmentRepository.class);		
//		MaintenanceTicketService maintenanceService=new MaintenanceTicketService(maintenanceRepo,equipmentRepo,alertRepo);
//		MaintenanceTicketResponseDto dto1=new MaintenanceTicketResponseDto();
//		MaintenanceTicketResponseDto dto2=new MaintenanceTicketResponseDto();
//		List<MaintenanceTicketResponseDto> ticketList=List.of(dto1,dto2);
//		MaintenanceTicket mt1=new MaintenanceTicket();
//		MaintenanceTicket mt2=new MaintenanceTicket();
//		List<MaintenanceTicket> mtList=List.of(mt1,mt2);
//		Integer i=2;
//		when(maintenanceRepo.findAll()).thenReturn(mtList);
//		List<MaintenanceTicketResponseDto> result=maintenanceService.getAllTickets();
//		assertSame(2, (int) result.size());
//	}
//
//}
