//SHIVAAAHHH

import { useEffect, useState } from "react";
import { getAllEquipment } from "../services/equipmentService";
import { addThreshold } from "../services/thresholdService";
import "./AddThresholds.css";

function AddThreshold({ onClose, onThresholdAdded }) {
  const [equipmentList, setEquipmentList] = useState([]);

  const [formData, setFormData] = useState({
    equipmentId: "",
    parameter: "",
    warningLimit: "",
    criticalLimit: "",
  });

  const [loading, setLoading] = useState(false);
  const [fetchingEquipment, setFetchingEquipment] = useState(true);
  const [error, setError] = useState("");

  // Fetch equipment list
  const fetchEquipment = async () => {
    try {
      setFetchingEquipment(true);

      const response = await getAllEquipment(0, 100);

      const equipmentData = Array.isArray(response)
        ? response
        : response.content || [];

      setEquipmentList(equipmentData);
    } catch (err) {
      console.error("Error fetching equipment:", err);
      setError("Failed to load equipment list.");
    } finally {
      setFetchingEquipment(false);
    }
  };

  useEffect(() => {
    fetchEquipment();
  }, []);

  // Handle form changes
  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    setError("");

    const warning = Number(formData.warningLimit);
    const critical = Number(formData.criticalLimit);

    if (
      !formData.equipmentId ||
      !formData.parameter ||
      formData.warningLimit === "" ||
      formData.criticalLimit === ""
    ) {
      setError("Please fill in all required fields.");
      return;
    }

    if (warning <= 0 || critical <= 0) {
      setError("Threshold limits must be greater than zero.");
      return;
    }

    // Power Factor has inverse threshold logic
    if (formData.parameter === "POWER_FACTOR") {
      if (critical >= warning) {
        setError(
          "For Power Factor, critical limit must be smaller than warning limit.",
        );
        return;
      }

      if (warning > 1) {
        setError("Power Factor warning limit cannot exceed 1.");
        return;
      }
    } else {
      if (warning >= critical) {
        setError("Warning limit must be smaller than critical limit.");
        return;
      }
    }

    const thresholdData = {
      parameter: formData.parameter,
      warningLimit: warning,
      criticalLimit: critical,
    };

    try {
      setLoading(true);

      await addThreshold(formData.equipmentId, thresholdData);

      onThresholdAdded();
      onClose();
    } catch (err) {
      console.error("Error creating threshold:", err);

      setError(
        err.response?.data?.message ||
          "Failed to create threshold. Please try again.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add-threshold-overlay">
      <div className="add-threshold-modal">
        {/* Header */}

        <div className="add-threshold-header">
          <div>
            <h2>Add Threshold</h2>
            <p>Configure equipment parameter limits</p>
          </div>

          <button
            className="threshold-close-btn"
            onClick={onClose}
            type="button"
          >
            ×
          </button>
        </div>

        {/* Form */}

        <form className="add-threshold-form" onSubmit={handleSubmit}>
          {error && <div className="add-threshold-error">{error}</div>}

          {/* Equipment */}

          <div className="threshold-form-group">
            <label>Equipment *</label>

            <select
              name="equipmentId"
              value={formData.equipmentId}
              onChange={handleChange}
              disabled={fetchingEquipment}
              required
            >
              <option value="">
                {fetchingEquipment
                  ? "Loading equipment..."
                  : "Select Equipment"}
              </option>

              {equipmentList.map((equipment) => (
                <option key={equipment.Id} value={equipment.Id}>
                  {equipment.equipmentCode} - {equipment.name}
                </option>
              ))}
            </select>
          </div>

          {/* Parameter */}

          <div className="threshold-form-group">
            <label>Parameter *</label>

            <select
              name="parameter"
              value={formData.parameter}
              onChange={handleChange}
              required
            >
              <option value="">Select Parameter</option>
              <option value="TEMPERATURE">Temperature</option>
              <option value="VOLTAGE">Voltage</option>
              <option value="CURRENT">Current</option>
              <option value="VIBRATION">Vibration</option>
              <option value="POWER_FACTOR">Power Factor</option>
            </select>
          </div>

          {/* Warning Limit */}

          <div className="threshold-form-group">
            <label>Warning Limit *</label>

            <input
              type="number"
              name="warningLimit"
              placeholder="Enter warning limit"
              value={formData.warningLimit}
              onChange={handleChange}
              step="any"
              min="0"
              required
            />
          </div>

          {/* Critical Limit */}

          <div className="threshold-form-group">
            <label>Critical Limit *</label>

            <input
              type="number"
              name="criticalLimit"
              placeholder="Enter critical limit"
              value={formData.criticalLimit}
              onChange={handleChange}
              step="any"
              min="0"
              required
            />
          </div>

          {/* Power Factor Information */}

          {formData.parameter === "POWER_FACTOR" && (
            <div className="threshold-info">
              Power Factor: Critical limit must be lower than the warning limit.
              Example: Warning 0.85, Critical 0.70.
            </div>
          )}

          {/* Other Parameter Information */}

          {formData.parameter && formData.parameter !== "POWER_FACTOR" && (
            <div className="threshold-info">
              Warning limit must be lower than the critical limit for this
              parameter.
            </div>
          )}

          {/* Actions */}

          <div className="add-threshold-actions">
            <button
              type="button"
              className="cancel-threshold-btn"
              onClick={onClose}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="create-threshold-btn"
              disabled={loading || fetchingEquipment}
            >
              {loading ? "Creating..." : "Create Threshold"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddThreshold;
