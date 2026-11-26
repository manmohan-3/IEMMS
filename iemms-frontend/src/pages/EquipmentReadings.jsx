import { useEffect, useState } from "react";

import { getAllEquipment } from "../services/equipmentService";
import { getEquipmentReadings } from "../services/equipmentReadingService";

import AddReading from "./AddReading";

import "./EquipmentReadings.css";

function EquipmentReadings() {
  const [equipmentList, setEquipmentList] = useState([]);
  const [selectedEquipment, setSelectedEquipment] = useState("");

  const [readings, setReadings] = useState([]);

  const [loading, setLoading] = useState(false);
  const [fetchingEquipment, setFetchingEquipment] = useState(true);

  const [error, setError] = useState("");

  // Controls Add Reading modal
  const [showAddReading, setShowAddReading] = useState(false);

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
      setError("Failed to load equipment.");
    } finally {
      setFetchingEquipment(false);
    }
  };

  // Fetch readings for selected equipment
  const fetchReadings = async (equipmentId) => {
    try {
      setLoading(true);
      setError("");

      const data = await getEquipmentReadings(equipmentId);

      setReadings(data);
    } catch (err) {
      console.error("Error fetching readings:", err);

      setError(
        err.response?.data?.message || "Failed to load equipment readings.",
      );
    } finally {
      setLoading(false);
    }
  };

  // Handle equipment selection
  const handleEquipmentChange = (e) => {
    const equipmentId = e.target.value;

    setSelectedEquipment(equipmentId);

    setReadings([]);
    setError("");

    if (equipmentId) {
      fetchReadings(equipmentId);
    }
  };

  // Refresh readings after successful creation
  const handleReadingSuccess = () => {
    fetchReadings(selectedEquipment);
  };

  useEffect(() => {
    fetchEquipment();
  }, []);

  return (
    <div className="equipment-readings-page">
      {/* Header */}

      <div className="readings-header">
        <div>
          <h1>Equipment Readings</h1>

          <p>Monitor and review historical equipment readings.</p>
        </div>

        {selectedEquipment && (
          <button
            className="add-reading-btn"
            onClick={() => setShowAddReading(true)}
          >
            + Add Reading
          </button>
        )}
      </div>

      {/* Equipment Selection */}

      <div className="readings-filter-container">
        <div className="reading-filter-group">
          <label>Select Equipment</label>

          <select
            value={selectedEquipment}
            onChange={handleEquipmentChange}
            disabled={fetchingEquipment}
          >
            <option value="">
              {fetchingEquipment ? "Loading equipment..." : "Choose Equipment"}
            </option>

            {equipmentList.map((equipment) => (
              <option key={equipment.Id} value={equipment.Id}>
                {equipment.equipmentCode} - {equipment.name}
              </option>
            ))}
          </select>
        </div>
      </div>

      {/* Error */}

      {error && <div className="readings-error">{error}</div>}

      {/* Readings Table */}

      <div className="readings-table-container">
        <div className="readings-table-header">
          <h2>Reading History</h2>

          <span>{readings.length} Records</span>
        </div>

        {loading ? (
          <div className="readings-loading">Loading readings...</div>
        ) : !selectedEquipment ? (
          <div className="readings-empty">
            Please select equipment to view its readings.
          </div>
        ) : readings.length === 0 ? (
          <div className="readings-empty">
            No readings found for this equipment.
          </div>
        ) : (
          <div className="readings-table-wrapper">
            <table className="readings-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Temperature (°C)</th>
                  <th>Voltage (V)</th>
                  <th>Current (A)</th>
                  <th>Vibration</th>
                  <th>Power Factor</th>
                  <th>Reading Time</th>
                </tr>
              </thead>

              <tbody>
                {readings.map((reading) => (
                  <tr key={reading.Id}>
                    <td>#{reading.Id}</td>
                    <td>{reading.temperature}</td>
                    <td>{reading.voltage}</td>
                    <td>{reading.current}</td>
                    <td>{reading.vibration}</td>
                    <td>{reading.powerFactor}</td>

                    <td>
                      {reading.readingTime
                        ? new Date(reading.readingTime).toLocaleString()
                        : "N/A"}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Add Reading Modal */}

      {showAddReading && (
        <AddReading
          equipmentId={selectedEquipment}
          onClose={() => setShowAddReading(false)}
          onSuccess={handleReadingSuccess}
        />
      )}
    </div>
  );
}

export default EquipmentReadings;
