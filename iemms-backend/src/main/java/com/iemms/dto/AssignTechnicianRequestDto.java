//SHIVAAAHHHHHHH


package com.iemms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AssignTechnicianRequestDto {
	@NotBlank(message="Technician name must  not be empty")
	@Size(max =100,message="Technician name lenght exceeded 100 ")
	private String technicianName;
	public AssignTechnicianRequestDto() {
		
	}
	public void setTechnicianName(String technicianName) {
		this.technicianName=technicianName;
	}
	public String getTechnicianName() {
		return technicianName;
	}
	

}
