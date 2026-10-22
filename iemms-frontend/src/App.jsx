//SHIVAAAHHHH

import Login from "./pages/Login";

function App() {
return (
   <div className="app-container"> 
   <header className="app-header"> 
    <div className="brand"> 
    <div className="brand-icon">I</div>


      <div>
        <h1>IEMMS</h1>
        <span>Industrial Intelligence Platform</span>
      </div>
    </div>

    <span className="system-status">
      <span className="status-dot"></span>
      Equipment Management System
    </span>
  </header>

  <main className="login-main">
    <section className="login-intro">
      <span className="eyebrow">INDUSTRIAL OPERATIONS</span>

      <h2>
        Monitor smarter.
        <br />
        Maintain better.
      </h2>

      <p>
        A centralized platform to monitor industrial equipment,
        manage alerts, and streamline maintenance operations.
      </p>

      <div className="feature-list">
        <div className="feature-item">
          <span className="feature-check">✓</span>
          Real-time equipment monitoring
        </div>

        <div className="feature-item">
          <span className="feature-check">✓</span>
          Centralized alert management
        </div>

        <div className="feature-item">
          <span className="feature-check">✓</span>
          Maintenance workflow tracking
        </div>
      </div>
    </section>

    <Login />
  </main>

  <footer className="app-footer">
    <span>© 2026 IEMMS</span>
    <span>Industrial Equipment Monitoring & Maintenance</span>
  </footer>
</div>


);
}

export default App;
