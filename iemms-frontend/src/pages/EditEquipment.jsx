//SHIVAAAAHHHH

import { useState } from "react";
import { updateEquipment } from "../services/equipmentService";
import "./EditEquipment.css";

function EditEquipment({ equipment, onClose, onEquipmentUpdated }) {
    const [formData,setFormData]=useState({
        equipmentCode: equipment.equipmentCode || "",
        equipmentName: equipment.name || "",
        type: equipment.type || "",
        manufacturer: equipment.manufacturer || "",
        model: equipment.model || "",
        location: equipment.location || "",
        installationDate: equipment.installationDate || "",
        status: equipment.status || "ACTIVE",
        description: equipment.description || ""
    });
    const [error,setError]=useState("");
    const [loading,setLoading]=useState(false);
    const handleChange=(e)=>{
        setFormData({...formData,[e.target.name]:e.target.value});
    }
    const handleSubmit=async (e)=>{
        e.preventDefault();
        setError("");
        try{
            setLoading(true);
            const response=await updateEquipment(equipment.Id,formData);
            onEquipmentUpdated();
            onClose();
        }
        catch(err){
            console.log("Error while editing equipment",err);
            setError(err.response?.data?.message||"Error occured... Please try again");
        }
        finally{
            setLoading(false);
        }
    }
    return (
    <div className="modal-overlay">
        <div className="equipment-modal">

            <h2>Update Equipment</h2>

            {error && (
                <p className="error-message">{error}</p>
            )}

            <form onSubmit={handleSubmit}>

                <div className="form-group">
                    <label>Equipment Code</label>
                    <input
                        type="text"
                        name="equipmentCode"
                        value={formData.equipmentCode}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Equipment Name</label>
                    <input
                        type="text"
                        name="equipmentName"
                        value={formData.equipmentName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Type</label>
                    <input
                        type="text"
                        name="type"
                        value={formData.type}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Manufacturer</label>
                    <input
                        type="text"
                        name="manufacturer"
                        value={formData.manufacturer}
                        onChange={handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Model</label>
                    <input
                        type="text"
                        name="model"
                        value={formData.model}
                        onChange={handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Location</label>
                    <input
                        type="text"
                        name="location"
                        value={formData.location}
                        onChange={handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Installation Date</label>
                    <input
                        type="date"
                        name="installationDate"
                        value={formData.installationDate}
                        onChange={handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Status</label>
                    <select
                        name="status"
                        value={formData.status}
                        onChange={handleChange}
                        required
                    >
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="INACTIVE">INACTIVE</option>
                        <option value="MAINTENANCE">MAINTENANCE</option>
                    </select>
                </div>

                <div className="form-group">
                    <label>Description</label>
                    <textarea
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        rows="3"
                    />
                </div>

                <div className="form-actions">
                    <button
                        type="button"
                        onClick={onClose}
                        disabled={loading}
                    >
                        Cancel
                    </button>

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading ? "Updating..." : "Update Equipment"}
                    </button>
                </div>

            </form>
        </div>
    </div>
);
}
export default EditEquipment;