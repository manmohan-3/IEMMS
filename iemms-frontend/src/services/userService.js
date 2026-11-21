//SHIVAAAAHHHHH

import api from "./api";

//Get all users
export const getAllUsers=async ()=>{
    const response=await api.get("/users");
    return response.data;
}

//Get user by id
export const getUserById=async (userId)=>{
    const response=await api.get(`/users/${userId}`);
    return response.data;
}

//create user
export const createUser=async (userData)=>{
    const response=await api.post("/users",userData);
    return response.data;
}

//disable user
export const disableUser=async (userId)=>{
    const response =await api.put(`/users/${userId}/disable`);
    return response.data;
}

//enable user
export const enableUser=async (userId)=>{
    const response=await api.put(`/users/${userId}/enable`);
    return response.data;
}