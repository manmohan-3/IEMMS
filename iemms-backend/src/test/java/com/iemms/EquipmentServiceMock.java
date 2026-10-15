////SHIVAAAAHHHhH
//
//
//package com.iemms;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.jspecify.annotations.Nullable;
//import org.junit.jupiter.api.Test;
//
//import com.iemms.dto.EquipmentRequestDto;
//import com.iemms.entity.Equipment;
//import com.iemms.exception.EquipmentNotFoundException;
//import com.iemms.repository.EquipmentRepository;
//import com.iemms.service.EquipmentService;
//
//
//
//public class EquipmentServiceMock {
//	
//	@Test
//	public void EquipmentTest() {
//		EquipmentRepository equipmentRepository=mock(EquipmentRepository.class);
//		EquipmentService equipmentService=new EquipmentService(equipmentRepository);
//		Equipment equipment =null;
//		when(equipmentRepository.save(equipment)).thenReturn(null);
//		Equipment result=equipmentService.saveEquipment(equipment);
//		assertSame(equipment,result);
//		verify(equipmentRepository).save(equipment);
//	}
//	@Test
//	public void getAllEquipmentsTest() {
//		
//		EquipmentRepository equipmentRepository=mock(EquipmentRepository.class);
//		Equipment equipment1=new Equipment();
//		Equipment equipment2=new Equipment();
//		List<Equipment> equipmentList=List.of(equipment1,equipment2);
//		EquipmentService equipmentService=new EquipmentService(equipmentRepository);
//		when(equipmentRepository.findAll()).thenReturn(equipmentList);
//		List<Equipment> result=equipmentService.getAllEquipment();
//		assertSame(equipmentList,result);
//		verify(equipmentRepository).findAll();	
//	}
//	@Test
//	public void getEquipmentByIdTest() {
//		EquipmentRepository equipmentRepository=mock(EquipmentRepository.class);
//		Equipment equipment=new Equipment();
//		Long eId=(long) 1;
//		EquipmentService equipmentService=new EquipmentService(equipmentRepository);
//		when(equipmentRepository.findById(eId)).thenReturn(Optional.of(equipment));
//		Equipment result=equipmentService.getEquipmentById(eId);
//		assertSame(equipment,result);
//		verify(equipmentRepository).findById(eId);
//	}
//	@Test
//	public void throwExceptionTest() {
//		EquipmentRepository equipmentRepository=mock(EquipmentRepository.class);
//		Equipment equipment=new Equipment();
//		Long eid=100L;
//		EquipmentService  equipmentService=new EquipmentService(equipmentRepository);
//		when(equipmentRepository.findById(eid)).thenReturn(Optional.empty());
//		EquipmentNotFoundException exception=assertThrows(EquipmentNotFoundException.class, ()->equipmentService.getEquipmentById(eid));
//		assertEquals("Equipment with ID " + eid + " not found",exception.getMessage());
//	}
//	@Test
//	public void updateEquipmentTest() {
//		EquipmentRepository equipmentRepository=mock(EquipmentRepository.class);
//		Equipment equipment=new Equipment();
//		Long eid=1L;
//		EquipmentService service=new EquipmentService(equipmentRepository);
//		EquipmentRequestDto dto=new EquipmentRequestDto();
//		when(equipmentRepository.findById(eid)).thenReturn(Optional.of(equipment));
//		when(equipmentRepository.save(equipment)).thenReturn(equipment);
//		Equipment result=service.updateEquipment(eid, dto);
//		assertSame(equipment,result);
//
//	}
//	@Test
//	public void deleteEquipmentTest() {
//		EquipmentRepository er=mock(EquipmentRepository.class);
//		Equipment e=new Equipment();
//		Long id=1L;
//		EquipmentService es=new EquipmentService(er);
//		when(er.findById(id)).thenReturn(Optional.of(e));
//		when(er.save(e)).thenReturn(e);
//		String result=es.deleteEquipment(id);
//		assertEquals("Equipment with ID "+id+" made INACTIVE",result);
//		
//	}
//
//}
