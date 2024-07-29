package Model;


/**
 * <p>
 * The <code>Reservation</code> class represents a reservation made by a guest
 * for a specific room in a hotel. It includes guest information, check-in and
 * check-out dates, room details, reservation ID, and pricing information.
 * </p>
 *
 * @author Jusper Angelo Cesar
 * @version 4.4
 *
 */
public class Reservation {
	
	
	/**
	 * Name of the guest associated with the reservation.
	 */
	private String guestName;

	/**
	 * Day of the month when the guest checks in.
	 */
	private int checkInDay;

	/**
	 * Day of the month when the guest checks out.
	 */
	private int checkOutDay;

	/**
	 * Room assigned to the guest for the reservation.
	 */
	private Room room;

	/**
	 * Unique identifier for the reservation.
	 */
	private String reservationID;

	private String discount;
	private double[] prices;
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
	Reservation (String guestName, int checkInDay, int checkOutDay, Room room, String discount, double[]priceMods){
		this.guestName = guestName;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.room = room;
		this.reservationID = String.format("%s%02d%02d", room.getRoomName(), checkInDay, checkOutDay);
		this.prices = new double[ checkOutDay -  checkInDay];
		this.discount = discount;
		recalculatePrice(priceMods);
	}
	 /**
     * Retrieves the total price for the reservation based on the number of nights
     * stayed and the room's price per night.
     *
     * @return The total price for the reservation.
     */
	public double getTotalPrice() {
		System.out.println(totalPrice + "Total Price");
		return totalPrice;
		
	}

	/**
	 * Calculates and returns the duration of the reservation in days.
	 *
	 * @return The number of days between the check-in day and the check-out day.
	 */
	public int getDuration(){
		return (checkOutDay - checkInDay);
	}

	/**
	 * Retrieves the discount code applied to the reservation.
	 *
	 * @return The discount code as a string.
	 */
	public String getDiscount() {
		return discount;
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
	
	/**
	 * Retrieves the price for a specific day of the reservation.
	 *
	 * @param checkIn The check-in day for which to retrieve the price.
	 * @return The price for the specified day.
	 */
	public double getPriceForDay(int checkIn) {
		return prices[checkIn-1];
	}
	
	/**
	 * Retrieves the detailed breakdown of prices for each day of the reservation.
	 *
	 * @return An array of doubles representing the price for each day of the reservation.
	 */
	public double[] getBreakdown() {
		return prices;
	}
	
	/**
	 * Recalculates the total price of the reservation based on the provided price modifiers and
	 * any applicable discount code. Updates the daily prices and the total price accordingly.
	 *
	 * @param priceMods An array of price modifiers to adjust the base rate for each day.
	 */
	public void recalculatePrice(double[] priceMods) {
		totalPrice = 0;
		for(int i = checkInDay - 1, j = 0; i < checkOutDay - 1; i++, j++) {
			
			 if (i >= 0 && i < priceMods.length) {
			        prices[j] = room.getRoomBaseRate() * priceMods[i];
		    } else {
		         prices[j] = room.getRoomBaseRate(); 
		    }

		}
		
		switch(discount) {
			case "I_WORK_HERE": 
				for (int i1 = 0; i1 < prices.length; i1++) {
	                prices[i1] *= (1 - 0.10); 
	            }
				break;
			case "STAY4_GET1":
				if(getDuration() >= 5) {
					prices[0] = 0;
				}
				break;
			case "PAYDAY":
				if ((checkInDay <= 15 && checkOutDay > 15) ||
					(checkInDay <= 30 && checkOutDay > 30 )) 
				   {
					for (int i1 = 0; i1 < prices.length; i1++) {
		                prices[i1] *= (1 - 0.07); 
		            }
				   }
				break;
		}
		
		totalPrice = 0;
		
		for(int i1 = 0; i1 < prices.length; i1++) {
			totalPrice += prices[i1]; 
		}
		
		
	}
	
}