//SHIVAAAAHHHHHH

import api from "./api";

export const getAllMaintenanceTickets=async ()=>{
    const response =await api.get("/maintenance-tickets");
    return response.data;
}
export const getMaintenanceTIcketById=async (ticketId)=>{
    const response=await api.get(`/maintenance-tickets/${ticketId}`);
    return response.data;
}
export const getMaintenanceTicketByEquipmentId=async (equipmentId)=>{
    const response=await api.get(`/maintenance-tickets/equipment/${equipmentId}`);
    return response.data;
}
export const createMaintenanceTicket=async (ticketData)=>{
    const response=await api.post("/maintenance-tickets");
    return response.data;
}
export const assignTechnician=async (ticketId,technicianName)=>{
    const response=await api.put(`/maintenance-tickets/${ticketId}/assign`,{technicianName});
    return response.data;
}
export const startMaintenance=async (ticketId)=>{
    const response=await api.put(`/maintenance-tickets/${ticketId}/start`);
    return response.data;
}
export const completeMaintenance=async (ticketId)=>{
    const response=await api.put(`/maintenance-tickets/${ticketId}/complete`);
    return response.data;
}
export const cancelMaintenance=async (ticketId)=>{
    const response=await api.put(`/maintenance-tickets/${ticketId}/cancel`);
    return response.data;
}