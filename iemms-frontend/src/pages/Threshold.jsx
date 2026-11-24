//SHIVAAAAHHHH

import { useEffect, useState } from "react";
import { getAllThresholds } from "../services/thresholdService";
import "./Threshold.css";

function Threshold() {
  const [thresholds, setThresholds] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Filter states
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedEquipment, setSelectedEquipment] = useState("ALL");
  const [selectedParameter, setSelectedParameter] = useState("ALL");

  // Fetch all thresholds
  const fetchThresholds = async () => {
    try {
      setLoading(true);
      setError("");

      const data = await getAllThresholds();
      setThresholds(data);
    } catch (error) {
      console.error("Error fetching thresholds:", error);
      setError("Failed to load thresholds.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchThresholds();
  }, []);

  // Get unique equipment
  const equipmentList = [
    ...new Map(
      thresholds.map((threshold) => [
        threshold.equipment.Id,
        threshold.equipment,
      ]),
    ).values(),
  ];

  // Apply search and filters
  const filteredThresholds = thresholds.filter((threshold) => {
    const search = searchTerm.toLowerCase();

    const matchesSearch =
      threshold.equipment.equipmentCode.toLowerCase().includes(search) ||
      threshold.equipment.name.toLowerCase().includes(search);

    const matchesEquipment =
      selectedEquipment === "ALL" ||
      String(threshold.equipment.Id) === selectedEquipment;

    const matchesParameter =
      selectedParameter === "ALL" || threshold.parameter === selectedParameter;

    return matchesSearch && matchesEquipment && matchesParameter;
  });

  // Summary calculations
  const totalThresholds = thresholds.length;

  const temperatureCount = thresholds.filter(
    (t) => t.parameter === "TEMPERATURE",
  ).length;

  const electricalCount = thresholds.filter((t) =>
    ["VOLTAGE", "CURRENT", "POWER_FACTOR"].includes(t.parameter),
  ).length;

  const mechanicalCount = thresholds.filter(
    (t) => t.parameter === "VIBRATION",
  ).length;

  return (
    <div className="threshold-page">
      {/* Header */}

      <div className="threshold-header">
        <div>
          <h1>Threshold Management</h1>
          <p>Configure equipment parameter limits.</p>
        </div>
      </div>

      {/* Summary Cards */}

      <div className="threshold-summary">
        <div className="threshold-card">
          <h3>Total Thresholds</h3>
          <p>{totalThresholds}</p>
        </div>

        <div className="threshold-card">
          <h3>Temperature</h3>
          <p>{temperatureCount}</p>
        </div>

        <div className="threshold-card">
          <h3>Electrical</h3>
          <p>{electricalCount}</p>
        </div>

        <div className="threshold-card">
          <h3>Mechanical</h3>
          <p>{mechanicalCount}</p>
        </div>
      </div>

      {/* Search and Filters */}

      <div className="threshold-filters">
        <input
          type="text"
          placeholder="Search equipment code or name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />

        <select
          value={selectedEquipment}
          onChange={(e) => setSelectedEquipment(e.target.value)}
        >
          <option value="ALL">All Equipment</option>

          {equipmentList.map((equipment) => (
            <option key={equipment.Id} value={equipment.Id}>
              {equipment.equipmentCode} - {equipment.name}
            </option>
          ))}
        </select>

        <select
          value={selectedParameter}
          onChange={(e) => setSelectedParameter(e.target.value)}
        >
          <option value="ALL">All Parameters</option>
          <option value="TEMPERATURE">Temperature</option>
          <option value="VOLTAGE">Voltage</option>
          <option value="CURRENT">Current</option>
          <option value="VIBRATION">Vibration</option>
          <option value="POWER_FACTOR">Power Factor</option>
        </select>

        <button
          className="threshold-reset-btn"
          onClick={() => {
            setSearchTerm("");
            setSelectedEquipment("ALL");
            setSelectedParameter("ALL");
          }}
        >
          Reset
        </button>
      </div>

      {/* Threshold Table */}

      {loading ? (
        <p className="threshold-loading">Loading thresholds...</p>
      ) : error ? (
        <p className="threshold-error">{error}</p>
      ) : (
        <div className="threshold-table-container">
          <table className="threshold-table">
            <thead>
              <tr>
                <th>Equipment Code</th>
                <th>Equipment Name</th>
                <th>Parameter</th>
                <th>Warning Limit</th>
                <th>Critical Limit</th>
                <th>Actions</th>
              </tr>
            </thead>

            <tbody>
              {filteredThresholds.length === 0 ? (
                <tr>
                  <td colSpan="6">No matching thresholds found.</td>
                </tr>
              ) : (
                filteredThresholds.map((threshold) => (
                  <tr key={threshold.Id}>
                    <td>{threshold.equipment.equipmentCode}</td>

                    <td>{threshold.equipment.name}</td>

                    <td>{threshold.parameter}</td>

                    <td>{threshold.warningLimit}</td>

                    <td>{threshold.criticalLimit}</td>

                    <td>
                      <button className="threshold-edit-btn">Edit</button>

                      <button className="threshold-delete-btn">Delete</button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default Threshold;
