 package com.hrs;

import java.util.Scanner;
/**
 * This is the main class that initializes the Hotel Reservation System (HRS)
 * and starts the menu for user interaction.
 *
 * @author Red
 * @version 3.0
 */
public class Main {
	 /**
     * The main method that starts the hotel reservation system.
     *
     * @param args The command-line arguments (not used in this application).
     */
	public static void main (String[] args) {
		HotelReservationSystem HRS = new HotelReservationSystem();
		Scanner scn = new Scanner(System.in);
		Menu menu = new Menu(HRS, scn);
		menu.start();
	}
}
