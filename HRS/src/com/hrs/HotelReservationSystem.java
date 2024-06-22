package com.hrs;
//CHANGE THIS CLASS TO A HOTELINVENTORY/MANAGER OR SOMETHING ALL THE LOGIC WOULD BE AT THE MENU
//IF THERE ARE NO HOTELS THEN MAKE IT NOT CALL THE FUCKING METHOD
import java.util.ArrayList;
public class HotelReservationSystem {

	//private Scanner scn = new Scanner(System.in);
	private ArrayList<Hotel> hotels =  new ArrayList<Hotel>();
	static final int MAX_ROOMS = 50;

	//Implement createHotel method
	public void createHotel(String name, int noOfRooms){
		
		hotels.add(new Hotel(name, noOfRooms, prefixGenerator(hotels.size())));	
	}
	public boolean isEmpty() {
		return hotels.isEmpty();
	}
	
	public String prefixGenerator(int counter) {
        StringBuilder result = new StringBuilder();
        while (counter >= 0) {
            result.insert(0, (char) ('A' + counter % 26));
            counter = counter / 26 - 1;
        }
        return result.toString();
    }
	
	public boolean isHotelNameUnique (String name) {
		for (Hotel hotel : hotels) {
	        if (hotel.getName().equals(name)) {
	           
	            return false;
	        }
	    }
		return true;
	}
	
	public Hotel findHotel(String name) {
		for(Hotel hotel: hotels) {
			if(hotel.getName().equals(name)) {
				return hotel;
			}
		}
		return null;
    }
	
	public void remove(Hotel hotel) {
		hotels.remove(hotel);
	}
	//implement ViewHotel method 
	
	//technically bad rn
	public void ViewHotel(String hotelName) {
		System.out.println("What do you want to see!");
		displayHotels();
		//add a menu here before displaying hotels
		/*
		 * 2. View Hotel
		 * 
		 * > display all the hotels
		 * > ask for a hotel name
		 * Total number of available and booked rooms for a selected date
		 * > immediately print high level info
		 * 
		 * 1. Hotel Information
		 * 2. Room Information
		 * 3. Reservation Information
		 * 
		 * 
		 * 
		 */
		//add a scanner here, find a hotel
		Hotel tempHotel = findHotel(hotelName);
		if(tempHotel != null) {
			System.out.println("Hotel Name: " + tempHotel.getName());
			System.out.println("Total No. of Rooms: " + tempHotel.getNoOfRooms());
			System.out.println("Estimate Earnings for the month: " + tempHotel.getEstimate());
			/* 
			 * Total number of available and booked rooms for a selected date
				ii. Information about a selected room, such as the room’s name, price per night, and
				availability across the entire month
				iii. Information about a selected reservation, such as the guest information, room
				information, check-in and -out dates, the total price for the booking, and the
				breakdown of the price per night
			 */
			System.out.println("");
			// some scanner thing if (string.equals(yes)){
			
			
			System.out.println("");
				for(Room room : tempHotel.getRooms())
					System.out.println(room.getRoomName());
			//}	
			
		}
		
		/*
		switch() {
		case HIGH_LEVEL:;
		case LOW_LEVEL:;
		}*/
	}
	
	
	public void displayHotels() {
		char bulletSymbol=  '\u2023'; 
		for(Hotel hotel: hotels)
		System.out.println(bulletSymbol + "  " + hotel.getName() );
	}
	
	
	
	//implement ManageHotel method

	
	public void changeHotelName(String hoteltobechanged, String name) {
		Hotel hotel = findHotel(hoteltobechanged);
		if(hoteltobechanged.equals(name)) {
			System.out.println("New name is the same as the hotel to be changed");
			return;
		}
		
		if(!isHotelNameUnique(name)) {
			System.out.println("Name is not unique");
			return;
		}
		
		if(hotel == null) {
			return;
		}
		
		
		hotel.setName(name);
	}
	
	
	
	//implement simulateBooking method
	
	

}