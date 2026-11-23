//SHIVAAAAHHHH

import {
  getAllAlerts,
  acknowledgeAlert,
  resolveAlert,
} from "../services/alertService";
import { useState, useEffect } from "react";
import "./Alerts.css";


function Alerts() {
  const [alerts, setAlerts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Filter States
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedSeverity, setSelectedSeverity] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("");
  const [actionLoadingId, setActionLoadingId] = useState(null);
  const [successMessage, setSuccessMessage] = useState("");
  const [actionError, setActionError] = useState("");

  // Fetch All Alerts
  const fetchAllAlerts = async (showLoading = true) => {
    try {
      if (showLoading) {
        setLoading(true);
      }
      setError("");

      const response = await getAllAlerts();

      console.log("Alert fetching response:", response);
      console.log("Alert response:", response[0]);

      setAlerts(Array.isArray(response) ? response : response.content || []);
    } catch (err) {
      console.log("Error while fetching alerts:", err);
      setError("Unable to fetch alerts. Please try again.");
    } finally {
      if (showLoading) {
        setLoading(false);
      }
    }
  };
  const handleAlertAction = async (alertId, action) => {
    try {
      setActionLoadingId(alertId);
      setSuccessMessage("");
      setActionError("");
      if (action === "ACKNOWLEDGED") {
        await acknowledgeAlert(alertId);
        setSuccessMessage("Ackwnoledge the alert successfully");
      } else if (action === "RESOLVED") {
        await resolveAlert(alertId);
        setSuccessMessage("Resolved alert successfully");
      }
      await fetchAllAlerts(false);
    } catch (err) {
      console.log("Alert while changing alert status", err);
      setActionError(err.response?.data?.message);
    } finally {
      setActionLoadingId(null);
    }
  };

  useEffect(() => {
    fetchAllAlerts();
  }, []);

  // Alert Statistics
  const totalAlerts = alerts.length;

  const warningAlerts = alerts.filter(
    (alert) => alert.severity === "WARNING",
  ).length;

  const criticalAlerts = alerts.filter(
    (alert) => alert.severity === "CRITICAL",
  ).length;

  const openAlerts = alerts.filter((alert) => alert.status === "OPEN").length;

  // Search and Filtering Logic
  const filteredAlerts = alerts.filter((alert) => {
    const matchesSearch =
      alert.equipmentCode?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      alert.parameter?.toLowerCase().includes(searchTerm.toLowerCase());

    const matchesSeverity =
      selectedSeverity === "" || alert.severity === selectedSeverity;

    const matchesStatus =
      selectedStatus === "" || alert.status === selectedStatus;

    return matchesSearch && matchesSeverity && matchesStatus;
  });

  // Loading State
  if (loading) {
    return <h2>Loading Alerts, please wait...</h2>;
  }

  // Error State
  if (error) {
    return <h2>{error}</h2>;
  }

  return (
    <div className="alerts-container">
      <h1>Alerts Management</h1>
      {successMessage && (
        <p className="alert-success-message">{successMessage}</p>
      )}
      {actionError && <p className="alert-error-message">{actionError}</p>}

      {/* Alert Summary */}

      <div className="alert-summary">
        <div className="alert-card">
          <h3>Total Alerts</h3>
          <h2>{totalAlerts}</h2>
        </div>

        <div className="alert-card">
          <h3>Critical Alerts</h3>
          <h2>{criticalAlerts}</h2>
        </div>

        <div className="alert-card">
          <h3>Warning Alerts</h3>
          <h2>{warningAlerts}</h2>
        </div>

        <div className="alert-card">
          <h3>Open Alerts</h3>
          <h2>{openAlerts}</h2>
        </div>
      </div>

      {/* Alerts Table */}

      <div className="alerts-table-container">
        <div className="alerts-header">
          <h2>All Equipment Alerts</h2>

          <div className="alerts-filters">
            {/* Search */}

            <input
              type="text"
              placeholder="Search equipment code or parameter..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />

            {/* Severity Filter */}

            <select
              value={selectedSeverity}
              onChange={(e) => setSelectedSeverity(e.target.value)}
            >
              <option value="">All Severities</option>
              <option value="CRITICAL">Critical</option>
              <option value="WARNING">Warning</option>
            </select>

            {/* Status Filter */}

            <select
              value={selectedStatus}
              onChange={(e) => setSelectedStatus(e.target.value)}
            >
              <option value="">All Statuses</option>
              <option value="OPEN">Open</option>
              <option value="ACKNOWLEDGED">Acknowledged</option>
              <option value="RESOLVED">Resolved</option>
            </select>

            {/* Clear Filters */}

            <button
              onClick={() => {
                setSearchTerm("");
                setSelectedSeverity("");
                setSelectedStatus("");
              }}
            >
              Clear Filters
            </button>
          </div>
        </div>

        <table className="alerts-table">
          <thead>
            <tr>
              <th>Alert ID</th>
              <th>Equipment ID</th>
              <th>Equipment Code</th>
              <th>Reading ID</th>
              <th>Parameter</th>
              <th>Actual Value</th>
              <th>Threshold</th>
              <th>Severity</th>
              <th>Status</th>
              <th>Created At</th>
              <th>Message</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {filteredAlerts.length === 0 ? (
              <tr>
                <td colSpan="11">No matching alerts found.</td>
              </tr>
            ) : (
              filteredAlerts.map((alert) => (
                <tr key={alert.Id}>
                  <td>{alert.Id}</td>
                  <td>{alert.equipmentId}</td>
                  <td>{alert.equipmentCode}</td>
                  <td>{alert.readingId}</td>
                  <td>{alert.parameter}</td>
                  <td>{alert.actualValue}</td>
                  <td>{alert.thresholdValue}</td>
                  <td>
                    <span
                      className={`severity-badge ${alert.severity?.toLowerCase()}`}
                    >
                      {alert.severity}
                    </span>
                  </td>

                  <td>
                    <span
                      className={`alert-status ${alert.status?.toLowerCase()}`}
                    >
                      {alert.status}
                    </span>
                  </td>

                  <td>
                    {alert.createdAt
                      ? new Date(alert.createdAt).toLocaleString()
                      : "N/A"}
                  </td>

                  <td>{alert.message}</td>
                  <td className="alert-actions">
                    {alert.status === "OPEN" && (
                      <button
                        className="acknowledge-btn"
                        disabled={actionLoadingId === alert.Id}
                        onClick={() => {
                          handleAlertAction(alert.Id, "ACKNOWLEDGED");
                        }}
                      >
                        {actionLoadingId === alert.Id
                          ? "Processing"
                          : "ACKNOWLEDGE"}
                      </button>
                    )}
                    {alert.status === "OPEN" ||
                      (alert.status === "ACKNOWLEDGED" && (
                        <button
                          className="resolve-btn"
                          disabled={actionLoadingId === alert.Id}
                          onClick={() => {
                            handleAlertAction(alert.Id, "RESOLVED");
                          }}
                        >
                          {actionLoadingId === alert.Id
                            ? "Processing"
                            : "RESOLVE"}
                        </button>
                      ))}
                    {alert.status === "RESOLVED" && (
                      <span className="completed-badge">Completed</span>
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Alerts;
