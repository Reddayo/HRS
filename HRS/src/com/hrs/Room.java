package com.hrs;


import java.util.ArrayList;
public class Room {
	private String name;
	private ArrayList<Reservation> reservation;
	private boolean reservationStatus;
	
	Room(String name){
		this.name = name;
		this.reservation = new ArrayList<Reservation>();
		this.reservationStatus = false;
	}
	public void setReservationStatus(boolean reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public boolean getReservationStatus() {
		return this.reservationStatus;
	}
	public int getReservationSize() {
		return reservation.size();
	}
	
	public String availability() {
		return "1, 2, 3, 4, 5";
	}
	public String getRoomName() {
		return this.name;
	}
}