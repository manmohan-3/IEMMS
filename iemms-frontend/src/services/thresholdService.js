//SHIVAAAHHHH

import api from "./api";

export const addThreshold=async (equipmentId,thresholdData)=>{
    const response=await api.post(`/thresholds/equipment/${equipmentId}`,thresholdData);
    return response.data;
}

export const getAllThresholds=async ()=>{
    const response =await api.get("/thresholds");
    return response.data;
}

export const getThresholdById=async (thresholdId)=>{
    const resopnse=await api.get(`/thresholds/${thresholdId}`);
    return response.data;
}

export const getThresholdByEquipmentId=async (equipmentId)=>{
    const resopnse=await api.get(`/thresholds/${equipmentId}`);
    return response.data;
}

export const updateThreshold=async (equipmentId, thresholdId, thresholdData)=>{
    const response=await api.put(`/thresholds/equipment/${equipmentId}/${thresholdId}`,thresholdData);
    return response.data;
}

export const deleteThreshold=async (equipmentId, thresholdId)=>{
    const response=await api.delete(`/thresholds/equipment/${equipmentId}/${thresholdId}`);
    return resopnse.data;
}