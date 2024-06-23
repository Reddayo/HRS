package com.hrs;


import java.util.ArrayList;

/**
 * The <code>Room</code> class represents a room in a hotel, containing details
 * about its name, reservations, availability status, room price, and methods
 * to manage reservations.
 */
public class Room {
	private String name;
	private ArrayList<Reservation> reservation;
	private boolean reservationStatus;
	private double roomPrice;
	
	/**
     * Constructs a new <code>Room</code> object with the given name and room price.
     *
     * @param name      The name or identifier of the room.
     * @param roomPrice The price per night for the room.
     */
	Room(String name, double roomPrice){
		this.name = name;
		this.reservation = new ArrayList<Reservation>();
		this.reservationStatus = false;
		this.roomPrice = 1299.0;
	}
	
	
	/**
	* Checks if the room is available for booking during the specified period.
	*
	* @param checkInDay  The day of check-in.
	* @param checkOutDay The day of check-out.
	* @return <code>true</code> if the room is available, <code>false</code> otherwise.
	*/
	public boolean isAvailable(int checkInDay, int checkOutDay) {
	        for (Reservation re : reservation) {
	            int reservedCheckIn = re.getCheckIn();
	            int reservedCheckOut = re.getCheckOut();
	            
	            // Check if there is an overlap
	            if ((checkInDay < reservedCheckOut) && (checkOutDay > reservedCheckIn)) {
	                return false;
	            }
	        }
	        return true;
	    }
	/**
     * Retrieves the price per night for the room.
     *
     * @return The price per night for the room.
     */
	public double getRoomPrice() {
		return this.roomPrice;
	}
	/**
     * Generates a textual representation of the room's availability for the month.
     *
     * @return A string representing the availability of the room.
     */
	 public String availability() {
	        StringBuilder availabilityString = new StringBuilder();
	        availabilityString.append("\tAvailability Calendar for Room ").append(name).append(":\n\t");

	        // Loop through days 1 to 31
	        for (int day = 1; day <= 30; day++) {
	            boolean available = true;
	            
	            // Check each reservation
	            for (Reservation reservation : reservation) {
	                int checkInDay = reservation.getCheckIn();
	                int checkOutDay = reservation.getCheckOut();
	                
	                // Mark days between check-in and day before check-out as unavailable
	                if (day >= checkInDay && day < checkOutDay) {
	                    available = false;
	                    break;
	                }
	            }
	            
	            // Append day to availability string based on availability
	            if (available) {
	                availabilityString.append(String.format("%2d", day)).append(" ");
	            } else {
	                availabilityString.append("-- "); // Mark as unavailable
	            }
	            
	            // Add newline after every 7 days for readability
	            if (day % 7 == 0) {
	                availabilityString.append("\n\t");
	            }
	        }
	        availabilityString.append("XX");
	        
	        return availabilityString.toString();
	    }
	
	 /**
	     * Removes a reservation from the room.
	     *
	     * @param reservation The reservation to be removed.
	     */
	 public void removeReservation(Reservation reservation) {
		this.reservation.remove(reservation);
	}
	 /**
	     * Adds a new reservation to the room.
	     *
	     * @param reservation The reservation to be added.
	     */
	public void addReservation(Reservation reservation) {
		this.reservation.add(reservation);
	}
	/**
     * Sets the reservation status of the room.
     *
     * @param reservationStatus The new reservation status.
     */
	public void setReservationStatus(boolean reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	/**
     * Retrieves the reservation status of the room.
     *
     * @return The reservation status of the room.
     */
	public boolean getReservationStatus() {
		return this.reservationStatus;
	}
	  /**
     * Retrieves a specific reservation from the room based on its index.
     *
     * @param i The index of the reservation.
     * @return The reservation at the specified index.
     */
	public Reservation getReservation(int i) {
		return reservation.get(i);
	}
	 /**
     * Retrieves all reservations associated with the room.
     *
     * @return The list of reservations associated with the room.
     */
	public ArrayList<Reservation> getReservations() {
		return reservation;
	}
	
	/**
     * Retrieves the number of reservations associated with the room.
     *
     * @return The number of reservations associated with the room.
     */
	public int getReservationSize() {
		return reservation.size();
	}
	
	/**
     * Retrieves the name or identifier of the room.
     *
     * @return The name or identifier of the room.
     */
	public String getRoomName() {
		return this.name;
	}
}