package com.hrs;

public class Calendar {
	private int maxDay;
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	Calendar(){
		this.maxDay = 31;
		
	}
	
	public void displayCalendar() {
		int i;
		for(i = 1; i <= maxDay; i++) {
			System.out.printf("%2d ", i);
			if(i % 7 == 0)
				System.out.println();
		}	
		System.out.println();
	}
	
	public void displayCalendar(Reservation reservation) {
		int i;
		for(i = 1; i <= maxDay; i++) {
			if(reservation.getCheckIn() <= i && reservation.getCheckOut() >= i) {
				System.out.print("██ ");
			}
			else {
			System.out.printf("%2d ", i);
			}
			if(i % 7 == 0)
				System.out.println();
		}	
		System.out.println();
	}
	
	public void displayCalendar2(Reservation reservation) {
		int i;
		for(i = 1; i <= maxDay; i++) {
			if(reservation.getCheckIn() <= i && reservation.getCheckOut() >= i) {
				System.out.print("[X]");
			}
			else {
			System.out.printf("%2d ", i);
			}
			if(i % 7 == 0)
				System.out.println();
		}	
		System.out.println();
	}
	
	
	public void RoomReservationButBetter(Hotel hotel, Reservation reservation, Room room) {
		
    //System.out.println("Welcome to " + hotel.getName());
    System.out.println("Reservation Details:");
    System.out.println("Guest Name: " + "FirstName " + "LastName");
    System.out.println("Room: " + room.getRoomName());
    
    // Display check-in and check-out dates
    System.out.println();
    System.out.println("Check-in: " + reservation.getCheckIn());
    System.out.println("Check-out: " + reservation.getCheckOut());
    
    // Print ASCII art or formatted display
    System.out.println();
    System.out.println("Here's your reservation visualized:");
    displayCalendar(reservation);
}
	public void displayRoomReservationCalendar(Room room) {
		int i;
		for(i = 1; i <= maxDay; i++) {
			System.out.printf("%2d", i);
		}	
	}
	
	
}
