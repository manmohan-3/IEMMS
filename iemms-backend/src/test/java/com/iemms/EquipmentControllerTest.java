////SHIVAAAAHHHHH
//
//
//package com.iemms;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import static org.mockito.ArgumentMatchers.eq;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.iemms.controller.EquipmentController;
//import com.iemms.dto.EquipmentRequestDto;
//import com.iemms.entity.Equipment;
//import com.iemms.service.EquipmentService;
//
//@WebMvcTest(EquipmentController.class)
//public class EquipmentControllerTest {
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockitoBean
//	private EquipmentService equipmentService;
//	
//	@Test
//	public void getAllEquipmentsTest() throws Exception {
//		
//		Equipment equip=new Equipment();
//        equip.setEquipmentCode("EQ-001");
//        equip.setName("Motor");
//        equip.setStatus("ACTIVE");
//        
//        when(equipmentService.getAllEquipment()).thenReturn(List.of(equip));
//        mockMvc.perform(get("/api/equipment"))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].equipmentCode").value("EQ-001"))
//        .andExpect(jsonPath("$[0].name").value("Motor"))
//        .andExpect(jsonPath("$[0].status").value("ACTIVE"));
//        verify(equipmentService).getAllEquipment();
//	}
//	@Test
//	public void getEquipmentByIdTest() throws Exception{
//		Equipment equip=new Equipment();
//		equip.setEquipmentCode("EQ-001");
//		equip.setName("Motor");
//		equip.setStatus("ACTIVE");
//		Long id=1L;
//		when(equipmentService.getEquipmentById(id)).thenReturn(equip);
//		mockMvc.perform(get("/api/equipment/1")).andExpect(status().isOk()).andExpect(jsonPath("equipmentCode").value("EQ-001"))
//		.andExpect(jsonPath("name").value("Motor")).andExpect(jsonPath("status").value("ACTIVE"));
//		verify(equipmentService).getEquipmentById(id);
//		
//	}
//	@Test
//	public void updateEquipmentTest() throws Exception{
//		Equipment equip=new Equipment();
//		equip.setEquipmentCode("EQ-001");
//		equip.setName("Motor2");
//		equip.setStatus("ACTIVE");
//		Long id=1L;
////		EquipmentRequestDto dto=new EquipmentRequestDto();
////		dto.setEquipmentCode("EQ-001");
////		dto.setEquipmentName("Motor");
////		dto.setStatus("ACTIVE");
//		when(equipmentService.updateEquipment(org.mockito.ArgumentMatchers.eq(id),any(EquipmentRequestDto.class))).thenReturn(equip);
//	    String requestBody = """
//	            {
//	                "equipmentCode": "EQ-001",
//	                "equipmentName": "Motor",
//	                "status": "ACTIVE"
//	            }
//	            """;
//		mockMvc.perform(put("/api/equipment/1").contentType(APPLICATION_JSON).content(requestBody)).andExpect(status().isOk()).andExpect(jsonPath("$.equipmentCode").value("EQ-001"))
//		.andExpect(jsonPath("$.name").value("Motor2")).andExpect(jsonPath("$.status").value("ACTIVE"));
//		verify(equipmentService).updateEquipment(org.mockito.ArgumentMatchers.eq(id),any(EquipmentRequestDto.class));
//	}
//
//}
