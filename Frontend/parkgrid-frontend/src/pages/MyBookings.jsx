import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";
import api from "../services/api";

function MyBookings() {

  const [bookings, setBookings] =
    useState([]);

  const [message, setMessage] =
    useState("");

  const userId =
    localStorage.getItem("userId");

  const loadBookings = async () => {

    if (!userId) {
      setMessage("Please login first.");
      return;
    }

    try {

      const response = await api.get(
        `/api/bookings/user/${userId}`
      );

      setBookings(response.data);

    } catch (error) {

      console.error(error);

      setMessage(
        "Unable to load bookings."
      );

    }
  };

  useEffect(() => {

    loadBookings();

  }, []);

  const cancelBooking = async (id) => {

    try {

      await api.put(
        `/api/bookings/${id}/cancel`
      );

      loadBookings();

    } catch (error) {

      console.error(error);

      setMessage(
        "Unable to cancel booking."
      );

    }
  };

  return (
    <>
      <Navbar />

      <div className="page">

        <h1>My Bookings</h1>

        {message && (
          <p className="message">
            {message}
          </p>
        )}

        <div className="booking-list">

          {bookings.map((booking) => (

            <div
              className="booking-card"
              key={booking.id}
            >

              <h2>
                Booking #{booking.id}
              </h2>

              <p>
                Vehicle:
                <strong>
                  {" "}{booking.vehicleNumber}
                </strong>
              </p>

              <p>
                Parking ID:
                {" "}{booking.parkingId}
              </p>

              <p>
                Slot ID:
                {" "}{booking.slotId}
              </p>

              <p>
                Start:
                {" "}{booking.startTime}
              </p>

              <p>
                End:
                {" "}{booking.endTime}
              </p>

              <p>
                Status:
                <strong>
                  {" "}{booking.status}
                </strong>
              </p>

              {booking.status === "CONFIRMED" && (

                <button
                  className="danger-btn"
                  onClick={() =>
                    cancelBooking(booking.id)
                  }
                >
                  Cancel Booking
                </button>

              )}

            </div>

          ))}

        </div>

      </div>
    </>
  );
}

export default MyBookings;