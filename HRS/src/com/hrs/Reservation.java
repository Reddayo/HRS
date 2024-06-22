package com.hrs;

public class Reservation {
	
	/*For additional context, a reservation has a guest name,
	check-in date, check-out date, and have a link to the room’s information. The reservation should
	also have information on the total price for the booking and a breakdown of the cost per night.*/
	private String guestName;
	private int checkInDay;
	private int checkOutDay;
	private Room room;
	private String reservationID;
	Reservation (String guestName, int checkInDay, int checkOutDay, Room room){
		this.guestName = guestName;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.room = room;
		this.reservationID = String.format("%s%2d%2d", room.getRoomName().substring(room.getRoomName().length() - 2), checkInDay, checkOutDay);
		room.setReservationStatus(true);
	}
	
	
	public double getPricePerNight() {
		return room.getRoomPrice();
	}
	public String getReservationID() {
		return reservationID;
	}
	
	/**
	 * @return the guestName
	 */
	public String getGuestName() {
		return guestName;
	}
	/**
	 * @return the checkIn
	 */
	public int getCheckIn() {
		return checkInDay;
	}
	/**
	 * @return the checkOut
	 */
	public int getCheckOut() {
		return checkOutDay;
	}
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	
	public String getRoomName() {
		return room.getRoomName();
	}
}