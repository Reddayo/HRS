package com.hrs;


import java.util.Scanner;

/**
 * <p>
 * The <code>Menu</code> class manages user interaction for a Hotel Reservation System (HRS).
 * It provides a console-based menu system to create hotels, view hotel details,
 * manage hotels (including rooms and reservations), simulate bookings, and exit
 * the program.
 * </p>
 */
public class Menu {

	private final Scanner scn;
	private final HotelReservationSystem HRS;
	private final InputHandler IH;
		
	 /**
     * Constructs a new <code>Menu</code> instance.
     * 
     * @param HRS The <code>HotelReservationSystem</code> instance to interact with.
     * @param scn The <code>Scanner</code> instance for user input.
     */
	Menu (HotelReservationSystem HRS, Scanner scn){
		this.HRS = HRS;
		this.scn = scn;
		this.IH = new InputHandler(scn);
	}
	
	
	 /**
     * Starts the menu loop, allowing users to interact with the Hotel Reservation System.
     * The menu continues to display until the user chooses to exit.
     */
	public void start () {
		boolean stop = false;
		int chosenOption = 0;
		final String[] MENU_OPTIONS = new String[] {"Create Hotel", 
													"View Hotel", 
													"Manage Hotel", 
													"Simulate Booking",
													"Exit"};
		while(!stop) {
			ascii.otherMenu();
			displayOptions(MENU_OPTIONS);
			
			chosenOption = IH.getValidIntegerInRange(1, MENU_OPTIONS.length, "\t>> ");
			
			switch(chosenOption) {
				case 1:	createHotel(); break; 
				case 2:	ViewHotel(); break;   
				case 3:	ManageHotelMenu(); break; 
				case 4:	displaySimulateBookingMenu(); break; 
				case 5: System.out.println("Exitting program..."); stop = true; break;
				default: break;
			}
		}
		
		ascii.animegirlheart();
		
	}
	 /**
     * Displays the menu options to the user.
     * 
     * @param menuOptions An array of Strings representing the menu options to display.
     */
	private void displayOptions(String[] someStringsToDisplay) {
		System.out.println("\n\n");
		int i = 0;
		for (String someString: someStringsToDisplay) {
            System.out.println("\t[" + ++i + "]  " + someString);
        }
	}
	/**
     * Handles the process of creating a new hotel.
     * Prompts the user for hotel name and number of rooms, and adds the hotel to the system.
     */
	private void createHotel() {
		
		String newHotelName;
		int newNoOfRooms;
		while(true) {
			System.out.println("\n\n\t\tCreate Hotel\n\n");
			System.out.println("\tPress enter to abort.");
	
			newHotelName = IH.getUniqueHotelName(HRS, "\n\tHotel name: ");
			
			if(newHotelName == null)
				return;
			
			newNoOfRooms = IH.getValidIntegerInRange(1, HotelReservationSystem.MAX_ROOMS, "\n\tNo. of rooms: " );
			
			if(newNoOfRooms == -1)
				return;
			
			HRS.createHotel(newHotelName, newNoOfRooms);

			System.out.println("\n\n\t" + newHotelName + " with " + newNoOfRooms + " rooms has been created!");
			if(!IH.confirmation("\n\tDo you want to create another hotel?"))
				return;
		}
	}
	/**
     * Displays an overview of a selected hotel's information.
     */
	private void ViewHotel() {
		
		if(HRS.isEmpty()) {
			System.out.println("\tThere are no hotels to view.");
			System.out.println("\tPress enter to continue.");
			scn.nextLine();
			return;
		}
		final String[] VIEWHOTEL_OPTS = new String[] {"Overview Information", 
													"Detailed Information", 
													"View another hotel", 
													"Exit"};
		while(true) {
			boolean stop = false;
			System.out.println("\t\tView Hotel");
			Hotel foundHotel = selectHotel();
			if(foundHotel == null) {
				return;
			}
			
			do {
				displayOptions(VIEWHOTEL_OPTS);
				int chosen  = IH.getValidIntegerInRange(1, VIEWHOTEL_OPTS.length, "\t>> ");
				
				if(chosen == -1) {
					return;
				}
				switch (chosen) {
					case 1: displayOverviewInfo(foundHotel); break;
					case 2: detailedViewHotelMenu(foundHotel); break;
					case 3: System.out.println("\tViewing another hotel."); stop = true; break;
					case 4:	return;
				}
				
		
			}while(!stop);
			
		}
	}
	 /**
     * Displays an overview of information for a specific hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object for which to display overview information.
     */
	private void displayOverviewInfo(Hotel foundHotel) {
		
		System.out.println("\tHotel Name: " + foundHotel.getName());
		System.out.println("\tTotal number of rooms: " + foundHotel.getNoOfRooms());
		System.out.println("\tEstimate earnings: " + foundHotel.getEstimate());
		System.out.print("\n\n\tPress enter to continue.");
		scn.nextLine();
	}
	/**
     * Displays detailed information about rooms in a specific hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object for which to display detailed room information.
     */
	private void detailedViewHotelMenu(Hotel foundHotel) {

		final String[] DETAILED_VIEWHOTEL_OPTS = new String[] { "No. of Available & Booked Rooms for a Selected Date", 
													"Room Information", "Reservation Information", 
													"Go to previous menu"};
		while(true) {
			displayOptions(DETAILED_VIEWHOTEL_OPTS);
			int chosen = IH.getValidIntegerInRange(1, DETAILED_VIEWHOTEL_OPTS.length, "\t>> ");
			if(chosen == -1) {
				return;
			}
			switch (chosen) {
			/* this needs date */
			case 1: dateAvailability(foundHotel); break;
			
			/* this needs room name */
			case 2: roomInfo(foundHotel); break;
			/* i guess a reservation id  roomPrefix + roomNo.*/ 
			case 3: reservationInfo(foundHotel); break;
			case 4: return;			
			}
			
		}
		
	}
	/**
     * Displays the availability of rooms in a hotel for a specific day.
     * 
     * @param foundHotel The <code>Hotel</code> object to check for room availability.
     */
	private void dateAvailability(Hotel foundHotel) {
	
		while(true) {	
		
			int day = IH.getValidIntegerInRange(1, 31, "\tEnter a day [Number 1 to 31]: ");
			if(day == 31) {
				System.out.println("\tDay is not available to check in.");
				continue;
			}
			if(day == -1) {
				return;
			}
			
			int num = foundHotel.getNoOfAvailableRooms(day);
			
			System.out.println("\tNo. of available rooms bv Day " + day + ": " + num);
			System.out.println("\tNo. of booked rooms by Day " + day + ": " + (foundHotel.getNoOfRooms() - num));
			System.out.print("\n\tPress enter to continue");
			scn.nextLine();
			
			if(!IH.confirmation("Do you want to check other days?")) {
				return;
			}
		}
	}
	  /**
     * Displays a list of available rooms in a selected hotel for booking.
     * 
     * @param hotel The <code>Hotel</code> object to display available rooms for.
     */
	private void displayRoomsOfHotel(Hotel hotel) {
		System.out.println("\n\n");
		for(Room room: hotel.getRooms())
			System.out.println("\t" + room.getRoomName());
	}
	/**
     * Selects a room from the list of available rooms in a hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object to select a room from.
     * @return The selected <code>Room</code> object, or <code>null</code> if no valid room is selected.
     */
	private Room selectRoom(Hotel foundHotel) {
		
		while(true){
			System.out.println("\tSelect a room");
			System.out.print("\t>> ");
			displayRoomsOfHotel(foundHotel);
			String someString = scn.nextLine();
			if(someString.isBlank())
				return null;
			Room room = foundHotel.findRoom(someString);
			if(room != null) {
				return room;
			}
			System.out.println("\tRoom not found. Enter a valid room or Press enter to exit.");
		}
	}
	  /**
     * Displays detailed information about a selected room in a hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object containing the room to display information for.
     */
	private void roomInfo(Hotel foundHotel) {
		
		while(true){
			Room room;
			room = selectRoom(foundHotel);
			if(room == null) {
				return;
			}
			System.out.println("Room name: " + room.getRoomName());
			System.out.println("Room price: " + foundHotel.getPrice());
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println("Room availability: " + room.availability());
			
			System.out.println("\n\tPress enter to continue.");
			scn.nextLine();
			
			if(!IH.confirmation("Do you want to check out other rooms?")){
				return;
			}
			
		}
	}
	 /**
     * Selects a reservation from the list of reservations in a hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object to select a reservation from.
     * @return The selected <code>Reservation</code> object, or <code>null</code> if no valid reservation is selected.
     */	
	private Reservation selectReservation(Hotel foundHotel) {
		Reservation reservation;
		while(true) {
			
			for(int i = 0; i < foundHotel.getReservation().size(); i++) {
				System.out.println("\t"+foundHotel.getReservation().get(i).getReservationID());
			}
			
			System.out.print("\tEnter Reservation ID: ");
			String someString = scn.nextLine();
			if(someString.isBlank()) {
				return null;
			}
			reservation = foundHotel.rFinder(someString);
			if(reservation != null) {
				return reservation;
			}
			System.out.println("\teservation not found");
		}
	}
    /**
     * Displays detailed information about a selected reservation in a hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object containing the reservation to display information for.
     */	
	private void reservationInfo(Hotel foundHotel) {
		Reservation reservation;
		while(true) {
			reservation = selectReservation(foundHotel);
			if(reservation == null) {
				return;
			}
			System.out.println("\n\n\tGuest name: " + reservation.getGuestName());
			System.out.println("\tRoom information: " + reservation.getRoomName());
			System.out.println("\tCheck-in: " + reservation.getCheckIn());
			System.out.println("\tCheck-out: "+ reservation.getCheckOut());
			System.out.println("\tPrice per night: " + reservation.getPricePerNight());
			System.out.println("\tTotal price: " + reservation.getTotalPrice());
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println(reservation.getRoom().availability());
			
			if(!IH.confirmation("Do you want to check other reservations? ")) {
				return;
			}
		}
	}
	/**
     * Selects a hotel from the list of available hotels.
     * 
     * @return The selected <code>Hotel</code> object, or <code>null</code> if no valid hotel is selected.
     */
	private Hotel selectHotel() {
			Hotel selectedHotel;
			String answer;
			while(true){
				HRS.displayHotels();
				System.out.println("\tSelect a hotel");
				System.out.print("\t>> ");
				answer = scn.nextLine();
				if(answer.isBlank()) {
					return null;
				}
				selectedHotel = HRS.findHotel(answer);
				if(selectedHotel != null) {
					return selectedHotel;
				}
				System.out.println("\tHotel not found. Enter a valid hotel or Press enter to exit.");
			}
			
		}
	/**
     * Manages options related to a selected hotel, such as changing hotel name, adding rooms,
     * removing rooms, changing room prices, removing reservations, or removing the entire hotel.
     */
	private void ManageHotelMenu() {
		while(true) {
			
			final String[] MANAGEHOTEL_OPTS = new String[]{"Change Hotel", "Add Room(s)", "Remove Room(s)",
														  "Change Room Price", "Remove Reservation", "Remove Hotel",
														  "Exit"};
			
			displayOptions(MANAGEHOTEL_OPTS);
			int chosen = IH.getValidIntegerInRange(1, 7, "\t>> ");
			if(chosen == -1) {
				return;
			}
			
			switch(chosen) {
	
			case 1:   changeHotelName(); break;
			case 2:			addRooms(); break;
			case 3:      removeRooms(); break;
			case 4:   changeRoomPrice(); break;
			case 5: 	removeReservations(); break;
			case 6:       removeHotel(); break;
			case 7: return;
			}
		}
		/* However, users must be prompted to confirm a modification or else the modification would be discarded.*/
	}
	/**
     * Handles changing the name of a selected hotel.
     */
	private void changeHotelName() {
			
			String answer;
			Hotel selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			
			answer = IH.getUniqueHotelName(HRS, "\tNew Hotel Name: ");
			if(answer == null) {
				return;
			}
			
			if(IH.confirmation("\tReplace "+selectedHotel.getName() + " with " + answer + " ? ")) {
				selectedHotel.setName(answer); 
				System.out.println("\tHotel name has been changed");
				
			}else {
				System.out.println("\tHotel name has NOT been changed");
			}
			
		}
	 /**
     * Handles adding rooms to a selected hotel.
     */	
	private void addRooms() {
		
		Hotel selectedHotel;
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 50)
				System.out.println("\tHotel is already at max rooms. Pick another one or Press Enter to exit.");
		}while(selectedHotel.getNoOfRooms() == 50);
		
		int noOfRooms;
		
		System.out.println("\tYou can only add until " + (50 - selectedHotel.getNoOfRooms()) + " room.");
		noOfRooms = IH.getValidIntegerInRange(1, 50 - selectedHotel.getNoOfRooms(), 
					"\tHow many room(s) do you want to add for " + selectedHotel.getName() +  "? ");
	
		if(noOfRooms == -1) {
			return;
		}
			
		if(IH.confirmation("\rAre you sure that you want to add "+ noOfRooms + " to " + selectedHotel.getName() +  "?")) {
			selectedHotel.addRooms(noOfRooms);
			
			System.out.println("\t " + noOfRooms + " room(s) has been added to " + selectedHotel.getName());
			System.out.println("\t " + selectedHotel.getName() + " now has " + selectedHotel.getNoOfRooms() + " room(s)");
			
			if(selectedHotel.getNoOfRooms()  == 50) {
				System.out.println("\tHotel has reached maximum rooms.");
			}
			
			System.out.println("\n\tPress Enter to continue.\n");
			scn.nextLine();
		}
		
		
	}

	/**
     * Handles removing rooms from a selected hotel.
     */
	private void removeRooms() {
		Hotel selectedHotel;
		
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 1)
				System.out.println("\tHotel is already at min rooms. Pick another one");
		}while(selectedHotel.getNoOfRooms() == 1);
		
		int noOfRooms;
	
		System.out.println("\tYou can only remove " + selectedHotel.removableRooms() + " rooms");
		
		noOfRooms = IH.getValidIntegerInRange(1, selectedHotel.removableRooms(), 
				"\tHow many rooms do you want to remove for " + selectedHotel.getName() +  "?");
		if(noOfRooms == -1) {
			return;
		}
			
		if(IH.confirmation("\tAre you sure that you want to remove "+ noOfRooms + " rooms off " + selectedHotel.getName() +  "?")){
			selectedHotel.roomRemover(noOfRooms);

			System.out.println("\t " + noOfRooms + " room(s) has been removed from " + selectedHotel.getName());
			System.out.println("\t " + selectedHotel.getName() + " now has " + selectedHotel.getNoOfRooms() + " room(s)");
			
			if(selectedHotel.getNoOfRooms() == 1) {
				System.out.println("\tHotel has reached minimum rooms.");

			}
			System.out.println("\n\tPress Enter to continue.\n");
			scn.nextLine();
		}
		
		
	}
	 /**
     * Handles changing the price of rooms in a selected hotel.
     */
	private void changeRoomPrice() {
		Hotel selectedHotel;
		do {
		selectedHotel = selectHotel();
		if(selectedHotel == null) {
			return;
		}
		if(!selectedHotel.getReservation().isEmpty()) {
			System.out.println("\tHotel has a reservation cannot change price.");
			System.out.println("\n\tPress enter to continue. Press enter twice to exit.");
			scn.nextLine();
		}	
		}while(!selectedHotel.getReservation().isEmpty());
	
		double roomPrice;
		
		roomPrice = IH.getMinDouble(100.00, "\tHow much do you want to change the price? ");
		if(roomPrice == -1) {
			return;
		}
		
		if(IH.confirmation("\tChange price from " + selectedHotel.getPrice() + " to " + roomPrice + " ?"))
		{
			selectedHotel.setPrice(roomPrice);
			System.out.println("\tPrice has been changed to " + selectedHotel.getPrice());
			
		}
			

	}
	/**
     * Handles removing a selected hotel from the system.
     */
	private void removeHotel() {
		Hotel selectedHotel;
		
		System.out.println("\n\n\t\tRemove Hotel");
		
		selectedHotel = selectHotel();
		if(selectedHotel == null) {
			return;
		}
		if(IH.confirmation("\n\tDo you want to remove " + selectedHotel.getName() + "? ")) {
			HRS.remove(selectedHotel);
			System.out.println("\tHotel has been removed");
		}
	}
	 /**
     * Handles removing a selected reservation from a hotel.
     */
	private void removeReservations() {

		Hotel selectedHotel;
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getReservation().isEmpty()) {
				System.out.println("\tHotel has no reservations pick another one.");
			}
				
		}while(selectedHotel.getReservation().isEmpty());
		String reservationID;
		Reservation ram;
		do{
			reservationID = scn.nextLine();
			
			if(reservationID.isBlank()) {
				return;
			}
			ram = selectedHotel.rFinder(reservationID);
			if(ram == null) {
				System.out.println("\tThere are no reservation ID like that.");
			}
		}while(ram == null);
			
		if(IH.confirmation("\tAre you sure that you want to remove that reservation? "))
		{
			Room room = ram.getRoom();
			selectedHotel.removeReservation(ram);
			room.removeReservation(ram);
			System.out.println("\tRemoved reservation");
			System.out.println("\n\tPress Enter to continue.");
			scn.nextLine();
		}
	}
	
	/**
     * Displays the menu for simulating a booking in a selected hotel.
     */
	private void displaySimulateBookingMenu() {
		 /* No reservations can be made when the check-out is on the 1st of the month 
		  * or when the check-in is  on the 31st of the month. 
		  * Bookings cannot be made outside of the defined period for the month.
		  */
		Hotel hotelToBook;
		do{
		/* Display the hotel list */
			hotelToBook = selectHotel();
			if(hotelToBook == null) {
				return;
			}
			System.out.print("\tGuest name:");
			String guestName = scn.nextLine();
			if(guestName.isBlank()) {
				return;
			}
			
			System.out.println(); 
			int checkIn = IH.getValidIntegerInRange(1, 30, "\tCheck in: ");
			
			if(checkIn == -1) {
				return;
			}
			int checkOut;
			do {
					System.out.println();
					
					checkOut = IH.getValidIntegerInRange(2, 31, "\tCheck out: ");
					
					if(checkOut == -1) {
						return;
				}
				if(checkOut == checkIn) {
					System.out.println("\tYou cannot check in and check out in the same day.");
				}
			
			}while(checkOut == checkIn);
			
		/* ask for checkIn checkOut dates */
		/* Bookings cannot be made outside of defined period */
			/*make this actually work*/
			
			Room room = hotelToBook.findAvailableRoom(checkIn, checkOut);
			if(room  == null) {
				System.out.println("\tThere are no available rooms in this hotel, given the time frame");
				return;
			}
			Reservation some = new Reservation(guestName, checkIn, checkOut, room);
			hotelToBook.addReservation(some);
			room.addReservation(some);
			System.out.println("\tReservation has been added");
			System.out.println("\tReservationID " + some.getReservationID());
		/* if SELECTING FOR ROOM IS GONNA BE MANUAL YOU FUCKS ARE GONNA PAY */
		
		}while(IH.confirmation("\tDo you want to make another booking?"));
		/* ask if they want to make a booking again, which displays hotel list again */
	}
//5 MOREEEEEEEEEEEE
}
