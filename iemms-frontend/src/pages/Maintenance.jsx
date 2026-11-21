//SHIVAAAHHH

import { useState, useEffect } from "react";
import {
  getAllMaintenanceTickets,
  assignTechnician,
  startMaintenance,
  completeMaintenance,
  cancelMaintenance,
} from "../services/maintenanceService";
import "./Maintenance.css";

function Maintenance() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [technicianName, setTechnicianName] = useState("");
  const [actionLoading, setActionLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [statusFilter, setStatusFilter] = useState("ALL");
  const [priorityFilter, setPriorityFilter] = useState("ALL");
  const [sortBy, setSortBy] = useState("NEWEST");

  //Search and Filter Maintenance Tickets
  const filteredTickets = tickets.filter((ticket) => {
    const search = searchTerm.toLowerCase();
    const matchesSearch =
      ticket.ticketNumber?.toLowerCase().includes(search) ||
      ticket.equipmentCode?.toLowerCase().includes(search) ||
      ticket.title?.toLowerCase().includes(search) ||
      ticket.assignedTechnician?.toLowerCase().includes(search);

    const matchesStatus =
      statusFilter === "ALL" || ticket.status === statusFilter;

    const matchesPriority =
      priorityFilter === "ALL" || ticket.priority === priorityFilter;
    return matchesSearch && matchesStatus && matchesPriority;
  });
  // Maintenance Summary Calculations

  const totalTickets = tickets.length;

  const openTickets = tickets.filter(
    (ticket) => ticket.status === "OPEN",
  ).length;

  const assignedTickets = tickets.filter(
    (ticket) => ticket.status === "ASSIGNED",
  ).length;

  const inProgressTickets = tickets.filter(
    (ticket) => ticket.status === "IN_PROGRESS",
  ).length;

  const completedTickets = tickets.filter(
    (ticket) => ticket.status === "COMPLETED",
  ).length;

  const cancelledTickets = tickets.filter(
    (ticket) => ticket.status === "CANCELLED",
  ).length;

  // Fetch Maintenance Tickets

  const fetchTickets = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await getAllMaintenanceTickets();

      setTickets(response);
      return response;

      console.log("Maintenance Tickets:", response);
    } catch (err) {
      console.error("Error while fetching maintenance tickets:", err);

      setError("Unable to load maintenance tickets.");
    } finally {
      setLoading(false);
    }
  };

  const hanldeStartMaintenance = async () => {
    try {
      setActionLoading(true);
      await startMaintenance(selectedTicket.Id);
      alert(`Started maintenance for ticket ${selectedTicket.Id}`);
      const updatedTickets = await fetchTickets();
      const updatedTicket = updatedTickets.find(
        (ticket) => ticket.Id == selectedTicket.Id,
      );
      setSelectedTicket(updatedTicket || null);
    } catch (err) {
      console.log("Error while starting maintenance ", err);
      alert("Unable to start maintenance");
    } finally {
      setActionLoading(false);
    }
  };

  const handleAssignTechnician = async () => {
    if (!technicianName.trim()) {
      alert("Enter technician name to assign");
      return;
    }
    try {
      setActionLoading(true);
      await assignTechnician(selectedTicket.Id, technicianName.trim());
      alert("Technician assigned successfully");
      setTechnicianName("");
      await fetchTickets();
      const updatedTickets = await getAllMaintenanceTickets();
      const updatedTicket = updatedTickets.find(
        (ticket) => ticket.Id === selectedTicket.Id,
      );
      setSelectedTicket(updatedTicket || null);
    } catch (err) {
      console.log("Error while assigning technician", err);
      alert(err.response?.data?.message || "Unable to assign technician");
    } finally {
      setActionLoading(false);
    }
  };
  const handleCompleteMaintenance = async () => {
    try {
      setActionLoading(true);
      await completeMaintenance(selectedTicket.Id);
      alert(`Completed maintenance for ${selectedTicket.Id}`);
      const updatedTickets = await fetchTickets();
      const updatedTicket = updatedTickets.find(
        (ticket) => ticket.Id === selectedTicket.Id,
      );
      setSelectedTicket(updatedTicket || null);
    } catch (err) {
      console.log("Error while completing maintenance ", err);
      alert(err.response?.data?.message || "Unable to complete maintenance");
    } finally {
      setActionLoading(false);
    }
  };
  const handleCancelMaintenance = async () => {
    try {
      setActionLoading(true);
      await cancelMaintenance(selectedTicket.Id);
      alert(`Cancelled maintenance for ${selectedTicket.Id}`);
      const updatedTickets = await fetchTickets();
      const updatedTicket = updatedTickets.find(
        (ticket) => ticket.Id === selectedTicket.Id,
      );
      setSelectedTicket(updatedTicket || null);
    } catch (err) {
      console.log("Error while cancelling maintenance ", err);
      alert(err.response?.data?.message || "Unable to complete maintenance");
    } finally {
      setActionLoading(false);
    }
  };
  const sortedTickets = [...filteredTickets].sort((a, b) => {
    if (sortBy === "NEWEST") {
      return new Date(b.createdAt) - new Date(a.createdAt);
    }
    if (sortBy === "OLDEST") {
      return new Date(a.createdAt) - new Date(b.createdAt);
    }
    if (sortBy === "PRIORITY") {
      const priorityOrder = {
        CRITICAL: 4,
        HIGH: 3,
        MEDIUM: 2,
        LOW: 1,
      };
      return priorityOrder[b.priority] - priorityOrder[a.priority];
    }
    return 0;
  });

  useEffect(() => {
    fetchTickets();
  }, []);

  return (
    <div className="maintenance-page">
      {/* Page Header */}

      <div className="page-header">
        <div>
          <h1>Maintenance Management</h1>
          <p>Monitor and manage equipment maintenance tickets.</p>
        </div>
        {/* Maintenance Search and Filters */}

        <div className="maintenance-filters">
          <input
            type="text"
            placeholder="Search ticket, equipment, title or technician..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />

          <select
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value)}
          >
            <option value="ALL">All Statuses</option>
            <option value="OPEN">Open</option>
            <option value="ASSIGNED">Assigned</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="COMPLETED">Completed</option>
            <option value="CANCELLED">Cancelled</option>
          </select>

          <select
            value={priorityFilter}
            onChange={(e) => setPriorityFilter(e.target.value)}
          >
            <option value="ALL">All Priorities</option>
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
            <option value="CRITICAL">Critical</option>
          </select>
          <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
            <option value="NEWEST">Newest First</option>
            <option value="OLDEST">Oldest First</option>
            <option value="PRIORITY">Highest Priority</option>
          </select>

          <button
            className="clear-filters-btn"
            onClick={() => {
              setSearchTerm("");
              setStatusFilter("ALL");
              setPriorityFilter("ALL");
              setSortBy("NEWEST");
            }}
          >
            Clear Filters
          </button>
        </div>
      </div>

      {/* Loading and Error Messages */}

      {loading && <p>Loading maintenance tickets...</p>}

      {error && <p className="error-message">{error}</p>}

      {!loading && !error && (
        <>
          {/* Maintenance Summary Cards */}

          <div className="maintenance-summary">
            <div className="summary-card">
              <h3>Total Tickets</h3>
              <h2>{totalTickets}</h2>
            </div>

            <div className="summary-card">
              <h3>Open</h3>
              <h2>{openTickets}</h2>
            </div>

            <div className="summary-card">
              <h3>Assigned</h3>
              <h2>{assignedTickets}</h2>
            </div>

            <div className="summary-card">
              <h3>In Progress</h3>
              <h2>{inProgressTickets}</h2>
            </div>

            <div className="summary-card">
              <h3>Completed</h3>
              <h2>{completedTickets}</h2>
            </div>

            <div className="summary-card">
              <h3>Cancelled</h3>
              <h2>{cancelledTickets}</h2>
            </div>
          </div>

          {/* Maintenance Tickets Table */}

          <div className="maintenance-table-container">
            <div className="table-header">
              <h2>Maintenance Tickets</h2>
              <p>View and manage all maintenance requests.</p>
            </div>

            <div className="table-responsive">
              <table className="maintenance-table">
                <thead>
                  <tr>
                    <th>Ticket No.</th>
                    <th>Equipment</th>
                    <th>Title</th>
                    <th>Priority</th>
                    <th>Technician</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Actions</th>
                  </tr>
                </thead>

                <tbody>
                  {sortedTickets.length === 0 ? (
                    <tr>
                      <td colSpan="8" className="no-data">
                        No maintenance tickets found.
                      </td>
                    </tr>
                  ) : (
                    sortedTickets.map((ticket) => (
                      <tr key={ticket.Id}>
                        <td>{ticket.ticketNumber}</td>

                        <td>{ticket.equipmentCode}</td>

                        <td>{ticket.title}</td>

                        <td>
                          <span
                            className={`priority-badge ${ticket.priority.toLowerCase()}`}
                          >
                            {ticket.priority}
                          </span>
                        </td>

                        <td>{ticket.assignedTechnician || "Not Assigned"}</td>

                        <td>
                          <span
                            className={`maintenance-status ${ticket.status.toLowerCase()}`}
                          >
                            {ticket.status.replace("_", " ")}
                          </span>
                        </td>

                        <td>
                          {new Date(ticket.createdAt).toLocaleDateString()}
                        </td>

                        <td>
                          <button
                            className="view-ticket-btn"
                            onClick={() => {
                              setSelectedTicket(ticket);
                            }}
                          >
                            View
                          </button>
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
            </div>
          </div>
          {selectedTicket && (
            <div className="ticket-modal-overlay">
              <div className="ticket-modal">
                <div className="ticket-modal-header">
                  <div>
                    <h2>Maintenance Ticket Details</h2>
                    <p>{selectedTicket.ticketNumber}</p>
                  </div>

                  <button
                    className="close-modal-btn"
                    onClick={() => setSelectedTicket(null)}
                  >
                    ×
                  </button>
                </div>

                <div className="ticket-details-grid">
                  <div className="ticket-detail">
                    <label>Equipment Code</label>
                    <p>{selectedTicket.equipmentCode}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Equipment ID</label>
                    <p>{selectedTicket.equipmentId}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Ticket Title</label>
                    <p>{selectedTicket.title}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Priority</label>
                    <p>{selectedTicket.priority}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Status</label>
                    <p>{selectedTicket.status.replace("_", " ")}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Assigned Technician</label>
                    <p>{selectedTicket.assignedTechnician || "Not Assigned"}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Alert ID</label>
                    <p>{selectedTicket.alertId || "N/A"}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Created At</label>
                    <p>{new Date(selectedTicket.createdAt).toLocaleString()}</p>
                  </div>

                  <div className="ticket-detail">
                    <label>Completed At</label>
                    <p>
                      {selectedTicket.completedAt
                        ? new Date(selectedTicket.completedAt).toLocaleString()
                        : "Not Completed"}
                    </p>
                  </div>

                  <div className="ticket-detail full-width">
                    <label>Description</label>
                    <p>{selectedTicket.description}</p>
                  </div>
                </div>
                {(selectedTicket.status === "OPEN" ||
                  selectedTicket.status === "ASSIGNED") && (
                  <div className="technician-assignment">
                    {selectedTicket.assignedTechnician ? (
                      <>
                        <h3>Update Technician</h3>

                        <p>Change Technician for this fault.</p>
                      </>
                    ) : (
                      <>
                        <h3>Assign Technician</h3>

                        <p>
                          Assign a technician to inspect and resolve this
                          equipment fault.
                        </p>
                        <button className="cancel-btn">Cancel</button>
                      </>
                    )}

                    <input
                      type="text"
                      placeholder="Enter technician name"
                      value={technicianName}
                      onChange={(e) => setTechnicianName(e.target.value)}
                    />

                    <button
                      className="assign-btn"
                      onClick={handleAssignTechnician}
                      disabled={actionLoading}
                    >
                      {actionLoading ? "Assigning..." : "Assign Technician"}
                    </button>
                  </div>
                )}

                <div className="ticket-modal-footer">
                  <div className="ticket-actions">
                    {selectedTicket.status === "ASSIGNED" && (
                      <>
                        <button
                          className="start-btn"
                          onClick={hanldeStartMaintenance}
                          disabled={actionLoading}
                        >
                          {actionLoading ? "Starting..." : "Start Maintenance"}
                        </button>
                        <button className="cancel-btn">Cancel</button>
                      </>
                    )}
                    {selectedTicket.status === "IN_PROGRESS" && (
                      <>
                        <button
                          className="complete-btn"
                          onClick={handleCompleteMaintenance}
                          disabled={actionLoading}
                        >
                          {actionLoading
                            ? "Completing..."
                            : "Compelete Maintenance"}
                        </button>
                        <button className="cancel-btn">Cancel</button>
                      </>
                    )}
                  </div>
                  <button
                    className="close-ticket-btn"
                    onClick={() => setSelectedTicket(null)}
                  >
                    Close
                  </button>
                </div>
              </div>
            </div>
          )}
        </>
      )}
    </div>
  );
}

export default Maintenance;
