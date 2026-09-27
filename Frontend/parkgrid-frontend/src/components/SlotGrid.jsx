function SlotGrid({ slots, onSelect }) {

  return (
    <div className="slot-grid">

      {slots.map((slot) => (

        <button
          key={slot.id}
          className={`slot ${slot.status.toLowerCase()}`}
          disabled={slot.status !== "AVAILABLE"}
          onClick={() => onSelect(slot)}
        >

          <span>🚗</span>

          <strong>{slot.slotNumber}</strong>

          <small>{slot.status}</small>

        </button>

      ))}

    </div>
  );
}

export default SlotGrid;