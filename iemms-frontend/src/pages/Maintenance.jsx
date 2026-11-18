//SHIVAAAHHH

import { useState, useEffect } from "react";
import { getAllMaintenanceTickets } from "../services/maintenanceService";
import "./Maintenance.css";

function Maintenance() {

    const [tickets, setTickets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    // Maintenance Summary Calculations

    const totalTickets = tickets.length;

    const openTickets = tickets.filter(
        ticket => ticket.status === "OPEN"
    ).length;

    const assignedTickets = tickets.filter(
        ticket => ticket.status === "ASSIGNED"
    ).length;

    const inProgressTickets = tickets.filter(
        ticket => ticket.status === "IN_PROGRESS"
    ).length;

    const completedTickets = tickets.filter(
        ticket => ticket.status === "COMPLETED"
    ).length;

    const cancelledTickets = tickets.filter(
        ticket => ticket.status === "CANCELLED"
    ).length;

    // Fetch Maintenance Tickets

    const fetchTickets = async () => {

        try {
            setLoading(true);
            setError("");

            const response = await getAllMaintenanceTickets();

            setTickets(response);

            console.log("Maintenance Tickets:", response);

        } catch (err) {

            console.error("Error while fetching maintenance tickets:", err);

            setError("Unable to load maintenance tickets.");

        } finally {
            setLoading(false);
        }
    };

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
            </div>

            {/* Loading and Error Messages */}

            {loading && (
                <p>Loading maintenance tickets...</p>
            )}

            {error && (
                <p className="error-message">{error}</p>
            )}

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

                                    {tickets.length === 0 ? (

                                        <tr>
                                            <td colSpan="8" className="no-data">
                                                No maintenance tickets found.
                                            </td>
                                        </tr>

                                    ) : (

                                        tickets.map(ticket => (

                                            <tr key={ticket.Id}>

                                                <td>{ticket.ticketNumber}</td>

                                                <td>{ticket.equipmentCode}</td>

                                                <td>{ticket.title}</td>

                                                <td>
                                                    <span className={`priority-badge ${ticket.priority.toLowerCase()}`}>
                                                        {ticket.priority}
                                                    </span>
                                                </td>

                                                <td>
                                                    {ticket.assignedTechnician || "Not Assigned"}
                                                </td>

                                                <td>
                                                    <span className={`maintenance-status ${ticket.status.toLowerCase()}`}>
                                                        {ticket.status.replace("_", " ")}
                                                    </span>
                                                </td>

                                                <td>
                                                    {new Date(ticket.createdAt).toLocaleDateString()}
                                                </td>

                                                <td>
                                                    <button className="view-ticket-btn">
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

                </>
            )}

        </div>

    );
}

export default Maintenance;