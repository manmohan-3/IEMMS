//SHIVAAAAHHHH

import { useState } from "react";
import { createEquipment } from "../services/equipmentService";
import "./AddEquipment.css";

function AddEquipment({onClose, onEquipmentAdded}){
    const [formData,setFormData]=useState({
        equipmentCode:"",
        equipmentName:"",
        type: "",
        manufacturer: "",
        model: "",
        location: "",
        installationDate: "",
        status: "ACTIVE",
        description: ""
    });
    const [loading, setLoading]=useState(false);
    const [error,setError]=useState("");
    const handleChange=(e)=>{
        setFormData({...formData,[e.target.name]:e.target.value});
    }
    const handleSubmit=async (e)=>{
        e.preventDefault();
        setError("");
        try{
            setLoading(true);
            await createEquipment(formData);
            onEquipmentAdded();
            onClose();
        }
        catch(err){
            console.log("Equipment creation failed:",err);
            setError(err.response?.data?.message||"Unable to register the equipment. Please try again");
        }
        finally{
            setLoading(false);
        }
        
    }
    return (
        <div className="add-equipment-overlay">

            <div className="add-equipment-modal">

                <div className="add-equipment-header">
                    <div>
                        <h2>Register Equipment</h2>
                        <p>Add new industrial equipment to your inventory.</p>
                    </div>

                    <button
                        className="add-equipment-close"
                        onClick={onClose}
                        type="button"
                    >
                        ×
                    </button>
                </div>

                {error && (
                    <div className="add-equipment-error">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="add-equipment-grid">

                        <div className="form-group">
                            <label>Equipment Code *</label>
                            <input
                                type="text"
                                name="equipmentCode"
                                value={formData.equipmentCode}
                                onChange={handleChange}
                                placeholder="Example: EQ-025"
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label>Equipment Name *</label>
                            <input
                                type="text"
                                name="equipmentName"
                                value={formData.equipmentName}
                                onChange={handleChange}
                                placeholder="Enter equipment name"
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label>Equipment Type *</label>
                            <input
                                type="text"
                                name="type"
                                value={formData.type}
                                onChange={handleChange}
                                placeholder="Example: Motor, Pump"
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
                                placeholder="Enter manufacturer"
                            />
                        </div>

                        <div className="form-group">
                            <label>Model</label>
                            <input
                                type="text"
                                name="model"
                                value={formData.model}
                                onChange={handleChange}
                                placeholder="Enter model number"
                            />
                        </div>

                        <div className="form-group">
                            <label>Location</label>
                            <input
                                type="text"
                                name="location"
                                value={formData.location}
                                onChange={handleChange}
                                placeholder="Example: Plant 1"
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
                            <label>Status *</label>
                            <select
                                name="status"
                                value={formData.status}
                                onChange={handleChange}
                                required
                            >
                                <option value="ACTIVE">Active</option>
                                <option value="INACTIVE">Inactive</option>
                                <option value="MAINTENANCE">Maintenance</option>
                            </select>
                        </div>

                    </div>

                    <div className="form-group description-group">
                        <label>Description</label>
                        <textarea
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            placeholder="Enter equipment description..."
                            rows="3"
                        />
                    </div>

                    <div className="add-equipment-actions">

                        <button
                            type="button"
                            className="cancel-equipment-btn"
                            onClick={onClose}
                        >
                            Cancel
                        </button>

                        <button
                            type="submit"
                            className="save-equipment-btn"
                            disabled={loading}
                        >
                            {loading ? "Registering..." : "+ Register Equipment"}
                        </button>

                    </div>

                </form>

            </div>

        </div>
    );
}

export default AddEquipment;
