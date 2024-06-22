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
				System.out.print(ANSI_BLUE);
			}
			
			System.out.printf("%2d ", i);
			System.out.print(ANSI_RESET);
			if(i % 7 == 0)
				System.out.println();
		}	
		System.out.println();
	}
	
	
	public void displayRoomReservationCalendar(Room room) {
		int i;
		for(i = 1; i <= maxDay; i++) {
			System.out.printf("%2d", i);
		}	
	}
	
	
}
