import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";
import ParkingCard from "../components/ParkingCard";

import api from "../services/api";

function Parking() {

  const [parking, setParking] = useState([]);

  const [loading, setLoading] = useState(true);

  const [error, setError] = useState("");

  useEffect(() => {

    const loadParking = async () => {

      try {

        const response = await api.get(
          "/api/parking"
        );

        setParking(response.data);

      } catch (err) {

        console.error(err);

        setError(
          "Unable to load parking locations."
        );

      } finally {

        setLoading(false);

      }
    };

    loadParking();

  }, []);

  return (
    <>
      <Navbar />

      <div className="page">

        <h1>Available Parking</h1>

        <p className="page-subtitle">
          Select a parking location to reserve a slot.
        </p>

        {loading && (
          <p>Loading parking...</p>
        )}

        {error && (
          <p className="error">
            {error}
          </p>
        )}

        <div className="parking-grid">

          {parking.map((item) => (

            <ParkingCard
              key={item.id}
              parking={item}
            />

          ))}

        </div>

      </div>
    </>
  );
}

export default Parking;