package com.hrs;

import java.util.ArrayList;
import java.util.Collections;
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
		 for (int i = 1; i <= noOfRooms; i++) {
	            String roomId = String.format("%s%02d", hotelRoomPrefix, i);
	            rooms.add(new Room(roomId, price));
	        }
	        this.noOfRooms = noOfRooms;
	}
	
	
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
	
	 public void addRooms(int noOfRooms) {
	        for (int i = 0; i < noOfRooms; i++) {
	            String nextRoomId = getNextRoomId();
	            rooms.add(new Room(nextRoomId, price));
	        }
	    }
	 
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
	   
	   public void roomRemover(int count) {
		   for(int i = 0; i < rooms.size(); i++) {
			   Room room = rooms.get(i);
			   if (room.getReservations().isEmpty()) {
				   rooms.remove(i);
				   count--;
		 
			   }
		    if(count == 0) {
		    	 noOfRooms = rooms.size();
		    	return;
		    }
		    
		   }
		  
		   
	   }
	
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
	   
	   
	   public boolean reservationIDChecker(String reservationID) {
		   
		   for(Reservation r: reservations) {
			   if(r.getReservationID() == reservationID) {
				   return true;
			   }
		   }
		   return false;
	   }
	
	   
	   
	   public Reservation rFinder(String reservationID) {
		   Reservation ra = null;
		   for(Reservation r: reservations) {
			   if(r.getReservationID() == reservationID) {
				  ra = r;
			   }
		   }
		   return ra;
	   }
	
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
