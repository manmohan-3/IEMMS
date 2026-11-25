//SHIVAAAAAHHH
import { useState } from "react";
import { updateThreshold } from "../services/thresholdService";
import "./AddThresholds.css";

function EditThreshold({ threshold, onClose, onThresholdUpdated }) {
  const [formData, setFormData] = useState({
    parameter: threshold.parameter,
    warningLimit: threshold.warningLimit,
    criticalLimit: threshold.criticalLimit,
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    const warning = Number(formData.warningLimit);
    const critical = Number(formData.criticalLimit);

    if (formData.warningLimit === "" || formData.criticalLimit === "") {
      setError("Please fill in all required fields.");
      return;
    }

    if (warning <= 0 || critical <= 0) {
      setError("Threshold limits must be greater than zero.");
      return;
    }

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

      await updateThreshold(
        threshold.equipment.Id,
        threshold.Id,
        thresholdData,
      );

      await onThresholdUpdated();
      onClose();
    } catch (err) {
      console.error("Error updating threshold:", err);

      setError(
        err.response?.data?.message ||
          "Failed to update threshold. Please try again.",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add-threshold-overlay">
      <div className="add-threshold-modal">
        <div className="add-threshold-header">
          <div>
            <h2>Edit Threshold</h2>
            <p>Update equipment parameter limits.</p>
          </div>

          <button className="threshold-close-btn" onClick={onClose}>
            ×
          </button>
        </div>

        <form className="add-threshold-form" onSubmit={handleSubmit}>
          {error && <div className="add-threshold-error">{error}</div>}

          <div className="threshold-form-group">
            <label>Equipment</label>

            <input
              type="text"
              value={`${threshold.equipment.equipmentCode} - ${threshold.equipment.name}`}
              disabled
            />
          </div>

          <div className="threshold-form-group">
            <label>Parameter</label>

            <input
              type="text"
              value={formData.parameter.replace("_", " ")}
              disabled
            />
          </div>

          <div className="threshold-form-group">
            <label>Warning Limit</label>

            <input
              type="number"
              name="warningLimit"
              value={formData.warningLimit}
              onChange={handleChange}
              step="any"
              min="0"
              required
            />
          </div>

          <div className="threshold-form-group">
            <label>Critical Limit</label>

            <input
              type="number"
              name="criticalLimit"
              value={formData.criticalLimit}
              onChange={handleChange}
              step="any"
              min="0"
              required
            />
          </div>

          <div className="threshold-info">
            {formData.parameter === "POWER_FACTOR"
              ? "Power Factor: Critical limit must be smaller than warning limit."
              : "Warning limit must be smaller than critical limit."}
          </div>

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
              disabled={loading}
            >
              {loading ? "Updating..." : "Update Threshold"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EditThreshold;
