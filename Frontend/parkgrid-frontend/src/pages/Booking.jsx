import { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";
import SlotGrid from "../components/SlotGrid";

import api from "../services/api";

function Booking() {

  const [searchParams] = useSearchParams();

  const navigate = useNavigate();

  const parkingId =
    searchParams.get("parkingId");

  const [slots, setSlots] = useState([]);

  const [selectedSlot, setSelectedSlot] =
    useState(null);

  const [vehicleNumber, setVehicleNumber] =
    useState("");

  const [startTime, setStartTime] =
    useState("");

  const [endTime, setEndTime] =
    useState("");

  const [message, setMessage] =
    useState("");

  useEffect(() => {

    if (!parkingId) return;

    const loadSlots = async () => {

      try {

        const response = await api.get(
          `/api/parking/${parkingId}/slots`
        );

        setSlots(response.data);

      } catch (error) {

        console.error(error);

        setMessage(
          "Unable to load parking slots."
        );

      }

    };

    loadSlots();

  }, [parkingId]);

  const createBooking = async (e) => {

    e.preventDefault();

    const userId =
      localStorage.getItem("userId");

    if (!userId) {

      setMessage(
        "Please login before booking."
      );

      return;

    }

    if (!selectedSlot) {

      setMessage(
        "Please select a parking slot."
      );

      return;

    }

    try {

      const response = await api.post(
        "/api/bookings",
        {
          userId: Number(userId),

          parkingId: Number(parkingId),

          slotId: selectedSlot.id,

          vehicleNumber,

          startTime,

          endTime,
        }
      );

      setMessage(
        `Booking successful! Booking ID: ${response.data.id}`
      );

      setTimeout(() => {
        navigate("/my-bookings");
      }, 1500);

    } catch (error) {

      console.error(error);

      setMessage(
        error.response?.data?.message ||
        "Booking failed."
      );

    }

  };

  return (
    <>
      <Navbar />

      <div className="page">

        <h1>Select Parking Slot</h1>

        <p className="page-subtitle">
          Parking ID: {parkingId}
        </p>

        <SlotGrid
          slots={slots}
          onSelect={setSelectedSlot}
        />

        {selectedSlot && (

          <div className="selected-slot">

            Selected Slot:
            <strong>
              {" "}{selectedSlot.slotNumber}
            </strong>

          </div>

        )}

        <form
          className="booking-form"
          onSubmit={createBooking}
        >

          <input
            type="text"
            placeholder="Vehicle Number"
            value={vehicleNumber}
            onChange={(e) =>
              setVehicleNumber(e.target.value)
            }
            required
          />

          <label>
            Start Time
          </label>

          <input
            type="datetime-local"
            value={startTime}
            onChange={(e) =>
              setStartTime(e.target.value)
            }
            required
          />

          <label>
            End Time
          </label>

          <input
            type="datetime-local"
            value={endTime}
            onChange={(e) =>
              setEndTime(e.target.value)
            }
            required
          />

          <button type="submit">
            Reserve Slot
          </button>

        </form>

        {message && (
          <div className="message">
            {message}
          </div>
        )}

      </div>
    </>
  );
}

export default Booking;