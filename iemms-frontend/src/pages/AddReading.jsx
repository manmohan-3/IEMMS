//SHIVAAAHHH

import { useState } from "react";
import { createEquipmentReading } from "../services/equipmentReadingService";
import "./AddReading.css";

function AddReading({ equipmentId, onClose, onSuccess }) {
  const [formData, setFormData] = useState({
    temperature: "",
    voltage: "",
    current: "",
    vibration: "",
    powerFactor: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);
      setError("");

      const readingData = {
        temperature: Number(formData.temperature),
        voltage: Number(formData.voltage),
        current: Number(formData.current),
        vibration: Number(formData.vibration),
        powerFactor: Number(formData.powerFactor),
      };

      await createEquipmentReading(equipmentId, readingData);

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Error adding reading:", err);

      setError(
        err.response?.data?.message || "Failed to save equipment reading.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add-reading-overlay">
      <div className="add-reading-modal">
        <div className="add-reading-header">
          <h2>Add Equipment Reading</h2>

          <button type="button" className="reading-close-btn" onClick={onClose}>
            ×
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="reading-form-group">
            <label>Temperature (°C)</label>

            <input
              type="number"
              name="temperature"
              value={formData.temperature}
              onChange={handleChange}
              min="-273.15"
              step="any"
              required
            />
          </div>

          <div className="reading-form-group">
            <label>Voltage (V)</label>

            <input
              type="number"
              name="voltage"
              value={formData.voltage}
              onChange={handleChange}
              min="0"
              step="any"
              required
            />
          </div>

          <div className="reading-form-group">
            <label>Current (A)</label>

            <input
              type="number"
              name="current"
              value={formData.current}
              onChange={handleChange}
              min="0"
              step="any"
              required
            />
          </div>

          <div className="reading-form-group">
            <label>Vibration</label>

            <input
              type="number"
              name="vibration"
              value={formData.vibration}
              onChange={handleChange}
              min="0"
              step="any"
              required
            />
          </div>

          <div className="reading-form-group">
            <label>Power Factor</label>

            <input
              type="number"
              name="powerFactor"
              value={formData.powerFactor}
              onChange={handleChange}
              min="0"
              max="1"
              step="any"
              required
            />
          </div>

          {error && <p className="reading-form-error">{error}</p>}

          <div className="reading-form-actions">
            <button
              type="button"
              className="reading-cancel-btn"
              onClick={onClose}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="reading-save-btn"
              disabled={loading}
            >
              {loading ? "Saving..." : "Save Reading"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddReading;
