//SHIVAAAAHHHH
import api from "./api";

// Get all equipment with pagination
export const getAllEquipment = async (page = 0, size = 5) => {
    const response = await api.get("/equipment", {
        params: {
            page,
            size
        }
    });

    return response.data;
};