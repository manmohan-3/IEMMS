//SHIVAAAHHHH
import { useEffect, useState } from "react";
import { getAllEquipment,deleteEquipment } from "../services/equipmentService";
import "./Equipment.css";
import AddEquipment from "./AddEquipment";
import EditEquipment from "./EditEquipment";

function Equipment() {

    const [equipmentList, setEquipmentList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [selectedStatus,setSelectedStatus]=useState("");
    const [searchTerm,setSearchTerm]=useState("");

    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const [showAddModal,setShowAddModal]=useState(false);
    const [selectedEquipment, setSelectedEquipment] = useState(null);
    const [showActionDialog, setShowActionDialog] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);

    const pageSize = 5;

    // Fetch equipment from backend
    const fetchEquipment = async (page, status=selectedStatus,search=searchTerm) => {
        try {
            setLoading(true);
            setError("");

            const data = await getAllEquipment(page, pageSize,status,search);

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
    const handleDoubleClick=(equipment)=>{
        setSelectedEquipment(equipment);
        setShowActionDialog(true);
    }

    useEffect(() => {
        fetchEquipment(0);
    }, []);
    const handleEquipmentAdded=(equipment)=>{
        setSelectedStatus("");
        setSearchTerm("");
        fetchEquipment(0,"","");
    };
    const handleUpdateSelected=()=>{
        setShowActionDialog(false);
        setShowEditModal(true);
    }
    const handleDeleteEquipment=async (equipment)=>{
        const confirm=window.confirm(`Are you to delete equipment ${equipment.name}`);
        if(!confirm){
            return;
        }
        try{
            const response = await deleteEquipment(equipment.Id);
            setSelectedEquipment(null);
            setShowActionDialog(false);
            fetchEquipment(0);
        }
        catch(err){
            console.log("Could not delete equipment ",err);
            alert(err.response?.data?.message||"unable to delete equipment");
        }

    }


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
                    <button className="add-equipment-btn" onClick={()=>{setShowAddModal(true)}}>
                        + Add Equipment
                    </button>
                </div>
            </div>

            {/* Equipment Table */}
            <div className="equipment-table-heading">
                <h2>Equipment Inventory</h2>
                <div className="equipment-search">
                    <input type="text" placeholder="Search name, code, manufacturer..." value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}onKeyDown={(e) => {
                            if (e.key === "Enter") {fetchEquipment(0, selectedStatus, searchTerm);}}}
        />
        <button onClick={() =>fetchEquipment(0, selectedStatus, searchTerm)}>
            Search
        </button>
            </div>
            <div className="equipment-filter">
                <label htmlFor="statusFilter">Status:</label>
                <select id="statusFilter" value={selectedStatus} onChange={(e) => {const status = e.target.value;
                setSelectedStatus(status);fetchEquipment(0, status, searchTerm);}}>
                       <option value="">All Equipment</option>
                        <option value="ACTIVE">Active</option>
                        <option value="INACTIVE">Inactive</option>
                        <option value="MAINTENANCE">Under Maintenance</option>
                </select>
            </div>      
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

                                    <tr key={equipment.Id} onDoubleClick={()=>{handleDoubleClick(equipment)}}>

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
                {showAddModal && (
                    <AddEquipment onClose={() => setShowAddModal(false)}
                                onEquipmentAdded={handleEquipmentAdded}/>
                )}
                {
                    showActionDialog && selectedEquipment &&(
                        <div className="modal-overlay">
                            <div className="equipment-modal">
                                <h2>Equipment Actions</h2>
                                <p>What would you like to do with{" "}
                                <strong>{selectedEquipment.name}</strong>?</p>
                                    <div className="form-actions">
                                    <button type="button" onClick={handleUpdateSelected}>Update Equipment</button>
                                        <button type="button" onClick={() =>{handleDeleteEquipment(selectedEquipment) }}>Delete Equipment</button>

                                <button type="button" onClick={() => setShowActionDialog(false)}>Cancel</button>

                                </div>

                            </div>
                        </div>
                    )
                }
                {
                    showEditModal && selectedEquipment &&(
                        <EditEquipment equipment={selectedEquipment}
                            onClose={()=>{setShowEditModal(false)}}
                            onEquipmentUpdated={()=>{fetchEquipment(currentPage)}}
                        />
                    )
                }

            </div>

        
    );
}

export default Equipment;