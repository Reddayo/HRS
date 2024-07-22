package Model;


/**
 * The <code>Reservation</code> class represents a reservation made by a guest
 * for a specific room in a hotel. It includes guest information, check-in and
 * check-out dates, room details, reservation ID, and pricing information.
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


	public int getDuration(){
		return (checkOutDay - checkInDay);
	}

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
	public double getPriceForDay(int checkIn) {
		return prices[checkIn-1];
	}
	
	public double[] getBreakdown() {
		return prices;
	}
	
	public void recalculatePrice(double[] priceMods) {
		totalPrice = 0;
		for(int i = checkInDay - 1, j = 0; i < checkOutDay - 1; i++, j++) {
			
			 if (i >= 0 && i < priceMods.length) {
			        prices[j] = room.getRoomBaseRate() * priceMods[i];
		    } else {
		         prices[j] = 1.0; 
		    }
		}
		
		
		for(int i = 0; i < prices.length; i++) {
			totalPrice += prices[i]; 
		}
		
		if(discount.equals("I_WORK_HERE")) {
		    totalPrice *= (1 - 0.10);
		}else if(discount.equals("STAY4_GET1")) {
			if(getDuration() >= 5) {
				prices[0] = 0;
				totalPrice = 0;
				for(int i = 0; i < prices.length; i++) {
					totalPrice += prices[i]; 
				}
				
				
			}
		}else if(discount.equals("PAYDAY")) {
		   if ((checkInDay <= 15 && checkOutDay > 15) ||
		       (checkInDay <= 30 && checkOutDay > 30 )) 
		   {
		     totalPrice *= (1 - 0.07);
		  }
		}
		
		
		
	}
	
}