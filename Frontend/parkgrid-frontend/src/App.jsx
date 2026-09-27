import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Parking from "./pages/Parking";
import Booking from "./pages/Booking";
import MyBookings from "./pages/MyBookings";
import Billing from "./pages/Billing";

function App() {
  return (
    <BrowserRouter>

      <Routes>

        <Route path="/" element={<Home />} />

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/parking" element={<Parking />} />

        <Route path="/booking" element={<Booking />} />

        <Route path="/my-bookings" element={<MyBookings />} />

        <Route path="/billing" element={<Billing />} />

      </Routes>

    </BrowserRouter>
  );
}

export default App;