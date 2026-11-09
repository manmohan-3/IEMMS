//SHIVAAAHHH
import "./Sidebar.css";

function Sidebar({ activePage, setActivePage, onLogout }) {

    const menuItems = [
        { name: "Dashboard", icon: "▦" },
        { name: "Equipment", icon: "⚙" },
        { name: "Alerts", icon: "⚠" },
        { name: "Maintenance", icon: "🔧" },
        { name: "Users", icon: "♙" }
    ];

    return (
        <aside className="sidebar">

            <div className="sidebar-brand">
                <div className="sidebar-logo">I</div>

                <div>
                    <h2>IEMMS</h2>
                    <span>Industrial Intelligence</span>
                </div>
            </div>

            <div className="sidebar-menu">

                <p className="menu-heading">MAIN MENU</p>

                {menuItems.map((item) => (
                    <button
                        key={item.name}
                        className={`sidebar-item ${
                            activePage === item.name ? "active" : ""
                        }`}
                        onClick={() => setActivePage(item.name)}
                    >
                        <span className="sidebar-icon">{item.icon}</span>
                        <span>{item.name}</span>
                    </button>
                ))}

            </div>

            <div className="sidebar-bottom">

                <div className="sidebar-system">
                    <span className="system-indicator"></span>
                    <div>
                        <strong>System Online</strong>
                        <small>All services operational</small>
                    </div>
                </div>

                <button className="logout-button" onClick={onLogout}>
                    <span>⇥</span>
                    Logout
                </button>

            </div>

        </aside>
    );
}

export default Sidebar;