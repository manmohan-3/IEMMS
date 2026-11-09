//SHIVAAAHHHH

import { useState } from "react";

import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Sidebar from "./components/Sidebar";
import Equipment from "./pages/Equipment";

import "./App.css";

function App() {

    const [isLoggedIn, setIsLoggedIn] = useState(
        !!sessionStorage.getItem("token")
    );

    const [activePage, setActivePage] = useState("Dashboard");

    function handleLogout() {

        sessionStorage.removeItem("token");
        sessionStorage.removeItem("tokenType");

        setIsLoggedIn(false);
        setActivePage("Dashboard");
    }

    return (

        <div className="app-container">

            {!isLoggedIn ? (

                <>
                    <header className="app-header">
                        <div className="brand">
                            <div className="brand-icon">I</div>

                            <div>
                                <h1>IEMMS</h1>
                                <span>Industrial Intelligence Platform</span>
                            </div>
                        </div>
                    </header>

                    <main className="login-main">
                        <Login
                            onLoginSuccess={() => setIsLoggedIn(true)}
                        />
                    </main>

                    <footer className="app-footer">
                        <span>© 2026 IEMMS</span>
                        <span>
                            Industrial Equipment Monitoring & Maintenance
                        </span>
                    </footer>
                </>

            ) : (

                <div className="authenticated-layout">

                    <Sidebar
                        activePage={activePage}
                        setActivePage={setActivePage}
                        onLogout={handleLogout}
                    />

                    <div className="main-content">

                        <header className="top-navbar">

                            <div>
                                <h2>{activePage}</h2>
                                <span>
                                    Industrial Equipment Monitoring System
                                </span>
                            </div>

                            <div className="top-navbar-right">
                                <span className="top-status-dot"></span>
                                <span>System Online</span>

                                <div className="user-avatar">A</div>
                            </div>

                        </header>

                        <main className="page-content">

                            {activePage === "Dashboard" ? (
                                <Dashboard />
                            ) : activePage==="Equipment" ?(<Equipment/>):
                             (
                                <div className="coming-soon">
                                    <h2>{activePage}</h2>
                                    <p>
                                        This module is coming soon.
                                    </p>
                                </div>
                            )}

                        </main>

                    </div>

                </div>

            )}

        </div>
    );
}

export default App;