//SHIVAAAHHHH
import { useEffect, useState } from "react";
import { getAllEquipment } from "../services/equipmentService";
import "./Equipment.css";

function Equipment() {

    const [equipmentList, setEquipmentList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [totalElements, setTotalElements] = useState(0);

    const pageSize = 5;

    // Fetch equipment from backend
    const fetchEquipment = async (page) => {
        try {
            setLoading(true);
            setError("");

            const data = await getAllEquipment(page, pageSize);

            setEquipmentList(data.content);
            setCurrentPage(data.number);
            setTotalPages(data.totalPages);
            setTotalElements(data.totalElements);

        } catch (err) {
            console.error("Equipment fetching error:", err);

            setError(
                err.response?.data?.message ||
                "Unable to fetch equipment details."
            );
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchEquipment(0);
    }, []);

    return (
        <div className="equipment-container">

            {/* Page Heading */}
            <div className="equipment-header">
                <div>
                    <h1>Equipment Management</h1>
                    <p>Monitor and manage your industrial equipment.</p>
                </div>

                <div className="equipment-total">
                    <span>Total Equipment</span>
                    <h2>{totalElements}</h2>
                </div>
            </div>

            {/* Equipment Table */}
            <div className="equipment-table-container">

                <div className="equipment-table-heading">
                    <h2>Equipment Inventory</h2>
                    <span>Page {currentPage + 1} of {totalPages}</span>
                </div>

                {loading ? (
                    <div className="equipment-message">
                        Loading equipment...
                    </div>
                ) : error ? (
                    <div className="equipment-error">
                        {error}
                    </div>
                ) : equipmentList.length === 0 ? (
                    <div className="equipment-message">
                        No equipment records found.
                    </div>
                ) : (

                    <div className="equipment-table-wrapper">

                        <table className="equipment-table">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Equipment Code</th>
                                    <th>Name</th>
                                    <th>Type</th>
                                    <th>Manufacturer</th>
                                    <th>Location</th>
                                    <th>Installation Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>

                            <tbody>
                                {equipmentList.map((equipment) => (

                                    <tr key={equipment.Id}>

                                        <td>#{equipment.Id}</td>

                                        <td className="equipment-code">
                                            {equipment.equipmentCode}
                                        </td>

                                        <td>{equipment.name}</td>

                                        <td>{equipment.type}</td>

                                        <td>{equipment.manufacturer}</td>

                                        <td>{equipment.location}</td>

                                        <td>{equipment.installationDate}</td>

                                        <td>
                                            <span className={`equipment-status ${equipment.status?.toLowerCase()}`}>
                                                {equipment.status}
                                            </span>
                                        </td>

                                    </tr>

                                ))}
                            </tbody>

                        </table>

                    </div>
                )}

                {/* Pagination */}
                {!loading && !error && totalPages > 0 && (

                    <div className="equipment-pagination">

                        <button
                            disabled={currentPage === 0}
                            onClick={() => fetchEquipment(currentPage - 1)}
                        >
                            ← Previous
                        </button>

                        <span>
                            Page {currentPage + 1} / {totalPages}
                        </span>

                        <button
                            disabled={currentPage >= totalPages - 1}
                            onClick={() => fetchEquipment(currentPage + 1)}
                        >
                            Next →
                        </button>

                    </div>

                )}

            </div>

        </div>
    );
}

export default Equipment;