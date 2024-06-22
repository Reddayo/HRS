package com.hrs;

public class TestMain {
	
	
	
	public static void main (String[] args) {
		
		
		Calendar calendar = new Calendar();
		Hotel hotel = new Hotel("Sunset Hotel", 10, "A");
		Room room = new Room("A1", 100);
		Reservation reservation = new Reservation("First name"+"Last name", 1, 31, room);
		Reservation reservation1 = new Reservation("First name"+"Last name", 10, 17, room);
		//System.out.println("Normal Calendar");
		//calendar.displayCalendar();
		
		//System.out.println("Reservation for first name last name in Sunset Hotel in Room A1");
		
		calendar.RoomReservationButBetter(hotel, reservation1, room);
		System.out.println();
		
		
		for(int i = 0; i < 10000; i++) {
			System.out.printf("%c", i);
			if(i % 100 == 0) {
				System.out.println();
			}
		}
	
	}
	
}
