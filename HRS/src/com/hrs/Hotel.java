package com.hrs;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The <code>Hotel</code> class represents a hotel entity with rooms and reservations.
 * It manages rooms, reservations, and provides various operations related to rooms
 * and reservations within the hotel.
 */
public class Hotel {
	private String name;
	private String hotelRoomPrefix;
	private ArrayList<Room> rooms;
	private int noOfRooms;
	private double price;
	private ArrayList<Reservation> reservations;
	
	/**
     * Constructs a new <code>Hotel</code> object with the given name, number of rooms,
     * and hotel room prefix.
     *
     * @param name            The name of the hotel.
     * @param noOfRooms       The initial number of rooms in the hotel.
     * @param hotelRoomPrefix The prefix for generating room IDs.
     */
	Hotel(String name, int noOfRooms, String hotelRoomPrefix){
		this.name = name;
		this.rooms = new ArrayList<Room>();
		this.noOfRooms = noOfRooms;
		this.price = HotelReservationSystem.defaultPrice;
		this.reservations = new ArrayList<Reservation>();
		this.hotelRoomPrefix = hotelRoomPrefix;
		
		roomsInitializer(noOfRooms);
	}
	 /**
     * Adds a reservation to the hotel.
     *
     * @param reservation The reservation to add.
     */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	 /**
     * Retrieves a room by its index.
     *
     * @param index The index of the room to retrieve.
     * @return The room at the specified index.
     */
	public Room getRoom(int index) {
		return rooms.get(index);
	}
	 /**
     * Initializes rooms in the hotel with the given number.
     *
     * @param noOfRooms The number of rooms to initialize.
     */
	public void roomsInitializer(int noOfRooms) {
		 for (int i = 1; i <= noOfRooms; i++) {
	            String roomId = String.format("%s%02d", hotelRoomPrefix, i);
	            rooms.add(new Room(roomId, price));
	        }
	        this.noOfRooms = noOfRooms;
	}
	 /**
     * Finds a room in the hotel by its room ID.
     *
     * @param roomToFind The room ID to find.
     * @return The room object if found, otherwise <code>null</code>.
     */
	public Room findRoom(String roomToFind) {
		for(Room room: rooms)
			if(roomToFind.equals(room.getRoomName())) {
				return room;
			}
		return null;
	} 
	/**
     * Calculates the total estimated revenue from all reservations in the hotel.
    *
    * @return The total estimated revenue.
    */
	 public double getEstimate() {
	        double totalEstimate = 0.0;
	        for (Reservation reservation : reservations) {
	            int checkInDay = reservation.getCheckIn();
	            int checkOutDay = reservation.getCheckOut();

	            // Calculate number of nights stayed
	            int numberOfNights = checkOutDay - checkInDay;

	            // Calculate total price for the reservation
	            double reservationPrice = numberOfNights * price;
	            totalEstimate += reservationPrice;
	        }
	        return totalEstimate;
	    }
	 /**
	     * Adds a specified number of rooms to the hotel.
	     *
	     * @param noOfRooms The number of rooms to add.
	     */
	 public void addRooms(int noOfRooms) {
	        for (int i = 1; i <= noOfRooms; i++) {
	            String nextRoomId = getNextRoomId();
	            rooms.add(new Room(nextRoomId, price));
	        }
	       this.noOfRooms += noOfRooms;
	    }
	 /**
	     * Generates the next available room ID based on existing rooms.
	     *
	     * @return The next available room ID.
	     */
	   private String getNextRoomId() {
	      
	        ArrayList<Integer> roomNumbers = new ArrayList<>();
	        for (Room room : rooms) {
	            String numberPart = room.getRoomName().substring(hotelRoomPrefix.length());
	            roomNumbers.add(Integer.parseInt(numberPart));
	        }
	        Collections.sort(roomNumbers);

	        int nextNumber = 1;
	        for (int number : roomNumbers) {
	            if (number != nextNumber) {
	                break;
	            }
	            nextNumber++;
	        }

	        return String.format("%s%02d", hotelRoomPrefix, nextNumber);
	    }

	   /**
	     * Removes a room at the specified index from the hotel.
	     *
	     * @param index The index of the room to remove.
	     * @throws IllegalArgumentException If the room index is invalid or has reservations.
	     */
	   public void removeRoom(int index) {
		    if (index < 0 || index >= rooms.size()) {
		        throw new IllegalArgumentException("Invalid room index");
		    }
		    Room room = rooms.get(index);
		    if (room.getReservations().isEmpty()) {
		        rooms.remove(index);
		    } else {
		        throw new IllegalArgumentException("Room cannot be removed because it has reservations");
		    }
		}
	   /**
	     * Removes a specified number of rooms from the hotel that have no reservations.
	     *
	     * @param count The number of rooms to remove.
	     */
	   public void roomRemover(int count) {
		   for(int i = 0; i < rooms.size() && count > 0; i++) {
			   Room room = rooms.get(i);
			   if (room.getReservations().isEmpty()) {
				   rooms.remove(i);
				   count--;
				   i--;
			   }
		    if(count == 0) {
		    	this.noOfRooms = rooms.size();
		    	return;
		    }
		   }
	   }
	   
	   /**
	     * Finds an available room in the hotel for a specified check-in and check-out day.
	     *
	     * @param checkInDay  The check-in day.
	     * @param checkOutDay The check-out day.
	     * @return The available room object if found, otherwise <code>null</code>.
	     */
	   public Room findAvailableRoom(int checkInDay, int checkOutDay) {
	        for (Room room : rooms) {
	            if (room.isAvailable(checkInDay, checkOutDay)) {
	                return room;
	            }
	        }
	        return null; // No available room found
	    }
	   
	   /**
	     * Retrieves the number of available rooms in the hotel on a specified day.
	     *
	     * @param day The day to check availability.
	     * @return The number of available rooms.
	     */
	   public int getNoOfAvailableRooms(int day) {
		   int ctr = 0;
		   for (int i = 0; i < noOfRooms; i++) {
			    Room room = rooms.get(i);
			    boolean isRoomAvailable = true;

			    for (int j = 0; j < room.getReservationSize() && isRoomAvailable; j++) {
			        Reservation reservation = room.getReservation(j);
			        if (day >= reservation.getCheckIn() && day < reservation.getCheckOut()) {
			            // If date is between check-in and check-out dates of any reservation, room is not available
			            isRoomAvailable = false;
			            // No need to check further reservations for this room
			        }
			    }

			    if (isRoomAvailable) {
			        ctr++; // Increment counter for available rooms
			    }
			}
		   return ctr;
	   }
	   /**
	     * Removes a range of rooms from the hotel based on start and end indices,
	     * provided they have no reservations.
	     *
	     * @param startIndex The starting index of rooms to remove.
	     * @param endIndex   The ending index of rooms to remove.
	     * @throws IllegalArgumentException If the start or end index is invalid or rooms have reservations.
	     */
	   public void removeRooms(int startIndex, int endIndex) {
		    if (startIndex < 0 || endIndex >= rooms.size() || startIndex > endIndex) {
		        throw new IllegalArgumentException("Invalid start or end index");
		    }
		    for (int i = endIndex; i >= startIndex; i--) {
		        Room room = rooms.get(i);
		        if (room.getReservations().isEmpty()) {
		            rooms.remove(i);
		        } else {
		            throw new IllegalArgumentException("Room at index " + i + " has reservations and cannot be removed");
		        }
		    }
		}
	   /**
	     * Counts and returns the number of rooms in the hotel that can be removed (have no reservations).
	     *
	     * @return The number of removable rooms.
	     */
	   public int removableRooms() {
		    //System.out.println("Rooms without reservations:");
		    int i = 0;
		    for (Room room : rooms) {
		        if (room.getReservations().isEmpty()) {
		            //ystem.out.println(room.getRoomName());
		        	i++;
		        }
		    }
		    return i;
		}
	   
	   /**
	     * Checks if a reservation ID exists in the hotel's reservations.
	     *
	     * @param reservationID The reservation ID to check.
	     * @return <code>true</code> if the reservation ID exists, otherwise <code>false</code>.
	     */
	   public boolean reservationIDChecker(String reservationID) {
		   
		   for(Reservation r: reservations) {
			   if(r.getReservationID() == reservationID) {
				   return true;
			   }
		   }
		   return false;
	   }
	   /**
	     * Finds a reservation object by its reservation ID.
	     *
	     * @param reservationID The reservation ID to find.
	     * @return The reservation object if found, otherwise <code>null</code>.
	     */
	   public Reservation rFinder(String reservationID) {
		   Reservation ra = null;
		   for(Reservation r: reservations) {
			   if(r.getReservationID().equals(reservationID)) {
				  ra = r;
			   }
		   }
		   return ra;
	   }
	   /**
	     * Removes a reservation from the hotel, including its associated room and reservation list.
	     *
	     * @param someRes The reservation to remove.
	     */
	   public void removeReservation(Reservation someRes) {
		   
		   Room room = someRes.getRoom();
		   room.removeReservation(someRes);
		   reservations.remove(someRes);
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
