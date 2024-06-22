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
		
		System.out.println("      Hotel   Reservation   System\n");
		System.out.println("       Press any key to continue");
		scn.nextLine();
		
		while(!stop) {
			ascii.otherMenu();
			displayOptions(new String[] { "Create Hotel", "View Hotel", "Manage Hotel", "Simulate Booking" ,"Exit"});
			
			chosenOption = getValidNumberInRange(1, 5);
			
			switch(chosenOption) {
				case 1:		displayCreateHotelMenu(); break; //Create Hotel is done, the other functions like reserving will be finished later
				case 2:		displayViewHotelMenu(); break;   //I won't stop until i finish Simulate Booking today
				case 3:		displayManageHotelMenu(); break; // I won't stop until i finish Simulate Booking today
				case 4:		displaySimulateBookingMenu(); break; //I won't stop until i finish SImulate Booking today
				case 5: 	System.out.println("Exitting program..."); stop = true; break;
				default: break;
			}
			
		}
		
		ascii.animegirlheart();
		
	}
	
	private void displayOptions(String[] someStringsToDisplay) {
		System.out.println("\n");
		int i = 0;
		for (String someString: someStringsToDisplay) {
            System.out.println("\t[" + ++i + "]  " + someString);
        }
	
		System.out.print("\t>> ");
	}
	
	
private void displayCreateHotelMenu() {
		
		String newHotelName;
		int newNoOfRooms;
		System.out.println("\t\tCreate Hotel\n");
		System.out.println("\tPress enter to exit anytime");
		do {
			do {
				System.out.print("\tHotel name: ");
				
				newHotelName = scn.nextLine();
				if(!HRS.isHotelNameUnique(newHotelName))
					System.out.println("\t\n" + newHotelName + " is not unique.\n");
				
			}while(!HRS.isHotelNameUnique(newHotelName));
			
			if(newHotelName.isBlank())
				return;
			
			System.out.print("\tNo. of rooms: ");
			
			newNoOfRooms = getValidNumberInRange(1, 50);
			
			if(newNoOfRooms == -1)
				return;
			
			HRS.createHotel(newHotelName, newNoOfRooms);

			System.out.println("\t" + newHotelName + " with " + newNoOfRooms + " rooms has been created!");
		}while(confirmation("\tDo you want to create another hotel?"));
		//System.out.println("3. Price");
		//System.out.println("4. Exit");
		//this creates one instance of hotel OR NADA
		
		
		//Name must be given
		//then a hotel would be made with a minimum of 1 room
		// then a hotel would be made with  minimum of base price  which is 1299.0
	}
	

/*
 * ------------------------------------------------------------------------------------------------------
 */
	private void displayViewHotelMenu() {
		
		if(HRS.isEmpty()) {
			System.out.println("\tThere are no hotels to view.");
			return;
		}
		
		boolean stop = false;
		Hotel foundHotel;
		String hotelToFind;
		do {
			System.out.println("\t\tView Hotel");
			do {
				HRS.displayHotels();
				//System.out.println("**displays all the hotel**");
				System.out.println("\tSelect a hotel to view");
				System.out.print("\t>> ");
				hotelToFind = scn.nextLine();
				
				if(hotelToFind.isBlank())
					return;
				
				foundHotel = HRS.findHotel(hotelToFind);
				if(foundHotel == null)
					System.out.println("\tHotel not found. Enter a valid hotel or press enter to go to previous menu.");
			
			}while(foundHotel == null);
			
			do {
				displayOptions(new String[] {"Overview Information", "Detailed Information", "View another hotel", "Exit"});
				int chosen  = getValidNumberInRange(1, 4);
				
				if(chosen == -1) {
					return;
				}
				switch (chosen) {
					case 1: displayOverviewInfo(foundHotel); break;
					case 2: detailedViewHotelMenu(foundHotel); break;
					case 3: System.out.println("Viewing another hotel"); stop = true; break;
					case 4:	return;
				}
		
			}while(!stop);
			
		}while(true);
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
	

	
	private void detailedViewHotelMenu(Hotel foundHotel) {
		
		boolean stop = false;
		
		do {
			displayOptions(new String[] { "No. of Available & Booked Rooms for a Selected Date", "Room Information", "Reservation Information", "Go back to previous menu"});
			int chosen = getValidNumberInRange(1, 4);
			switch (chosen) {
			/* this needs date */
			case 1: dateAvailability(foundHotel); break;
			
			/* this needs room name */
			case 2: roomInfo(foundHotel); break;
			/* i guess a reservation id  roomPrefix + roomNo.*/ 
			case 3: reservationInfo(foundHotel); break;
			case 4: stop = true; break;
			}
			
		}while(!stop);
		
	}
	
	private void dateAvailability(Hotel foundHotel ) {
		
		int i, ctr = 0, j,date;
		boolean isAvailable = false;
		do {	
			
			System.out.print("\tEnter a day [Number 1 to 31]: ");
			date = getValidNumberInRange(1, 31);
			if(date == -1) {
				return;
			}
			for(i = 0; i < foundHotel.getNoOfRooms(); i++) {
				Room room = foundHotel.getRoom(i);
				for(j = 0; j < room.getReservationSize() && !isAvailable; j++) {
					Reservation reservation = room.getReservation(j);
					if(date < reservation.getCheckIn() || date >= reservation.getCheckOut()) {
						isAvailable = true;
					}
				}
				if(isAvailable || j == 0) {
					ctr++;
					isAvailable = false;
				}
			}
			
			System.out.println("\tThe number of available rooms bv Day " + date + " is " + ctr);
			System.out.println("\tThe number of booked rooms by Day " + date + " is " + (foundHotel.getNoOfRooms() - ctr));
			System.out.println("\n\tPress enter to continue");
			ctr = 0;
			scn.nextLine();
		
		}while(confirmation("Do you want to check other dates?"));
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
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println("Room availability: " + room.availability());
			
			System.out.println("\n\tPress enter to continue.");
			scn.nextLine();
			
		}while(confirmation("Do you want to check out other rooms?"));
	}
	public void reservationInfo(Hotel hotel) {
		Reservation reservation;
		do {
			do {
				
				String someString = scn.nextLine();
				if(someString.isBlank())
					return;
				reservation = hotel.rFinder(someString);
			}while(reservation == null);
			System.out.println("Guest name: " + reservation.getGuestName());
			System.out.println("Room information: " + reservation.getRoomName());
			System.out.println("Check-in: " + reservation.getCheckIn());
			System.out.println("Check-out: "+ reservation.getCheckOut());
			System.out.println("Price per night: " + reservation.getPricePerNight());
			/* THIS SHOULD PRINT OUT A CALENDAR OK */
			System.out.println("Room availability: " + reservation.getRoom().availability());
		}while(confirmation("Do you want to check other reservations?"));
	}
	
	private void displayOverviewInfo(Hotel foundHotel) {
					
		System.out.println("\tHotel Name: " + foundHotel.getName());
		System.out.println("\tTotal number of rooms: " + foundHotel.getNoOfRooms());
		System.out.println("\tEstimate earnings: " + foundHotel.getEstimate());
		System.out.print("\n\n\tPress enter to continue.");
		scn.nextLine();
	}
	
	
	private void displayManageHotelMenu() {
		boolean stop = false;
		do {
			
			
			displayOptions(new String[]{"Change Hotel Name", "Add Room(s)", "Remove Room(s)", 
				       "Change Room Price", "Remove Reservation", "Remove Hotel",  "Exit"});
			int chosen = getValidNumberInRange(1, 7);
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
			case 7: System.out.println("Go to previous menu"); stop = true; break;
			}
		}while(!stop);
		/* However, users must be prompted to confirm a modification or else the modification would be discarded.*/
	}
	
	
	private void removeReservations() {
		Hotel selectedHotel;
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getReservation().isEmpty()) {
				System.out.println("Hotel has no reservations pick another one.");
			}
				
		}while(selectedHotel.getReservation().isEmpty());
		String reservationID;
		Reservation ram;
		do {
			do{
				reservationID = scn.nextLine();
			
			if(reservationID.isBlank()) {
				return;
			}
			ram = selectedHotel.rFinder(reservationID);
			if(ram == null) {
				System.out.println("There are no reservation ID like that.");
			}
		}while(ram == null);
			
			
		}while(!confirmation("Are you sure that you want to remove reservation?"));
		
		
		selectedHotel.removeReservation(ram);
		System.out.println("Removed reservation");
		System.out.println("Press Enter to continue.");
		scn.nextLine();
		
	}
	
	private void removeRooms() {
		Hotel selectedHotel;
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 1)
				System.out.println("Hotel is already at min rooms. Pick another one");
		}while(selectedHotel.getNoOfRooms() == 1);
		
		int noOfRooms;
		int hotelsrooms;
		do {
			System.out.println("You can only remove" + selectedHotel.removableRooms());
			System.out.println("How many rooms do you want to remove for " + selectedHotel.getName() +  "?");
			
			noOfRooms = getValidNumberInRange(1, selectedHotel.removableRooms());
			if(noOfRooms == -1) {
				return;
			}
			hotelsrooms = selectedHotel.getNoOfRooms();
			
		}while(!confirmation("Are you sure that you want to remove "+ noOfRooms + " rooms off " + selectedHotel.getName() +  "?"));
		
		selectedHotel.roomRemover(noOfRooms);
		
		if(hotelsrooms - noOfRooms  == 1) {
			System.out.println("\tHotel has reached minimum rooms.");
			System.out.println("\n\tPress Enter to continue.\n");
			scn.nextLine();
		}
	}

	private void removeHotel() {
		Hotel selectedHotel;
		do {
			
			System.out.println("What hotel do you want to delete?");
			
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			HRS.remove(selectedHotel);
			System.out.println("Hotel has been removed");
			
			
		}while(confirmation("Do you want to remove another hotel?"));
	}
	private void changeRoomPrice() {
		Hotel selectedHotel = selectHotel();
		if(selectedHotel == null) {
			return;
		}
		double roomPrice;
		do {
			
			System.out.println("How much do you want to change the price?");
			
			roomPrice = getValidNumberInRange(100.00);
			if(roomPrice == -1) {
				return;
			}
			selectedHotel.setPrice(roomPrice);
			
			
		}while(confirmation("Do you want to change the price again for" + selectedHotel.getName() + "?"));
	}
		
	private void addRooms() {
		
		Hotel selectedHotel;
		do {
			selectedHotel = selectHotel();
			if(selectedHotel == null) {
				return;
			}
			if(selectedHotel.getNoOfRooms() == 50)
				System.out.println("Hotel is already at max rooms. Pick another one");
		}while(selectedHotel.getNoOfRooms() == 50);
		
		int noOfRooms;
		int hotelsrooms;
		do {
			
			System.out.println("How many rooms do you want to add for " + selectedHotel.getName() +  "?");
			
			noOfRooms = getValidNumberInRange(1, 50 - selectedHotel.getNoOfRooms());
			if(noOfRooms == -1) {
				return;
			}
			hotelsrooms = selectedHotel.getNoOfRooms();
			
		}while(!confirmation("Are you sure that you want to add "+ noOfRooms + " to " + selectedHotel.getName() +  "?"));
		
		selectedHotel.addRooms(noOfRooms + hotelsrooms);
		
		if(noOfRooms + hotelsrooms  == 50) {
			System.out.println("\tHotel has reached maximum rooms.");
			System.out.println("\n\tPress Enter to continue.\n");
			scn.nextLine();
		}
	}
	
	
	
	private void changeHotelName() {
		
		String answer;
		Hotel selectedHotel = selectHotel();
		if(selectedHotel == null) {
			return;
		}
		
		do {
			System.out.print("New Hotel Name: ");
			answer = scn.nextLine();
			if(answer.isBlank()) {
				return;
			}
			if(HRS.isHotelNameUnique(answer)) {
				selectedHotel.setName(answer); 
				System.out.println("Hotel name has been changed");
				return;
			}
			System.out.println("New Hotel name is not unique");
			
		}while(true);
		
		
	}
	private Hotel selectHotel() {
		
		Hotel selectedHotel;
		String answer;
		do {
			HRS.displayHotels();
			System.out.println("Select a hotel");
			answer = scn.nextLine();
			if(answer.isBlank()) {
				return null;
			}
			selectedHotel = HRS.findHotel(answer);
			if(selectedHotel != null) {
				return selectedHotel;
			}
			System.out.println("Invalid Input. select a valid hotel");
			
		}while(true);
		
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
			do {
				System.out.println("Hotel name: ");
				String someString = scn.nextLine();
				if(someString.isBlank())
					return;
				hotelToBook = HRS.findHotel(someString);
			}while(hotelToBook == null);
			
			
			String guestName = scn.nextLine();
			if(guestName.isBlank()) {
				return;
			}
			
			System.out.println("Check in: "); 
			int checkIn = getValidNumberInRange(1, 30);
			
			if(checkIn == -1) {
				return;
			}
			int checkOut;
			do {
			System.out.println("Check out: ");
			
			checkOut = getValidNumberInRange(2, 31);
			
			if(checkOut == -1) {
				return;
			}
			if(checkOut == checkIn) {
				System.out.println("You cannot check in and check out in the same day.");
			}
			}while(checkOut == checkIn);
			
		/* ask for checkIn checkOut dates */
		/* Bookings cannot be made outside of defined period */
			
			hotelToBook.addReservation(new Reservation(guestName, checkIn, checkOut, hotelToBook.getRoom(0)));
		/* if SELECTING FOR ROOM IS GONNA BE MANUAL YOU FUCKS ARE GONNA PAY */
		
		}while(confirmation("Do you want to make another booking"));
		/* ask if they want to make a booking again, which displays hotel list again */
	}
	
	
	
	
	
	
	
	private boolean confirmation(String someString) {
		
		while(true) {
			System.out.print(someString + " ");
			
			String answer = scn.nextLine();
	
			switch (answer) {
				case "Y": return true;
				case "N": return false;
				default: System.out.println("\n\n\tInvalid input. Enter Y or N\n\n"); break;
			}
		}
	}

	
	private int getPositiveIntegerInput() {
		int number = 0;
	    boolean validInput = false;

	    while (!validInput) {
	        try {
	            String input = scn.nextLine();
	            if (input.isBlank()) {
	                return -1;
	            } 
                number = Integer.parseInt(input);
                if (number > 0) {
                    validInput = true; 
                } else {
                    System.out.println("\n\tInput should be positive.");
                }
	            
	        } catch (NumberFormatException e) {
	            System.out.println("\n\tInvalid input. Please enter a valid positive integer.");
	        }
	    }
	    
	    return number;
	}
	
	private int getValidNumberInRange(int min, int max) {
		int someNumber = 0;
		boolean isInRange;
		do {
			someNumber = getPositiveIntegerInput();
			if(someNumber == -1) {
				return -1;
			}
			isInRange = (someNumber >= min && someNumber <= max);
			if(!isInRange) {
				System.out.println("\tInvalid input. number is not in range. \n");
			
			}
			
		}while(!isInRange);
		return someNumber;
	}
	
	
	
	private double getPositiveDoubleInput() {
		double number = 0;
	    boolean validInput = false;

	    while (!validInput) {
	        try {
	            String input = scn.nextLine();
	            if (input.isBlank()) {
	                return -1;
	            } 
                number = Double.parseDouble(input);
                if (number > 0) {
                    validInput = true; 
                } else {
                    System.out.println("\n\tInput should be positive.");
                }
	            
	        } catch (NumberFormatException e) {
	            System.out.println("\n\tInvalid input. Please enter a valid positive integer.");
	        }
	    }
	    
	    return number;
	}
	
	private double getValidNumberInRange(double min) {
		double someNumber = 0;
		boolean isInRange;
		do {
			someNumber = getPositiveDoubleInput();
			isInRange = (someNumber >= min);
			if(!isInRange) {
				System.out.println("Invalid input. number is not in range. \n");
			}
			
		}while(!isInRange);
		return someNumber;
	}
	
	
}
