//SHIVAAAAAAHHHH


package com.iemms.dto;

import com.iemms.entity.ThresholdParameter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ThresholdRequestDto {
	
	@NotNull(message="Parameter is required")
	private ThresholdParameter parameter;
	
	@NotNull(message="Warning limit is required")
	@Positive(message="Warning limit must be positive")
	private Double warningLimit;
	
	@NotNull(message="Critical limit is required")
	@Positive(message="Critical Limit must be positive")
	private Double criticalLimit;
	
	public ThresholdRequestDto() {
		
	}

	public ThresholdParameter getParameter() {
		return parameter;
	}

	public void setParameter(ThresholdParameter parameter) {
		this.parameter = parameter;
	}

	public Double getWarningLimit() {
		return warningLimit;
	}

	public void setWarningLimit(Double warningLimit) {
		this.warningLimit = warningLimit;
	}

	public Double getCriticalLimit() {
		return criticalLimit;
	}

	public void setCriticalLimit(Double criticalLimit) {
		this.criticalLimit = criticalLimit;
	}
	
	
	

}
