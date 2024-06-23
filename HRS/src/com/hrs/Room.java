package com.hrs;


import java.util.ArrayList;
public class Room {
	private String name;
	private ArrayList<Reservation> reservation;
	private boolean reservationStatus;
	private double roomPrice;
	Room(String name, double roomPrice){
		this.name = name;
		this.reservation = new ArrayList<Reservation>();
		this.reservationStatus = false;
		this.roomPrice = 1299.0;
	}
	
	
	
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
	public double getRoomPrice() {
		return this.roomPrice;
	}
	 public String availability() {
	        StringBuilder availabilityString = new StringBuilder();
	        availabilityString.append("Availability Calendar for Room ").append(name).append(":\n");

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
	                availabilityString.append("\n");
	            }
	        }
	        availabilityString.append("XX");
	        
	        return availabilityString.toString();
	    }
	public void removeReservation(Reservation reservation) {
		this.reservation.remove(reservation);
	}
	public void addReservation(Reservation reservation) {
		this.reservation.add(reservation);
	}
	
	public void setReservationStatus(boolean reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public boolean getReservationStatus() {
		return this.reservationStatus;
	}
	
	public Reservation getReservation(int i) {
		return reservation.get(i);
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservation;
	}
	public int getReservationSize() {
		return reservation.size();
	}
	
	
	public String getRoomName() {
		return this.name;
	}
}