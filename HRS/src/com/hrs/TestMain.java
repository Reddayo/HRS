package com.hrs;

public class TestMain {
	
	
	
	public static void main (String[] args) {
		
		
		Calendar calendar = new Calendar();
		Room room = new Room("A1");
		Reservation reservation = new Reservation("First name"+"Last name", 1, 31, room);
		System.out.println("Normal Calendar");
		calendar.displayCalendar();
		
		System.out.println("Reservation for first name last name in Sunset Hotel in A1");
		calendar.displayCalendar(reservation);


	}
}
