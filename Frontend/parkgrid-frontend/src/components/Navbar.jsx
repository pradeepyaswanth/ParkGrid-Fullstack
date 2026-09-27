import { Link, useNavigate } from "react-router-dom";

function Navbar() {

  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("userId");

    navigate("/login");
  };

  return (
    <nav className="navbar">

      <div className="logo">
        <span className="logo-icon">🌿</span>
        ParkGrid
      </div>

      <div className="nav-links">

        <Link to="/">Home</Link>

        <Link to="/parking">Parking</Link>

        {token && (
          <Link to="/my-bookings">
            My Bookings
          </Link>
        )}

        {!token ? (
          <>
            <Link className="login-btn" to="/login">
              Login
            </Link>

            <Link className="register-btn" to="/register">
              Register
            </Link>
          </>
        ) : (
          <button
            className="logout-btn"
            onClick={logout}
          >
            Logout
          </button>
        )}

      </div>

    </nav>
  );
}

export default Navbar;