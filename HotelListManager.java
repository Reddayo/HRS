package Model;

import java.util.ArrayList;

/**
 * <p>
 * The <code>HotelListManager</code> class manages a collection of hotels
 * within the hotel reservation system. It provides functionality to create hotels,
 * check uniqueness of hotel names, find hotels by name, remove hotels, and display
 * a list of all hotels currently managed.
 * </p>
 */
public class HotelListManager {

	/**
	 * The list of other hotels in the chain.
	 */
	private ArrayList<Hotel> hotels;

	/**
	 * Maximum number of rooms allowed in the hotel.
	 */
	static final int MAX_ROOMS = 50;

	/**
	 * Default price for a room in the hotel.
	 */
	static final double defaultPrice = 1299;

	 /**
     * Constructs a new <code>HotelListManager</code> instance with an empty list of hotels.
     */
	public HotelListManager(){
		hotels =  new ArrayList<Hotel>();
		hotels.add(new Hotel("Blue",1299, 10, 20, 10, "A"));
		hotels.add(new Hotel("Red", 1299, 1, 0, 1, "B"));
	}
	/**
	 * Generates the next prefix for the room in the list of hotel.
	 *
	 * @return the prefix of the hotel room.
	 */
	 private String prefixGenerator() {
	        int counter = 0;
	        StringBuilder result = new StringBuilder();
	        while (true) {
	            result.setLength(0); 
	            int tempCounter = counter;
	            while (tempCounter >= 0) {
	                result.insert(0, (char) ('A' + tempCounter % 26));
	                tempCounter = tempCounter / 26 - 1;
	            }
	            
	       
	            boolean isUnique = true;
	            for (Hotel hotel : hotels) {
	                if (hotel.getRoomPrefix().equals(result.toString())) {
	                    isUnique = false;
	                    break;
	                }
	            }
	            
	            if (isUnique) {
	                return result.toString();
	            }
	            counter++;
	        }
	    }
	 
	 
	 public int getHotelListSize() {
		 return hotels.size();
	 }

	/**
     * Checks if hotel is part of list
     * 
	 * @param hotel the hotel to look for
	 *
	 * @return if hotel is part of the list
     */

		public boolean contains(Hotel hotel){
			return hotels.contains(hotel);
		}
	/**
     * Creates a new hotel and add it to the list
     * 
	 * @param name the name of the room
	 * @param noOfRooms the number of rooms
	 * @throws Exception 
     */
	public void createHotel(String name,double price,  int standard, int deluxe, int executive) throws Exception{
		if(isHotelNameUnique(name)) {
		hotels.add(new Hotel(name, price, standard, deluxe, executive, prefixGenerator()));	
		}else { 
			throw new Exception("This hotel already exists");
		}
	}
	/**
     * Checks if the list of hotels is empty.
     * 
     * @return <code>true</code> if there are no hotels in the system, <code>false</code> otherwise.
     */
	public boolean isEmpty() {
		return hotels.isEmpty();
	}
	
	 /**
     * Checks if a hotel name is unique among all existing hotels in the system.
     * 
     * @param name The name of the hotel to check for uniqueness.
     * @return <code>true</code> if the hotel name is unique, <code>false</code> otherwise.
     */
	public boolean isHotelNameUnique (String name) {
		for (Hotel hotel : hotels) {
	        if (hotel.getName().equals(name)) {
	           
	            return false;
	        }
	    }
		return true;
	}
	/**
     * Finds a hotel by its name.
     * 
     * @param name The name of the hotel to find.
     * @return The <code>Hotel</code> object if found, or <code>null</code> if not found.
     */
	public Hotel findHotel(String name) {
		for(Hotel hotel: hotels) {
			if(hotel.getName().equals(name)) {
				return hotel;
			}
		}
		return null;
    }
	
	
	 /**
     * Removes a specified hotel from the system.
     * 
     * @param hotel The <code>Hotel</code> object to remove from the system.
     */
	public void remove(Hotel hotel) {
		hotels.remove(hotel);
	}

	/**
	 * Returns a list of all hotels currently managed by the system.
	 * Each hotel is listed with a bullet symbol and its name.
	 * 
	 * @return the list of hotels in text form.
	 */
	public String hotelsText() {
	   
	    StringBuilder output = new StringBuilder();
	    for (Hotel hotel : hotels) {
	        output.append(" -  ").append(hotel.getName()).append("\n");
	    }
	    return output.toString();
	}

	/**
	 * Returns a list of available rooms in a selected hotel for booking.
	 * 
	 * @param hotel The <code>Hotel</code> object to check for rooms for.
	 * 
	 * @return the list of text in hotel in text form.
	 */
	public String roomsOfHotelText(Hotel hotel) {
	    StringBuilder output = new StringBuilder("\n\n");
		for(int i = 0; i < hotel.getRoomSize(); i++) {
	        output.append(" -  ").append(hotel.getRoom(i).getRoomName()).append("\n");
	    }
	    return output.toString();
	}

	/**
	 * Returns a list of reservations in a selected hotel.
	 * 
	 * @param hotel The <code>Hotel</code> object to check for reservations.
	 * 
	 * @return the list of reservations in hotel in text form
	 */
	public String reservationsOfHotelText(Hotel hotel) {
	    StringBuilder output = new StringBuilder();
	    for (int i = 0; i < hotel.getReservationSize(); i++) {
	        output.append(" -  ").append(hotel.getReservation(i).getReservationID()).append("\n");
	    }
	    return output.toString();
	}
	public Hotel getHotel(int i) {
		// TODO Auto-generated method stub
		return hotels.get(i);
	}

}