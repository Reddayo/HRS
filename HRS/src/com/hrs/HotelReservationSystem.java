package com.hrs;
//CHANGE THIS CLASS TO A HOTELINVENTORY/MANAGER OR SOMETHING ALL THE LOGIC WOULD BE AT THE MENU
//IF THERE ARE NO HOTELS THEN MAKE IT NOT CALL THE FUCKING METHOD
import java.util.ArrayList;

/**
 * <p>
 * The <code>HotelReservationSystem</code> class manages a collection of hotels
 * within a hotel reservation system. It provides functionality to create hotels,
 * check uniqueness of hotel names, find hotels by name, remove hotels, and display
 * a list of all hotels currently managed.
 * </p>
 * <p>
 * This class supports operations for managing hotels in the system, such as adding,
 * finding, and removing hotels. It also includes utility methods for generating
 * unique prefixes and displaying hotel information.
 * </p>
 */
public class HotelReservationSystem {

	//private Scanner scn = new Scanner(System.in);
	private ArrayList<Hotel> hotels;
	static final int MAX_ROOMS = 50;
	static final double defaultPrice = 1299;
	 /**
     * Constructs a new <code>HotelReservationSystem</code> instance with an empty list of hotels.
     */
	HotelReservationSystem(){
		hotels =  new ArrayList<Hotel>();
	}
	/**
     * Creates a new hotel with the specified name and number of rooms, and adds it to the system.
     * 
     * @param name The name of the hotel to create.
     * @param noOfRooms The number of rooms in the hotel.
     */
	public String prefixGenerator(int counter) {
        StringBuilder result = new StringBuilder();
        while (counter >= 0) {
            result.insert(0, (char) ('A' + counter % 26));
            counter = counter / 26 - 1;
        }
        return result.toString();
    }
	//Implement createHotel method
	public void createHotel(String name, int noOfRooms){
		
		hotels.add(new Hotel(name, noOfRooms, prefixGenerator(hotels.size())));	
	} /**
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
     * Displays a list of all hotels currently managed by the system.
     * Each hotel is listed with a bullet symbol and its name.
     */
	public void displayHotels() {
		char bulletSymbol=  149; 
		for(Hotel hotel: hotels)
		System.out.println("\t" + bulletSymbol + "  " + hotel.getName() );
	}

		
}