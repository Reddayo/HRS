package com.hrs;
/**
 * The <code>Reservation</code> class represents a reservation made by a guest
 * for a specific room in a hotel. It includes guest information, check-in and
 * check-out dates, room details, reservation ID, and pricing information.
 */
public class Reservation {
	
	
	private String guestName;
	private int checkInDay;
	private int checkOutDay;
	private Room room;
	private String reservationID;
	private double totalPrice;
	/**
     * Constructs a new <code>Reservation</code> object with the given guest name,
     * check-in day, check-out day, and room.
     *
     * @param guestName   The name of the guest making the reservation.
     * @param checkInDay  The day when the guest checks in.
     * @param checkOutDay The day when the guest checks out.
     * @param room        The room reserved for the guest.
     */
	Reservation (String guestName, int checkInDay, int checkOutDay, Room room){
		this.guestName = guestName;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.room = room;
		this.reservationID = String.format("%s%02d%02d", room.getRoomName(), checkInDay, checkOutDay);
		this.totalPrice = room.getRoomPrice() * (checkOutDay - checkInDay);
	}
	 /**
     * Retrieves the total price for the reservation based on the number of nights
     * stayed and the room's price per night.
     *
     * @return The total price for the reservation.
     */
	public double getTotalPrice() {
		return this.totalPrice;
	}
	 /**
     * Retrieves the price per night for the room reserved in this reservation.
     *
     * @return The price per night for the room.
     */
	public double getPricePerNight() {
		return room.getRoomPrice();
	}

    /**
     * Retrieves the reservation ID, which is a unique identifier for this reservation.
     *
     * @return The reservation ID.
     */
	public String getReservationID() {
		return this.reservationID;
	}
	
	/**
	 * @return the guestName
	 */
	public String getGuestName() {
		return this.guestName;
	}
	/**
	 * @return the checkIn
	 */
	public int getCheckIn() {
		return this.checkInDay;
	}
	/**
	 * @return the checkOut
	 */
	public int getCheckOut() {
		return this.checkOutDay;
	}
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return this.room;
	}
	
	/**
	 * @return the roomName
	 */
	
	public String getRoomName() {
		return this.room.getRoomName();
	}
}