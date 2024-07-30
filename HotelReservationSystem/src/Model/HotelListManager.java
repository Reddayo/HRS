package Model;


import java.util.ArrayList;

/**
 * <p>
 * The <code>HotelListManager</code> class manages a collection of hotels
 * within the hotel reservation system. It provides functionality to create hotels,
 * check uniqueness of hotel names, find hotels by name, remove hotels, and display
 * a list of all hotels currently managed.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class HotelListManager {

	/**
	 * The list of other hotels in the chain.
	 */
	private ArrayList<Hotel> hotels;

	/**
	 * Maximum number of rooms allowed in the hotel.
	 */
	public static final int MAX_ROOMS = 50;

	/**
	 * Default price for a room in the hotel.
	 */
	public static final double defaultPrice = 1299;

	/**
	 * Minimum price that can be set for hotels.
	 */
    public static final double minimumPrice = 100;



	 /**
     * Constructs a new <code>HotelListManager</code> instance with an empty list of hotels.
     */
	public HotelListManager(){
		hotels =  new ArrayList<Hotel>();
		/*
		hotels.add(new Hotel("Blue",1299, 1, 2, 3, "A"));
		hotels.add(new Hotel("Red", 1299, 1, 0, 1, "B"));
		hotels.add(new Hotel("Green",1299, 10, 20, 10, "C"));
		hotels.add(new Hotel("Yellow", 1299, 1, 0, 1, "D"));
		hotels.add(new Hotel("Purple",1299, 10, 20, 10, "E"));
		hotels.add(new Hotel("Orange", 1299, 1, 0, 1, "F"));
		Hotel hotel = hotels.get(5);

		hotel.addReservation("Akai Haato","Executive", 1, 31, "I_WORK_HERE" );
		hotel.addReservation("Akai Haato", "Standard", 2, 5, "STAY4_GET1");
		hotel = hotels.get(2);
		hotel.addReservation("Akai Haato", "Deluxe", 11, 21, "PAYDAY");
		hotel = hotels.get(1);
		hotel.addReservation("Suisei Hoshimati", "Executive", 11, 21, "STAY4_GET1");
		*/
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
	 
	 /**
	  * Returns the number of hotels currently in the list.
	  * 
	  * @return the size of the hotel list.
	  */
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
		 * Creates a new hotel and adds it to the list of hotels.
		 * 
		 * @param name     the name of the hotel.
		 * @param price    the base price for rooms in the hotel.
		 * @param standard the number of standard rooms in the hotel.
		 * @param deluxe   the number of deluxe rooms in the hotel.
		 * @param executive the number of executive rooms in the hotel.
		 * @throws Exception if a hotel with the same name already exists.
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
	 * Retrieves the hotel at the specified index from the list of hotels.
	 *
	 * @param i The index of the hotel in the list.
	 * @return The {@link Hotel} object at the specified index.
	 */
	public Hotel getHotel(int i) {
		return hotels.get(i);
	}
	
	/**
	 * Changes the name of a hotel from the old name to the new name, ensuring the new name is unique.
	 *
	 * @param oldName The current name of the hotel.
	 * @param newName The new name to be assigned to the hotel.
	 * 
	 * @throws IllegalArgumentException if the hotel with the old name is not found or if a hotel with the new name already exists.
	 */
	public void changeHotelName(String oldName, String newName){
		Hotel hotel = this.findHotel(oldName);

		if(hotel == null){
			throw new IllegalArgumentException("Hotel name is not unique");

		}
		
		hotel = this.findHotel(newName);

		if(hotel != null){
			throw new IllegalArgumentException("Hotel name is not unique");

		}
		hotel = this.findHotel(oldName);
		hotel.setName(newName);
	}
}