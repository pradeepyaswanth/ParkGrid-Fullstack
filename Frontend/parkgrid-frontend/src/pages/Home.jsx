import { useEffect } from "react";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";
import api from "../services/api";

function Home() {

  useEffect(() => {

    console.log("Testing backend connection...");

    api.get("/api/parking")
      .then((response) => {

        console.log("✅ BACKEND CONNECTED");
        console.log("Parking data:", response.data);

      })
      .catch((error) => {

        console.error("❌ BACKEND CONNECTION FAILED");
        console.error(error);

      });

  }, []);

  return (
    <>
      <Navbar />

      <section className="hero">

        <div className="hero-content">

          <p className="eyebrow">
            SMART PARKING SOLUTION
          </p>

          <h1>
            Find Parking.
            <br />
            <span>Travel Smarter.</span>
          </h1>

          <p className="hero-description">
            Find available parking slots, reserve your slot,
            and manage your bookings easily.
          </p>

          <div className="hero-buttons">

            <Link
              to="/parking"
              className="primary-btn"
            >
              Find Parking
            </Link>

            <Link
              to="/login"
              className="secondary-btn"
            >
              Login
            </Link>

          </div>

        </div>

        <div className="hero-card">

          <div className="parking-symbol">
            P
          </div>

          <h2>PARK SMART</h2>

          <p>ParkGrid</p>

        </div>

      </section>

      <section className="features">

        <div className="feature">

          <div className="feature-icon">
            🚗
          </div>

          <h3>
            Real-time Availability
          </h3>

          <p>
            Check available parking slots instantly.
          </p>

        </div>

        <div className="feature">

          <div className="feature-icon">
            🛡️
          </div>

          <h3>
            Secure & Reliable
          </h3>

          <p>
            Your booking and account data is protected.
          </p>

        </div>

        <div className="feature">

          <div className="feature-icon">
            ⏱️
          </div>

          <h3>
            Save Time
          </h3>

          <p>
            Reserve your parking slot in seconds.
          </p>

        </div>

      </section>

    </>
  );
}

export default Home;