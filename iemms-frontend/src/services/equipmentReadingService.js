//SHIVAAAHHHH

import api from "./api";

export const getEquipmentReadings=async (equipmentId)=>{
    const response=await api.get(`/equipment/${equipmentId}/readings`);
    return response.data;
}

export const createEquipmentReading=async (equipmentId, readingData)=>{
    const response=await api.post(`equipment/${equipmentId}/readings`,readingData);
    return response.data;
}