//SHIVAAAAHHHHH


package com.iemms;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.iemms.controller.EquipmentController;
import com.iemms.entity.Equipment;
import com.iemms.service.EquipmentService;

@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private EquipmentService equipmentService;
	
	@Test
	public void getAllEquipmentsTest() throws Exception {
		
		Equipment equip=new Equipment();
        equip.setEquipmentCode("EQ-001");
        equip.setName("Motor");
        equip.setStatus("ACTIVE");
        
        when(equipmentService.getAllEquipment()).thenReturn(List.of(equip));
        mockMvc.perform(get("/api/equipment"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].equipmentCode").value("EQ-001"))
        .andExpect(jsonPath("$[0].name").value("Motor"))
        .andExpect(jsonPath("$[0].status").value("ACTIVE"));
        verify(equipmentService).getAllEquipment();
	}

}
