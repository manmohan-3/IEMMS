//SHIVAAAAHHHH
import api from "./api";

// Get all equipment with pagination
export const getAllEquipment = async (page = 0, size = 5, status="", search="") => {
    const response = await api.get("/equipment", {
        params: {
            page,
            size,
            ...(status &&{status}),
            ...(search &&{search})
        }
    });

    return response.data;
};
export const createEquipment=async(equipmentData)=>{
    const response=await api.post("/equipment",equipmentData);
    // console.log("Service called");
    return response.data;
}
export const updateEquipment=async(id,equipmentData)=>{
    const response=await api.put(`/equipment/${id}`,equipmentData);
    return response.data;
}
export const deleteEquipment=async(id)=>{
    const response=await api.delete(`/equipment/${id}`);
    return response.data;
}