import { useState } from "react";

import Navbar from "../components/Navbar";
import api from "../services/api";

function Billing() {

  const [billId, setBillId] =
    useState("");

  const [bill, setBill] =
    useState(null);

  const [message, setMessage] =
    useState("");

  const searchBill = async () => {

    try {

      const response = await api.get(
        `/api/billing/${billId}`
      );

      setBill(response.data);

      setMessage("");

    } catch (error) {

      console.error(error);

      setMessage(
        "Bill not found."
      );

    }

  };

  const markAsPaid = async () => {

    try {

      const response = await api.put(
        `/api/billing/${billId}/status?status=PAID`
      );

      setBill(response.data);

    } catch (error) {

      console.error(error);

      setMessage(
        "Unable to update payment."
      );

    }

  };

  return (
    <>
      <Navbar />

      <div className="page">

        <h1>Billing</h1>

        <div className="billing-search">

          <input
            type="number"
            placeholder="Enter Bill ID"
            value={billId}
            onChange={(e) =>
              setBillId(e.target.value)
            }
          />

          <button onClick={searchBill}>
            Search Bill
          </button>

        </div>

        {message && (
          <p className="error">
            {message}
          </p>
        )}

        {bill && (

          <div className="bill-card">

            <h2>
              Bill #{bill.id}
            </h2>

            <p>
              Booking ID:
              {" "}{bill.bookingId}
            </p>

            <p>
              Vehicle:
              {" "}{bill.vehicleNumber}
            </p>

            <p>
              Hourly Rate:
              {" "}₹{bill.hourlyRate}
            </p>

            <p>
              Duration:
              {" "}{bill.durationHours} hours
            </p>

            <h2>
              Total: ₹{bill.amount}
            </h2>

            <p>
              Payment Status:
              {" "}
              <strong>
                {bill.paymentStatus}
              </strong>
            </p>

            {bill.paymentStatus === "PENDING" && (

              <button onClick={markAsPaid}>
                Mark as Paid
              </button>

            )}

          </div>

        )}

      </div>
    </>
  );
}

export default Billing;