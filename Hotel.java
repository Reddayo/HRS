package Model;


import java.util.ArrayList;
import java.util.Collections;

/**
 * The <code>Hotel</code> class represents a hotel entity with rooms and reservations.
 * It manages rooms, reservations, and provides various operations related to rooms
 * and reservations within the hotel.
 */
public class Hotel {

    private final static int MAXNOOFROOMS = 50;
	/**
	 * The name of the hotel.
	 */
	private String name;
	/**
	 * Prefix for hotel room identifiers.
	 */
	private final String hotelRoomPrefix; 
	/**
	 * The list of rooms
	 */
	private ArrayList<Room> rooms;
	/**
	 * The price per room
	 */
	private double price;
	/**
	 * The reservation list for all rooms
	 */
	private ArrayList<Reservation> reservations;

	//private int noOfRooms;
	
	/**
     * Constructs a new <code>Hotel</code> object with the given name, number of rooms,
     * and hotel room prefix.
     *
     * @param name            The name of the hotel.
     * @param noOfRooms       The initial number of rooms in the hotel.
     * @param hotelRoomPrefix The prefix for generating room IDs.
     */
	Hotel(String name, double price, int standard, int deluxe, int executive, String hotelRoomPrefix){

        if(standard + deluxe + executive == 0){
            throw new IllegalArgumentException("Total No. of Rooms cannot be 0");
        }else if(standard + deluxe + executive < 0){
            throw new IllegalArgumentException("Total No. Rooms cannot be less than 0");
        }else if(standard + deluxe + executive > MAXNOOFROOMS ){
            throw new IllegalArgumentException("Total No. Rooms cannot be greater than 0");
        }
    
    	this.name = name;
		this.rooms = new ArrayList<Room>();
        this.reservations = new ArrayList<Reservation>();
		this.price = price;
		
		this.hotelRoomPrefix = hotelRoomPrefix;
		
		roomsInitializer(standard, deluxe, executive);
	}
	
	
	public int getNoOfStandardRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room.getRoomType() == RoomType.STANDARD) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int getNoOfDeluxeRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room.getRoomType() == RoomType.DELUXE) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int getNoOfExecutiveRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room.getRoomType() == RoomType.EXECUTIVE) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int getNoOfRooms() {
		return rooms.size();
	}
	/**
	 * Sorts rooms by the room number
	 */
	private void sortRoomsByRoomNumber() {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                Room room1 = rooms.get(i);
                Room room2 = rooms.get(j);
                
                int number1 = extractRoomNumber(room1.getRoomName());
                int number2 = extractRoomNumber(room2.getRoomName());
                
                if (number1 > number2) {
                    
                    Room temp = rooms.get(i);
                    rooms.set(i, rooms.get(j));
                    rooms.set(j, temp);
                }
            }
        }
    }
    /**
	 * Extracts the room number from the room name
	 *
	 * @param roomId the room name to extract from
	 * 
	 * @return roomNumber
	 */
	private int extractRoomNumber(String roomId) {
        // Assuming roomId format is "prefixXX", where XX is the numeric part
        String numberPart = roomId.substring(hotelRoomPrefix.length());
        return Integer.parseInt(numberPart);
    }
	 
	/**
	 * Initializes rooms in the hotel with the given number.
	 *
	 * @param noOfRooms The number of rooms to initialize.
	 */
	private void roomsInitializer(int standard, int deluxe, int executive) {
		 for (int i = 1; i <= standard; i++) {
	            String roomId = String.format("%s%02d", hotelRoomPrefix, i+rooms.size());
	            rooms.add(new Room(roomId, price, RoomType.STANDARD));
	     }
		 
		 for (int i = 1; i <= deluxe; i++) {
	            String roomId = String.format("%s%02d", hotelRoomPrefix, i+rooms.size());
	            rooms.add(new Room(roomId, price, RoomType.DELUXE));
	     }
		 for (int i = 1; i <= executive; i++) {
	            String roomId = String.format("%s%02d", hotelRoomPrefix, i+rooms.size());
	            rooms.add(new Room(roomId, price, RoomType.EXECUTIVE));
	     }
	        
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
	 * Adds a specified number of rooms to the hotel.
	 *
	 * @param noOfRooms The number of rooms to add.
	 */
	public void addRooms(int noOfRooms, RoomType roomType) {
	    for (int i = 1; i <= noOfRooms; i++) {
	        String nextRoomId = getNextRoomId();
	        rooms.add(new Room(nextRoomId, price, roomType));
	    }
	    sortRoomsByRoomNumber();
	}

	/**
     * Adds a reservation to the hotel.
     *
     * @param reservation The reservation to add.
     */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		Room room = reservation.getRoom();
		room.addReservation(reservation);
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
	 * Counts and returns the number of rooms in the hotel that can be removed (have no reservations).
	 *
	 * @return The number of removable rooms.
	 */
	   public int removableRooms() {
			int i = 0;
			for (Room room : rooms) {
			    if (room.getReservation(0) != null) {
			    
			        	i++;
			        }
			    }
			    return i;
		}
	/**
	 * Removes a specified number of rooms from the hotel that have no reservations.
	 *
	 * @param count The number of rooms to remove.
	 */
	public void roomRemover(int count) {
		   for(int i = rooms.size()-1; i >= 0 && count > 0; i--) {
			   Room room = rooms.get(i);
			   if (room.getReservation(0) != null) {
				   rooms.remove(i);
				   count--;
				   
			   }
		   }
	   }
	/**
	 * Checks if a reservation ID exists in the hotel's reservations.
	 *
	 * @param reservationID The reservation ID to check.
	 * @return <code>true</code> if the reservation ID exists, otherwise <code>false</code>.
	 */
   public boolean checkReservationID(String reservationID) {
	   
	   for(Reservation r: reservations) {
		   if(r.getReservationID() == reservationID) {
			   return true;
		   }
	   }
	   return false;
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
	 * Finds a reservation object by its reservation ID.
	 *
	 * @param reservationID The reservation ID to find.
	 * @return The reservation object if found, otherwise <code>null</code>.
	 */
   public Reservation findReservation(String reservationID) {
	   Reservation ra = null;
	   for(Reservation r: reservations) {
		   if(r.getReservationID().equals(reservationID)) {
			  ra = r;
		   }
	   }
	   return ra;
   }
   /**
	 * Retrieves the number of available rooms in the hotel on a specified day.
	 *
	 * @param day The day to check availability.
	 * @return The number of available rooms.
	 */
   public int getNoOfAvailableRooms(int day) {
	   int ctr = 0;
	   for (int i = 0; i < rooms.size(); i++) {
		    Room room = rooms.get(i);
		    boolean isRoomAvailable = true;

		    for (int j = 0; j < room.getReservationSize() && isRoomAvailable; j++) {
		        Reservation reservation = room.getReservation(j);
		        if (day >= reservation.getCheckIn() && day < reservation.getCheckOut()) {
		            
    			isRoomAvailable = false;
    			}
			}

			if (isRoomAvailable) {
				ctr++; 
		    }
		}
	   return ctr;
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
            int numberOfNights = checkOutDay - checkInDay;
            double reservationPrice = numberOfNights * price;
            totalEstimate += reservationPrice;
        }
        return totalEstimate;
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
	 * @return the prefix of rooms
	 */
   public String getRoomPrefix() {
		return this.hotelRoomPrefix;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
		for(Room room: rooms){
			room.setRoomPrice(price);
		}
	}

	public Reservation getReservation(int i){
		return reservations.get(i);
	}

	public int getReservationSize(){
		return reservations.size();
	}

	public int getRoomSize() {
		return rooms.size();
	}
}