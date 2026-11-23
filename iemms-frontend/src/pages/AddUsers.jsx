//SHIVAAAAHHH

import { useState } from "react";
import { createUser } from "../services/userService";
import "./AddUsers.css";

function AddUser({ onClose, onUserCreated }) {
  const currentRole = sessionStorage.getItem("role");

  const availableRoles =
    currentRole === "ADMIN"
      ? ["MANAGER", "TECHNICIAN"]
      : currentRole === "MANAGER"
        ? ["TECHNICIAN"]
        : [];

  const [formData, setFormData] = useState({
    fullName: "",
    username: "",
    email: "",
    mobileNumber: "",
    password: "",
    role: availableRoles[0] || "",
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

    try {
      setLoading(true);
      setError("");

      await createUser(formData);

      alert("User created successfully!");

      onUserCreated();
      onClose();
    } catch (err) {
      setError(err.response?.data?.message || "Unable to create user");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add-user-overlay">
      <div className="add-user-modal">
        <div className="add-user-header">
          <h2>Add New User</h2>

          <button onClick={onClose} className="close-btn">&times;</button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="add-user-form">
            <div className="form-group">
              <label>Full Name</label>

              <input
                type="text"
                name="fullName"
                value={formData.fullName}
                onChange={handleChange}
                placeholder="Enter full name"
                maxLength="50"
                required
              />
            </div>

            <div className="form-group">
              <label>Username</label>

              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                placeholder="Enter username"
                maxLength="50"
                required
              />
            </div>

            <div className="form-group">
              <label>Email</label>

              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Enter email address"
                maxLength="50"
                required
              />
            </div>

            <div className="form-group">
              <label>Mobile Number</label>

              <input
                type="text"
                name="mobileNumber"
                value={formData.mobileNumber}
                onChange={handleChange}
                placeholder="Enter mobile number"
                maxLength="15"
                required
              />
            </div>

            <div className="form-group">
              <label>Password</label>

              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Enter password"
                required
              />
            </div>

            <div className="form-group">
              <label>User Role</label>

              <select
                name="role"
                value={formData.role}
                onChange={handleChange}
                required
              >
                {availableRoles.map((role) => (
                  <option key={role} value={role}>
                    {role}
                  </option>
                ))}
              </select>
            </div>
          </div>

          {error && <p className="add-user-error">{error}</p>}

          <div className="add-user-cancel-btn">
            <button type="button" onClick={onClose} className="cancel-user-btn">
              Cancel
            </button>

            <button
              type="submit"
              className="create-user-btn"
              disabled={loading}
            >
              {loading ? "Creating..." : "Create User"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddUser;
