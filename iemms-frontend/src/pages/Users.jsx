//SHIVAAAHHHHH

import { getAllUsers, enableUser, disableUser } from "../services/userService";
import { useState, useEffect } from "react";
import "./Users.css";
import AddUsers from "./AddUsers";

function Users() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [showAddUser, setShowAddUser] = useState(false);
  const fetchUsers = async () => {
    try {
      setLoading(true);
      setError("");
      const response = await getAllUsers();
      setUsers(response);
      console.log(response);
    } catch (err) {
      setError(err.response?.data?.message || "Unable to load users");
      console.log("Unable to load users");
    } finally {
      setLoading(false);
    }
  };
  const handleUserStatus = async (userId, enabled) => {
    try {
      setError("");
      if (enabled) {
        await disableUser(userId);
      } else {
        await enableUser(userId);
      }
      await fetchUsers();
    } catch (err) {
      setError(err.resonse?.data?.message || "Unable to perform action");
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);
  return (
    <div className="users-page">
      <div className="users-header">
        <div>
          <h1>User Management</h1>
          <p>Manage system users and their access permissions.</p>
        </div>
      </div>
      <button className="add-user-btn" onClick={() => setShowAddUser(true)}>
        + Add User
      </button>

      {loading && <p>Loading users...</p>}

      {error && <p>{error}</p>}

      {!loading && !error && (
        <div className="users-table-container">
          <table className="users-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Role</th>
                <th>Status</th>
                <th>Created At</th>
                <th>Actions</th>
              </tr>
            </thead>

            <tbody>
              {users.length > 0 ? (
                users.map((user) => (
                  <tr key={user.Id}>
                    <td>{user.Id}</td>
                    <td>{user.fullName}</td>
                    <td>{user.username}</td>
                    <td>{user.email}</td>
                    <td>{user.mobileNumber}</td>

                    <td>
                      <span className={`user-role ${user.role.toLowerCase()}`}>
                        {user.role}
                      </span>
                    </td>

                    <td>
                      <span
                        className={`user-status ${user.enabled ? "active" : "disabled"}`}
                      >
                        {user.enabled ? "Active" : "Disabled"}
                      </span>
                    </td>

                    <td>{new Date(user.createdAt).toLocaleDateString()}</td>

                    <td>
                      <button
                        className={`user-action-btn ${user.enabled ? "disable-user-btn" : "enable-user-btn"}`}
                        onClick={() => handleUserStatus(user.Id, user.enabled)}
                      >
                        {user.enabled ? "Disable" : "Enable"}
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="9">No users found.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}
      {showAddUser && (
        <AddUsers
          onClose={() => setShowAddUser(false)}
          onUserCreated={fetchUsers}
        />
      )}
    </div>
  );
}

export default Users;
