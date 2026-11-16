//SHIVAAAAAHHHH

import api from "./api"

export const getAllAlerts=async ()=>{
    const response=await api.get("/alerts");
    return response.data;
}

export const getAlertById=async (alertId)=>{
    const response=await api.get(`/alerts/${alertId}`);
    return response.data;
}

export const getAlertByEquipmentId=async (equipmentId)=>{
    const response=await api.get(`/alerts/equipment/${equipmentId}`);
    return response.data;
}
export const acknowledgeAlert=async (alertId)=>{
    const response=await api.put(`/alerts/${alertId}/acknowledge`);
    return response.data;
}
export const resolveAlert=async (alertId)=>{
    const response=await api.put(`/alerts/${alertId}/resolve`);
    return response.data;
}