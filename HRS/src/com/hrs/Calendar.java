package com.hrs;

public class Calendar {
	private int maxDay;

	Calendar(){
		this.maxDay = 31;
		
	}
	
	public void displayCalendar() {
		int i;
		for(i = 1; i <= maxDay; i++) {
			System.out.printf("%2d", i);
		}	
	}
	
	public void displayRoomReservationCalendar(Room room) {
		int i;
		for(i = 1; i <= maxDay; i++) {
			System.out.printf("%2d", i);
		}	
	}
	
	
}
