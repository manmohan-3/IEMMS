//SHIVAAAAHHHH

package com.iemms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iemms.dto.AlertResponseDto;
import com.iemms.entity.Alert;
import com.iemms.entity.AlertSeverity;
import com.iemms.entity.AlertStatus;
import com.iemms.entity.EquipmentReading;
import com.iemms.entity.Threshold;
import com.iemms.entity.ThresholdParameter;
import com.iemms.exception.EquipmentNotFoundException;
import com.iemms.repository.AlertRepository;
import com.iemms.repository.EquipmentRepository;
import com.iemms.repository.ThresholdRepository;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final ThresholdRepository thresholdRepository;
    private final EquipmentRepository equipmentRepository;

    public AlertService(AlertRepository alertRepository,ThresholdRepository thresholdRepository,EquipmentRepository equipmentRepository) {

        this.alertRepository = alertRepository;
        this.thresholdRepository = thresholdRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<Alert> generateAlert(EquipmentReading reading){
    	
    
    	Long EquipmentId=reading.getEquipment().getId();
    	List<Threshold> thresholds=thresholdRepository.findByEquipmentId(EquipmentId);
    	List<Alert> generatedAlerts=new ArrayList<>();
    	for(Threshold threshold: thresholds) {
    		Double ActualValue=getActualValue(reading,threshold.getParameter());
    		if(ActualValue==null) {
    			continue;
    		}
    		Alert alert=null;
    		if(ActualValue>=threshold.getCriticalLimit()) {
    			alert=createAlert(reading,threshold,ActualValue,threshold.getCriticalLimit(),AlertSeverity.CRITICAL);
    		}else if(ActualValue>=threshold.getWarningLimit()) {
    			alert=createAlert(reading,threshold,ActualValue,threshold.getWarningLimit(),AlertSeverity.WARNING);
    		}
    		if(alert!=null) {
    			generatedAlerts.add(alertRepository.save(alert));
    		}
    		
    	}
    	return generatedAlerts;
    } 
    public Double getActualValue(EquipmentReading reading, ThresholdParameter parameter) {
    	
    	switch(parameter) {
    	case TEMPERATURE:
    		return reading.getTemperature();
    	case VOLTAGE:
    		return reading.getVoltage();
    	case VIBRATION:
    		return reading.getVibration();
    	case CURRENT:
    		return reading.getCurrent();
    	case POWER_FACTOR:
    		return reading.getPowerFactor();
    	default:
    		return null;
    	}   	
    	
    }
    public Alert createAlert(EquipmentReading reading,Threshold threshold,Double ActualValue,Double thresholdValue,AlertSeverity severity) {
    	
    	Alert alert=new Alert();
    	alert.setEquipment(reading.getEquipment());
    	alert.setEquipmentReading(reading);
    	alert.setParameter(threshold.getParameter());
    	alert.setActualValue(ActualValue);
    	alert.setThresholdValue(thresholdValue);
    	alert.setSeverity(severity);
    	alert.setStatus(AlertStatus.OPEN);
    	alert.setCreatedAt(LocalDateTime.now());
    	alert.setMessage(threshold.getParameter() + " threshold exceeded. Actual value: " + ActualValue + ", threshold value: " + thresholdValue);
    	return alert;   	
    }
    //Getting all alerts
    public List<AlertResponseDto> getAllAlerts(){
    	return alertRepository.findAll().stream().map(this::convertToDto).toList();
    }
    //Getting by alert Id
    public AlertResponseDto getByAlertId(Long alertId) {
    	
    	Alert alert=alertRepository.findById(alertId).orElseThrow(()->new IllegalArgumentException("The alert ID"+
    	alertId+" is not available"));
    	return convertToDto(alert);
    	
    }
    //getting alerts of a particular equipment
    public List<AlertResponseDto> getAlertByEquipmentId(Long equipmentId){
    	equipmentRepository.findById(equipmentId) .orElseThrow(() -> new EquipmentNotFoundException( "Equipment not found with ID: " + equipmentId));
    	List<Alert> alerts = alertRepository.findByEquipmentId(equipmentId);
    	return alerts.stream().map(this::convertToDto).toList();
    }
    private AlertResponseDto convertToDto(Alert alert) { 
    	 System.out.println("DEBUG: Alert ID = " + alert.getId());

    	 System.out.println("DEBUG: Equipment = " + alert.getEquipment());

    	 System.out.println("DEBUG: Reading = " + alert.getEquipmentReading());

    	AlertResponseDto dto = new AlertResponseDto(); 
    	dto.setId(alert.getId());
    	dto.setEquipmentId(alert.getEquipment().getId()); 
    	dto.setEquipmentCode( alert.getEquipment().getEquipmentCode()); 
    	dto.setReadingId( alert.getEquipmentReading().getId());
    	dto.setParameter(alert.getParameter()); 
    	dto.setActualValue(alert.getActualValue()); 
    	dto.setThresholdValue(alert.getThresholdValue()); 
    	dto.setSeverity(alert.getSeverity()); 
    	dto.setStatus(alert.getStatus()); 
    	dto.setMessage(alert.getMessage()); 
    	dto.setCreatedAt(alert.getCreatedAt()); 
    	return dto;
    	}
    public AlertResponseDto acknowledgeAlert(Long alertId) {
    	
    	Alert alert=alertRepository.findById(alertId).orElseThrow(()->new IllegalArgumentException("Alert with ID"+alertId+" not found"));
    	if(alert.getStatus()==AlertStatus.RESOLVED) {
    		throw new IllegalArgumentException("Resolved alerts cannot be acknowledged ");
    	}
    	alert.setStatus(AlertStatus.ACKNOWLEDGED);
    	Alert updateAlert=alertRepository.save(alert);
    	return convertToDto(updateAlert);
    }
    public AlertResponseDto resolveAlert(Long alertId) {
    	Alert alert =alertRepository.findById(alertId).orElseThrow(()->new IllegalArgumentException("Alert with ID "+alertId+" not found"));
    	if(alert.getStatus()!=AlertStatus.ACKNOWLEDGED) {
    		throw new IllegalArgumentException("Only acknowledged alerts will be resolved");
    	}
    	alert.setStatus(AlertStatus.RESOLVED);
    	Alert updateAlert=alertRepository.save(alert);
    	return convertToDto(updateAlert);
    }
}
