package com.hrs;

import java.util.ArrayList;
public class Hotel {
	private String name;
	private ArrayList<Room> rooms;
	private int noOfRooms;
	private double price;
	private ArrayList<Reservation> reservations;
	private String hotelRoomPrefix;
	
	Hotel(String name, int noOfRooms, String hotelRoomPrefix){
		this.name = name;
		this.rooms = new ArrayList<Room>();
		this.noOfRooms = noOfRooms;
		this.price = 1299.00;
		this.reservations = new ArrayList<Reservation>();
		this.hotelRoomPrefix = hotelRoomPrefix;
		
		roomsInitializer(noOfRooms);
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	public Room getRoom(int index) {
		return rooms.get(index);
	}
	public void roomsInitializer(int noOfRooms) {
		for(int i = 1; i <= noOfRooms; i++) {
			rooms.add(new Room(hotelRoomPrefix + i));
		}
	}
	
	
	public double getEstimate() {
		return price * reservations.size();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	/**
	 * @return the noOfRooms
	 */
	public int getNoOfRooms() {
		return noOfRooms;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @return the reservation
	 */
	public ArrayList<Reservation> getReservation() {
		return reservations;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	/**
	 * @param noOfRooms the noOfRooms to set
	 */
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @param reservation the reservation to set
	 */
	public void setReservation(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}
}
