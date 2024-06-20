package com.hrs;


import java.util.Scanner;
public class Menu {

	private final Scanner scn;
	private HotelReservationSystem HRS;
	Menu(Scanner scn){
		this.scn = scn;
		this.HRS = new HotelReservationSystem();
	}
	
	
	public void start () {
		boolean stop = false;
		int chosenOption = 0;
		while(!stop) {
			
			displayMainMenu();
			
			chosenOption = getValidNumberInRange(1, 5, false);
			MainMenuOptions selectedOption = MainMenuOptions.values()[chosenOption - 1];
			
			switch(selectedOption) {
				case CREATE_HOTEL:		displayCreateHotelMenu(); break;
				case VIEW_HOTEL:		displayViewHotelMenu(); break;
				case MANAGE_HOTEL:		displayManageHotelMenu(); break;
				case SIMULATE_BOOKING:	displaySimulateBookingMenu(); break;
				case EXIT: 				System.out.println("Exitting program..."); stop = true; break;
				default: break;
			}

		}
		
	}
	
	private void displayMainMenu() {
		System.out.println("\t\tMain Menu\n");
		for (MainMenuOptions option : MainMenuOptions.values()) {
            System.out.println("\t" + (option.ordinal() + 1) + ". " + option.getDescription());
        }
		System.out.print(">> ");
	}
	
	private void displayCreateHotelMenu() {
		
		String newHotelName;
		int newNoOfRooms;
		System.out.println("Create Hotel\n");
		System.out.println("Press enter to exit anytime");
		do {
			do {
				System.out.print("Hotel name: ");
				
				newHotelName = scn.nextLine();
				if(!HRS.isHotelNameUnique(newHotelName))
					System.out.println("\n" + newHotelName + " is not unique.\n");
				
			}while(!HRS.isHotelNameUnique(newHotelName));
			
			if(newHotelName.isBlank())
				return;
			
			System.out.println("No. of rooms: ");
			
			newNoOfRooms = getValidNumberInRange(1, 50, true);
			
			if(newNoOfRooms == 0)
				return;
			
			HRS.createHotel(newHotelName, newNoOfRooms);

			System.out.println(newHotelName + " with " + newNoOfRooms + " has been created!");
		}while(confirmation("Do you want to create another hotel?"));
		//System.out.println("3. Price");
		//System.out.println("4. Exit");
		//this creates one instance of hotel OR NADA
		
		
		//Name must be given
		//then a hotel would be made with a minimum of 1 room
		// then a hotel would be made with  minimum of base price  which is 1299.0
	}
	
	
	private void displayViewHotelMenu() {
		boolean stop = false;
		Hotel foundHotel;
		String hotelToFind;
		do {
			System.out.println("View Hotel");
			do {
				HRS.displayHotels();
				//System.out.println("**displays all the hotel**");
				System.out.println("Select a hotel to view");
				System.out.println(">> ");
				hotelToFind = scn.nextLine();
				if(hotelToFind.isBlank())
					return;
				
				foundHotel = HRS.findHotel(hotelToFind);
				if(foundHotel == null)
					System.out.println("Hotel not found. Enter a valid hotel or press enter to go to previous menu.");
				
				
;
			}while(foundHotel == null);
			
			do {
				printViewHotelMenu();
				int chosen  = getValidNumberInRange(1, viewHotelMenu.values().length, false);
				viewHotelMenu vhm = viewHotelMenu.values()[chosen - 1];
				
				switch (vhm) {
				case OVERVIEW: displayOverviewInfo(foundHotel); break;
				case DETAILED: detailedViewHotelMenu(foundHotel); break;
				case EXITV: stop = true; break;
				}
		
			}while(!stop);
			
		}while(confirmation("Do you want to view another hotel?"));
		//immediately display the high level information DONT BAD IDEA
		/* High-level hotel information should include the name of the hotel, total number of rooms,
			estimate earnings for the month (i.e. sum of total price across all reservations) */
		
		/* overview info */
		
		
		/* detailed information */
		/*
		Total number of available and booked rooms for a selected date
		ii. Information about a selected room, such as the room’s name, price per night, and
		availability across the entire month
		iii. Information about a selected reservation, such as the guest information, room
		information, check-in and -out dates, the total price for the booking, and the
		breakdown of the price per night
		 */
		//System.out.println("1. No. of Available & Booked Rooms for a Selected Date");
		/* ask for date */
		//System.out.println("2. Room information"); 
		/* ask for room name then show*/
		/* such as the room’s name, price per night, and
		availability across the entire month */
		//System.out.println("3. Reservation information"); 
		/* display all the reservations for the hotel, reservation ID+Date */
		/*
		 * such as the guest information, room information, 
		 * check-in and -out dates, the total price for the booking, 
		 * and the breakdown of the price per night
		 * Continue to ask until go no
		*/
		//System.out.println("4. Go back to View Hotel"); 
	}
	
	private void printViewHotelMenu() {
		for(viewHotelMenu vhm : viewHotelMenu.values()) {
			System.out.println(vhm.ordinal() + 1 + vhm.getDescription());
		}
	}
	
	private void printDetailedViewHotelMenu() {
		for(DetailedViewHotelMenu dvhm : DetailedViewHotelMenu.values()) {
			System.out.println(dvhm.ordinal() + 1 + dvhm.getDescription());
		}
	}
	
	private void detailedViewHotelMenu(Hotel foundHotel) {
		
		boolean stop = false;
		do {
			printDetailedViewHotelMenu();
			int chosenOption = getValidNumberInRange(1, DetailedViewHotelMenu.values().length, false);
			DetailedViewHotelMenu dvhm = DetailedViewHotelMenu.values()[chosenOption - 1]; 
			switch (dvhm) {
			/* this needs date */
			case AVAILABILITY_DATE: 
			/* this needs room name */
			case ROOM_INFO: roomInfo(foundHotel); break;
			/* i guess a reservation id  roomPrefix + roomNo.*/ 
			case RESERVATION_INFO:
			case GOBACKTOVH: stop = true; break;
			}
			
		}while(!stop);
		
	}
	
	
	public void displayRoomsOfHotel(Hotel hotel) {
		for(Room room: hotel.getRooms())
			System.out.println(room.getRoomName());
	}
	
	public Room findRoom(String roomToFind,Hotel hotel) {
		for(Room room: hotel.getRooms())
			if(roomToFind.equals(room.getRoomName())) {
				return room;
			}
		return null;
	}
	
	public void availabilityDate() {
		
	}
	
	public void roomInfo(Hotel hotel) {
		Room room;
		do {
			do {
				displayRoomsOfHotel(hotel);
				String someString = scn.nextLine();
				if(someString.isBlank())
					return;
				room = findRoom(someString, hotel);
			}while(room == null);
			System.out.println("Room name: " + room.getRoomName());
			System.out.println("Room price: " + hotel.getPrice());
			System.out.println("Room availability: " + room.availability());
			
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
		}while(confirmation("Do you want to check other rooms?"));
	}
	public void reservationInfo() {
		
	}
	
	private void displayOverviewInfo(Hotel foundHotel) {
					
		System.out.println("Hotel Name: " + foundHotel.getName());
		System.out.println("Total number of rooms: " + foundHotel.getNoOfRooms());
		System.out.println("Estimate earnings: " + foundHotel.getEstimate());
	}
	
	
	private void displayManageHotelMenu() {

		System.out.println("Manage Hotel");
		System.out.println("1. Change hotel name");
		/* ask for the hotel that will be changed */
		/* then continually ask the user for an actual name 
		 * until it gets changed or user enters nothing
		 */
		System.out.println("2. Add room(s)");
		/* add rooms continually ask for hotel until then ask how many rooms should be added */
		System.out.println("3. Remove room(s)");
		/* remove rooms continually ask for hotel until then ask what rooms should be removed
		 * make sure that the rooms doesn't have a reservation
		 */
		
		System.out.println("4. Change room price");
		/* price must be >= 100, must have no reservations, (this is why it's better to just put in hotel*/
		System.out.println("5. Remove reservation");
		/* make a reservation id somehow and would be Room+CDate+CDate */
		// which would be shown
		
		System.out.println("6. Remove hotel");
		//ok from the looks of it i can remove a hotel even if there is a reservation cause hotel
		
		
		System.out.println("7. Exit");
		
		/* However, users must be prompted to confirm a modification or else the modification would be discarded.*/
	}
		
	
	
	private void displaySimulateBookingMenu() {
		 /* No reservations can be made when the check-out is on the 1st of the month 
		  * or when the check-in is  on the 31st of the month. 
		  * Bookings cannot be made outside of the defined period for the month.
		  */
		Hotel hotelToBook;
		do{
		/* Display the hotel list */
			System.out.println("Hotel List");
			HRS.displayHotels();
		/* ask them for a hotel */ //MAKE THIS INTO A GODDAMN METHOD
			String guestName = scn.nextLine();
			if(guestName.isBlank()) {
				return;
			}
			do {
				System.out.println("Hotel name: ");
				String someString = scn.nextLine();
				if(someString.isBlank())
					return;
				hotelToBook = HRS.findHotel(someString);
			}while(hotelToBook == null);
			
			System.out.println("Check in: "); 
			int checkIn = getValidNumberInRange(1, 30, true);
			if(checkIn == 0) {
				return;
			}
			System.out.println("Check out: ");
			int checkOut = getValidNumberInRange(2, 31, true);
			if(checkOut == 0) {
				return;
			}
			
		/* ask for checkIn checkOut dates */
		/* Bookings cannot be made outside of defined period */
			
			hotelToBook.addReservation(new Reservation(guestName, checkIn, checkOut, hotelToBook.getRoom(0)));
		/* SELECTING FOR ROOM IS GONNA BE MANUAL YOU FUCKS ARE GONNA PAY */
		
		}while(confirmation("Do you want to make another booking"));
		/* ask if they want to make a booking again, which displays hotel list again */
	}
	
	
	
	
	
	
	
	private boolean confirmation(String someString) {
		
		while(true) {
			System.out.println(someString);
			
			String answer = scn.nextLine();
	
			switch (answer) {
				case "Y": return true;
				case "N": return false;
				default: System.out.println("Invalid input. Enter Y or N\n\n"); break;
			}
		}
	}

	
	private int getValidIntInput() {
		int someNumber = 0;
		boolean validInput = false;
		do {
			try {
				someNumber = Integer.parseInt(scn.nextLine());
				validInput = true;
			}catch(NumberFormatException e){
				System.out.println("Invalid input. integer needed");
			}
		}while(!validInput);
		return someNumber;
	}
	
	private int getValidNumberInRange(int min, int max, boolean checkForZero) {
		int someNumber = 0;
		boolean isInRange;
		do {
			someNumber = getValidIntInput();
			isInRange = (someNumber >= min && someNumber <= max) || (someNumber == 0 && checkForZero);
			if(!isInRange) {
				System.out.println("Invalid input. number is not in range. \n");
				if(checkForZero) {
					System.out.println("Enter 0 to exit.");
				}
			}
			
		}while(!isInRange);
		return someNumber;
	}
	
	
	
}
