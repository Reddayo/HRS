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
public class MenuHandler {

	private final Scanner scn;
	private final HotelListManager HRS;
	private final InputHandler IH;
		
	 /**
     * Constructs a new <code>Menu</code> instance.
     * 
     * @param HRS The <code>HotelReservationSystem</code> instance to interact with.
     * @param scn The <code>Scanner</code> instance for user input.
     */
	MenuHandler (HotelListManager HRS, Scanner scn){
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
			
			chosenOption = IH.getValidIntegerInRange(1, MENU_OPTIONS.length, ">> ");
			
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
            System.out.println("[" + ++i + "]  " + someString);
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
			System.out.println("\n\nCreate Hotel\n\n");
			System.out.println("Press enter to abort.");
	
			newHotelName = IH.getUniqueHotelName(HRS, "\nHotel name: ");
			
			if(newHotelName == null)
				return;
			
			newNoOfRooms = IH.getValidIntegerInRange(1, HotelListManager.MAX_ROOMS, "\nNo. of rooms: " );
			
			if(newNoOfRooms == -1)
				return;
			
			HRS.createHotel(newHotelName, newNoOfRooms);

			System.out.println("\n\n" + newHotelName + " with " + newNoOfRooms + " rooms has been created!");
			if(!IH.confirmation("\nDo you want to create another hotel?"))
				return;
		}
	}
	/**
     * Displays an overview of a selected hotel's information.
     */
	private void ViewHotel() {
		
		if(HRS.isEmpty()) {
			System.out.println("\nThere are no hotels to view.");
			System.out.println("\nPress enter to continue.");
			scn.nextLine();
			return;
		}
		final String[] VIEWHOTEL_OPTS = new String[] {"Overview Information", 
														"Detailed Information", 
														"View another hotel", 
														"Exit"};
		while(true) {
			boolean stop = false;
			System.out.println("\n\nView Hotel\n\n");
			Hotel foundHotel = IH.selectHotel(HRS);
			if(foundHotel == null) {
				return;
			}
			
			do {
				displayOptions(VIEWHOTEL_OPTS);
				int chosen  = IH.getValidIntegerInRange(1, VIEWHOTEL_OPTS.length, ">> ");
				
				if(chosen == -1) {
					return;
				}
				switch (chosen) {
					case 1: displayOverviewInfo(foundHotel); break;
					case 2: detailedViewHotelMenu(foundHotel); break;
					case 3: System.out.println("Viewing another hotel."); stop = true; break;
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
		
		System.out.println("\n\nHotel Name: " + foundHotel.getName());
		System.out.println("Total number of rooms: " + foundHotel.getNoOfRooms());
		System.out.println("Estimate earnings: " + foundHotel.getEstimate());
		System.out.print("\n\nPress enter to continue.");
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
			int chosen = IH.getValidIntegerInRange(1, DETAILED_VIEWHOTEL_OPTS.length, ">> ");
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
		
			int day = IH.getValidIntegerInRange(1, 31, "\n\nEnter a day [Number 1 to 31]: ");
			if(day == 31) {
				System.out.println("Day is not available to check in.");
				continue;
			}
			if(day == -1) {
				return;
			}
			//System.out.println(foundHotel.getNoOfRooms());
			int num = foundHotel.getNoOfAvailableRooms(day);
			
			System.out.println("\nNo. of available rooms bv Day " + day + ": " + num);
			System.out.println("No. of booked rooms by Day " + day + ": " + (foundHotel.getNoOfRooms() - num));
			System.out.print("\nPress enter to continue.");
			scn.nextLine();
			
			if(!IH.confirmation("\nDo you want to check other days?")) {
				return;
			}
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
			room = IH.selectRoom(HRS, foundHotel);
			if(room == null) {
				return;
			}
			System.out.println("\n\nRoom name: " + room.getRoomName());
			System.out.println("Room price: " + foundHotel.getPrice());
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println(room.availability());
			
			System.out.print("\nPress enter to continue.");
			scn.nextLine();
			
			if(!IH.confirmation("\nDo you want to check out other rooms?")){
				return;
			}
			
		}
	}
	 /**
     * Displays detailed information about a selected reservation in a hotel.
     * 
     * @param foundHotel The <code>Hotel</code> object containing the reservation to display information for.
     */	
	private void reservationInfo(Hotel foundHotel) {
		Reservation reservation;
		if(foundHotel.getReservation().isEmpty()) {
			System.out.println("\n\n" + foundHotel.getName() + " currently has no reservations");
			System.out.println("\nPress enter to continue");
			scn.nextLine();
			return;
		}
		while(true) {
			reservation = IH.selectReservation(HRS, foundHotel);
			if(reservation == null) {
				return;
			}
			System.out.println("\n\nGuest name: " + reservation.getGuestName());
			System.out.println("Room information: " + reservation.getRoomName());
			System.out.println("Check-in: " + reservation.getCheckIn());
			System.out.println("Check-out: "+ reservation.getCheckOut());
			System.out.println("Price per night: " + reservation.getPricePerNight());
			System.out.println("Total price: " + reservation.getTotalPrice());
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println(reservation.getRoom().availability());
			
			if(!IH.confirmation("Do you want to check other reservations? ")) {
				return;
			}
		}
	}
	/**
     * Manages options related to a selected hotel, such as changing hotel name, adding rooms,
     * removing rooms, changing room prices, removing reservations, or removing the entire hotel.
     */
	private void ManageHotelMenu() {
		while(true) {
			if(HRS.isEmpty()) {
				System.out.println("\nThere are no hotels to manage.");
				System.out.println("\nPress enter to continue.");
				scn.nextLine();
				return;
			}
			final String[] MANAGEHOTEL_OPTS = new String[]{"Change Hotel", "Add Room(s)", "Remove Room(s)",
														  "Change Room Price", "Remove Reservation", "Remove Hotel",
														  "Exit"};
			
			displayOptions(MANAGEHOTEL_OPTS);
			int chosen = IH.getValidIntegerInRange(1, 7, ">> ");
			if(chosen == -1) {
				return;
			}
			System.out.println("\n\n");
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
			
			Hotel selectedHotel = IH.selectHotel(HRS);
			if(selectedHotel == null) {
				return;
			}
			
			answer = IH.getUniqueHotelName(HRS, "\nNew Hotel Name: ");
			if(answer == null) {
				return;
			}
			
			if(IH.confirmation("\nReplace "+selectedHotel.getName() + " with " + answer + " ? ")) {
				selectedHotel.setName(answer); 
				System.out.println("\nHotel name has been changed");
				
			}else {
				System.out.println("\nHotel name has NOT been changed");
			}
			
		}
	 /**
     * Handles adding rooms to a selected hotel.
     */	
	private void addRooms() {
		
		Hotel selectedHotel;
		do {
			selectedHotel = IH.selectHotel(HRS);
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 50) {
				System.out.println("\nHotel is already at max rooms.");
				System.out.println("\nPress enter to pick again. Press enter twice to leave.");
				scn.nextLine();
			}
		}while(selectedHotel.getNoOfRooms() == 50);
		
		int noOfRooms;
		
		System.out.println("\nYou can only add until " + (50 - selectedHotel.getNoOfRooms()) + " room(s).");
		noOfRooms = IH.getValidIntegerInRange(1, 50 - selectedHotel.getNoOfRooms(), 
				"\nHow many room(s) do you want to add for " + selectedHotel.getName() +  "? ");
	
		if(noOfRooms == -1) {
			return;
		}
			
		if(IH.confirmation("\nAre you sure that you want to add "+ noOfRooms + " to " + selectedHotel.getName() +  "?")) {
			selectedHotel.addRooms(noOfRooms);
			
			System.out.println("\n" + noOfRooms + " room(s) has been added to " + selectedHotel.getName());
			System.out.println("\n" + selectedHotel.getName() + " now has " + selectedHotel.getNoOfRooms() + " room(s)");
			
			if(selectedHotel.getNoOfRooms()  == 50) {
				System.out.println("\nHotel has reached maximum rooms.");
			}
			
			System.out.println("\nPress Enter to continue.");
			scn.nextLine();
		}
		
		
	}

	/**
     * Handles removing rooms from a selected hotel.
     */
	private void removeRooms() {
		Hotel selectedHotel;
		
		
		
		do {
			selectedHotel = IH.selectHotel(HRS);
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 1) {
				System.out.println("\nHotel is already at min rooms.");
				System.out.println("\nPress enter to continue. Enter blank to exit.");
				scn.nextLine();
			}
			
		}while(selectedHotel.getNoOfRooms() == 1);
		
		int noOfRooms;
	
		System.out.println("\nYou can only remove " + selectedHotel.removableRooms() + " room(s).");
		
		noOfRooms = IH.getValidIntegerInRange(1, selectedHotel.removableRooms(), 
				"\nHow many rooms do you want to remove for " + selectedHotel.getName() +  "? ");
		
		if(noOfRooms == selectedHotel.getNoOfRooms()) {
			System.out.println("\nWarning! Removal of all the rooms would remove " + selectedHotel.getName() + ".");
			if(IH.confirmation("\nDo you wish to proceed?")) {
				HRS.remove(selectedHotel);
				
			}
			return;
		}
		
		if(noOfRooms == -1) {
			return;
		}
			
		if(IH.confirmation("\nAre you sure that you want to remove "+ noOfRooms + " rooms off " + selectedHotel.getName() +  "?")){
			selectedHotel.roomRemover(noOfRooms);

			System.out.println("\n" + noOfRooms + " room(s) has been removed from " + selectedHotel.getName());
			System.out.println("\n" + selectedHotel.getName() + " now has " + selectedHotel.getNoOfRooms() + " room(s)");
			
			if(selectedHotel.getNoOfRooms() == 1) {
				System.out.println("\nHotel has reached minimum rooms.");

			}
			System.out.println("\nPress Enter to continue.");
			scn.nextLine();
		}
		
		
	}
	 /**
     * Handles changing the price of rooms in a selected hotel.
     */
	private void changeRoomPrice() {
		Hotel selectedHotel;
		do {
			selectedHotel = IH.selectHotel(HRS);
			if(selectedHotel == null) {
				return;
			}
			if(!selectedHotel.getReservation().isEmpty()) {
				System.out.println("\nHotel has a reservation cannot change price.");
				System.out.println("Press enter to pick again. Press enter again to exit.");
				scn.nextLine();
			}	
		}while(!selectedHotel.getReservation().isEmpty());
	
		double roomPrice;
		
		roomPrice = IH.getMinDouble(100.00, "\nHow much do you want to change the price? ");
		if(roomPrice == -1) {
			return;
		}
		
		if(IH.confirmation("\nChange price from " + selectedHotel.getPrice() + " to " + roomPrice + " ?"))
		{
			selectedHotel.setPrice(roomPrice);
			System.out.println("\nPrice has been changed to " + selectedHotel.getPrice());
			
		}
			

	}
	/**
     * Handles removing a selected hotel from the system.
     */
	private void removeHotel() {
		Hotel selectedHotel;
		
		System.out.println("\n\nRemove Hotel");
		
		selectedHotel = IH.selectHotel(HRS);
		if(selectedHotel == null) {
			return;
		}
		if(IH.confirmation("\nDo you want to remove " + selectedHotel.getName() + "? ")) {
			HRS.remove(selectedHotel);
			System.out.println("\nHotel has been removed");
		}
	}
	 /**
     * Handles removing a selected reservation from a hotel.
     */
	private void removeReservations() {

		Hotel selectedHotel;
		do {
			selectedHotel = IH.selectHotel(HRS);
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getReservation().isEmpty()) {
				System.out.println("\n" + selectedHotel.getName() + " currently has no reservations.");
				System.out.println("Pick another one. Press enter to continue. Press enter twice to exit.");
				scn.nextLine();
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
				System.out.println("\nThere are no reservation ID like that.");
			}
		}while(ram == null);
			
		if(IH.confirmation("\nAre you sure that you want to remove that reservation? "))
		{
			Room room = ram.getRoom();
			selectedHotel.removeReservation(ram);
			room.removeReservation(ram);
			System.out.println("\nRemoved reservation");
			System.out.println("\nPress Enter to continue.");
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
		if(HRS.isEmpty()) {
			System.out.println("\nThere are no hotels to book from.");
			System.out.println("\nPress enter to continue.");
			scn.nextLine();
			return;
		}
		Hotel hotelToBook;
		do{
			/* Display the hotel list */
			/* ask for checkIn checkOut dates */
			/* Bookings cannot be made outside of defined period */
				/*make this actually work*/
			System.out.println("\n\n");
			hotelToBook = IH.selectHotel(HRS);
			if(hotelToBook == null) {
				return;
			}
			System.out.print("\nGuest name: ");
			String guestName = scn.nextLine();
			
			if(guestName.isBlank()) {
				return;
			}
			
			System.out.println(); 
			int checkIn = IH.getValidIntegerInRange(1, 30, "\nCheck in: ");
			
			if(checkIn == -1) {
				return;
			}
			
			int checkOut;
			
			do {
					System.out.println();
					
					checkOut = IH.getValidIntegerInRange(2, 31, "\nCheck out: ");
					
					if(checkOut == -1) {
						return;
				}
				if(checkOut == checkIn) {
					System.out.println("\nYou cannot check in and check out in the same day.");
				}
				if(checkOut < checkIn) {
					System.out.println("You cannot check out before the check in day starts.");
				}
			
			}while(checkOut == checkIn || checkOut < checkIn);
			
			
		
			
			Room room = hotelToBook.findAvailableRoom(checkIn, checkOut);
			if(room  == null) {
				System.out.println("\nThere are no available rooms in this hotel, given the time frame");
				
			}else {
			Reservation some = new Reservation(guestName, checkIn, checkOut, room);
			hotelToBook.addReservation(some);
			System.out.println("\nReservation has been added");
			System.out.println("\nHere's your Reservation ID: " + some.getReservationID());
		/* if SELECTING FOR ROOM IS GONNA BE MANUAL YOU FUCKS ARE GONNA PAY */
			}
		}while(IH.confirmation("\nDo you want to make another booking?"));
		/* ask if they want to make a booking again, which displays hotel list again */
	}
//5 MOREEEEEEEEEEEE
}
