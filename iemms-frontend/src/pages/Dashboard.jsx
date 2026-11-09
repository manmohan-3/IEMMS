//SHIVAAAHHH
import { getDashboardData } from "../services/dashboardService";
import { useState, useEffect } from "react";
import "./Dashboard.css";

function Dashboard() {

    const [dashboardData, setDashboardData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        loadDashboard();
    }, []);

    async function loadDashboard() {
        try {
            const data = await getDashboardData();
            setDashboardData(data);
        }
        catch (error) {
            console.error("Dashboard loading failed:", error);
            setError("Unable to load the Dashboard");
        }
        finally {
            setLoading(false);
        }
    }

    if (loading) {
        return <h2>Loading Dashboard...</h2>;
    }

    if (error) {
        return <h2>{error}</h2>;
    }

    if (!dashboardData) {
        return <h2>No dashboard data available.</h2>;
    }

    const equipment = dashboardData.equipment;
    const alert = dashboardData.alert;
    const maintenance = dashboardData.maintenance;

    return (
        <div className="dashboard-container">

            <h1>IEMMS Dashboard</h1>
            <p className="dashboard-subtitle">
                Welcome to Industrial Equipment Monitoring System
            </p>

            {/* Equipment Section */}

            <h2>Equipment Overview</h2>

            <div className="dashboard-cards">

                <div className="dashboard-card">
                    <h3>Total Equipment</h3>
                    <p>{equipment.totalEquipment}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Active Equipment</h3>
                    <p>{equipment.activeEquipment}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Inactive Equipment</h3>
                    <p>{equipment.inactiveEquipment}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Under Maintenance</h3>
                    <p>{equipment.maintenanceEquipment}</p>
                </div>

            </div>


            {/* Alert Section */}

            <h2>Alert Overview</h2>

            <div className="dashboard-cards">

                <div className="dashboard-card">
                    <h3>Total Alerts</h3>
                    <p>{alert.totalAlerts}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Critical Alerts</h3>
                    <p>{alert.criticalAlerts}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Open Alerts</h3>
                    <p>{alert.openAlerts}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Resolved Alerts</h3>
                    <p>{alert.resolvedAlerts}</p>
                </div>

            </div>


            {/* Maintenance Section */}

            <h2>Maintenance Overview</h2>

            <div className="dashboard-cards">

                <div className="dashboard-card">
                    <h3>Total Tickets</h3>
                    <p>{maintenance.totalTicket}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Open Tickets</h3>
                    <p>{maintenance.openTicket}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Assigned Tickets</h3>
                    <p>{maintenance.assignedTicket}</p>
                </div>

                <div className="dashboard-card">
                    <h3>In Progress</h3>
                    <p>{maintenance.inprogressTicket}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Completed</h3>
                    <p>{maintenance.completedTicket}</p>
                </div>

                <div className="dashboard-card">
                    <h3>Cancelled</h3>
                    <p>{maintenance.cancelledTicket}</p>
                </div>

            </div>

        </div>
    );
}

export default Dashboard;