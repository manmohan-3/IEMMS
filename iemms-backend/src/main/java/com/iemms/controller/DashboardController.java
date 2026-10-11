//SHIVAAAHHHH


package com.iemms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemms.dto.DashboardResponseDto;
import com.iemms.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	private final DashboardService dashboardService;
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService=dashboardService;
	}
	@GetMapping
	public DashboardResponseDto getDashboard() {
		return dashboardService.getDashboard();
	}

}
