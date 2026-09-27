import { useNavigate } from "react-router-dom";

function ParkingCard({ parking }) {

  const navigate = useNavigate();

  return (
    <div className="parking-card">

      <div className="parking-icon">
        P
      </div>

      <h2>{parking.location}</h2>

      <p>
        Total Slots:
        <strong> {parking.totalSlots}</strong>
      </p>

      <p className="available-text">
        Available:
        <strong> {parking.availableSlots}</strong>
      </p>

      <button
        onClick={() =>
          navigate(`/booking?parkingId=${parking.id}`)
        }
      >
        View Slots
      </button>

    </div>
  );
}

export default ParkingCard;