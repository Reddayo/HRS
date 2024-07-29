package Model;




import java.util.ArrayList;

/**
 * <p>
 * The <code>Room</code> class represents a room in a hotel, containing details
 * about its name, reservations, availability status, room price, and methods
 * to manage reservations.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public abstract class Room {
	/**
	 * The name associated with this entity.
	 */
	private String name;

	/**
	 * List of reservations associated with this entity.
	 */
	private ArrayList<Reservation> reservations;

	/**
	 * The price per room for this entity.
	 */
	private double roomPrice; //THIS NEEDS TO SAY BASE PRICE 



	
	/**
     * Constructs a new <code>Room</code> object with the given name and room price.
     *
     * @param name      The name or identifier of the room.
     * @param roomPrice The price per night for the room.
     */
	Room(String name, double roomPrice){
		this.name = name;
		this.reservations = new ArrayList<Reservation>();
		this.roomPrice = roomPrice;
	}
	
	
	/**
     * Removes a reservation from the room.
     *
     * @param reservation The reservation to be removed.
     */
	 public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	
	}


	/**
     * Adds a new reservation to the room.
     *
     * @param reservation The reservation to be added.
     */
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
		
	}

   /**
	* Checks if the room is available for booking during the specified period.
	*
	* @param checkInDay  The day of check-in.
	* @param checkOutDay The day of check-out.
	* @return <code>true</code> if the room is available, <code>false</code> otherwise.
	*/
	public boolean isAvailable(int checkInDay, int checkOutDay) {
	        for (Reservation re : reservations) {
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
	 * Generates an array representing the room's availability for each day of the month.
	 *
	 * @return A boolean array where each element indicates the availability of the room for a specific day.
	 *         The array has 31 elements, corresponding to days 1 through 31.
	 *         A value of <code>true</code> means the room is available on that day, and <code>false</code>
	 *         means the room is booked.
	 */
	public boolean[] getAvailability() {
		boolean[] availabilityArray = new boolean[31]; 
		for (int day = 0; day < 31; day++) {
			boolean available = true;
			for (Reservation reservation : reservations) {
				int checkInDay = reservation.getCheckIn();
				int checkOutDay = reservation.getCheckOut();
	
				if ((day+1) >= checkInDay && (day+1) < checkOutDay) {
					available = false;
					break;
				}
			}
	
			availabilityArray[day] = available; 
		}
	
		return availabilityArray;
	}
	
	
   /**
    * Retrieves the price per night for the room.
    *
    * @return The price per night for the room.
    */
	public double getRoomBaseRate() {
		return this.roomPrice;
	}

	/**
	 * Sets the price per night for the room.
	 *
	 * @param roomPrice the room price to set to.
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	/**
	 * Retrieves the reservation at the specified index from the list of reservations.
	 *
	 * @param i The index of the reservation in the list.
	 * @return The {@link Reservation} object at the specified index.
	 */
	public Reservation getReservation(int i) {
		
		return reservations.get(i);
	}
	
	/**
	 * Checks if the room is currently booked.
	 *
	 * @return <code>true</code> if there are one or more reservations for the room, 
	 *         indicating it is booked; <code>false</code> otherwise.
	 */
	public boolean isBooked() {
		return reservations.size() != 0;
	}
	
	/**
     * Retrieves the number of reservations associated with the room.
     *
     * @return The number of reservations associated with the room.
     */
	public int getReservationSize() {
		return reservations.size();
	}
	
	/**
     * Retrieves the name or identifier of the room.
     *
     * @return The name or identifier of the room.
     */
	public String getRoomName() {
		return this.name;
	}
	
	/**
	 * Returns the type of the room as a string. This is an abstract method
	 */
	public abstract String getRoomType();
	

}
