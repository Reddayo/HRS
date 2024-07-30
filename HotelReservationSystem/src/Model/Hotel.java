package Model;




import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 * The <code>Hotel</code> class represents a hotel entity with rooms and reservations.
 * It manages rooms, reservations, and provides various operations related to rooms
 * and reservations within the hotel.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
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

	private double[] datePriceModifier;
	
	/**
	 * Constructs a new <code>Hotel</code> object with the specified name, room prices, 
	 * room counts for each type, and hotel room prefix.
	 *
	 * @param name            The name of the hotel.
	 * @param price           The base price for the rooms in the hotel.
	 * @param standard        The number of standard rooms to initialize.
	 * @param deluxe          The number of deluxe rooms to initialize.
	 * @param executive       The number of executive rooms to initialize.
	 * @param hotelRoomPrefix The prefix for generating room IDs.
	 * 
	 * @throws IllegalArgumentException if the total number of rooms (standard + deluxe + executive)
	 *                                  is zero, less than zero, or exceeds the maximum allowed number of rooms.
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
		this.datePriceModifier = new double[30];
		
		this.hotelRoomPrefix = hotelRoomPrefix;
		
		roomsInitializer(standard, deluxe, executive);
		priceInitializer();
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
	 * Initializes rooms in the hotel with the specified numbers for each room type.
	 *
	 * @param standard The number of standard rooms to initialize.
	 * @param deluxe The number of deluxe rooms to initialize.
	 * @param executive The number of executive rooms to initialize.
	 * 
	 * This method creates and adds rooms to the hotel's list of rooms, with unique
	 * room IDs based on the existing number of rooms and the hotel room prefix.
	 */
	private void roomsInitializer(int standard, int deluxe, int executive) {
		int roomCount = rooms.size();
	
		for (int i = 1; i <= standard; i++) {
			String roomId = String.format("%s%02d", hotelRoomPrefix, i + roomCount);
			rooms.add(new StandardRoom(roomId, price));
		}
	
		roomCount += standard; 
	
		for (int i = 1; i <= deluxe; i++) {
			String roomId = String.format("%s%02d", hotelRoomPrefix, i + roomCount);
			rooms.add(new DeluxeRoom(roomId, price));
		}
	
		roomCount += deluxe; 
	
		for (int i = 1; i <= executive; i++) {
			String roomId = String.format("%s%02d", hotelRoomPrefix, i + roomCount);
			rooms.add(new ExecutiveRoom(roomId, price));
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
	 * Adds a reservation to the hotel for the specified guest, room type, and dates.
	 *
	 * @param guestName The name of the guest making the reservation.
	 * @param roomType  The type of room to be reserved (e.g., "Standard", "Deluxe", "Executive").
	 * @param checkIn   The check-in date (as an integer, typically representing the day of the month).
	 * @param checkOut  The check-out date (as an integer, typically representing the day of the month).
	 * @param discount  The discount code to apply to the reservation, if any.
	 * 
	 * @throws IllegalArgumentException if there are no available rooms of the specified type
	 *                                  for the given dates.
	 */
	public void addReservation(String guestName, String roomType, int checkIn, int checkOut, String discount) {
		
	   Room room = findAvailableRoom(checkIn, checkOut, roomType);
		
	   if(room == null) {
		   throw new IllegalArgumentException ("There are no " + roomType +  " rooms available");
	   }
	   
	   
		
		Reservation res = new Reservation(guestName, checkIn, checkOut, room, discount, datePriceModifier);
		reservations.add(res);
		room.addReservation(res);
	}
	/**
	  * Removes a reservation from the hotel, including its associated room and reservation list.
	  *
	  * @param reservationID The reservation to remove.
	  */
	public void removeReservation(String reservationID) {
	
		
			if(reservationID == null) {
				System.out.println("ID is null");
				return;
			}
			Reservation res = findReservation(reservationID);
			
			  reservations.remove(res);
			  Room room = res.getRoom();
			  room.removeReservation(res);
		
	   }
	/**
	 * Counts and returns the number of rooms in the hotel that can be removed (have no reservations).
	 *
	 * @return The number of removable rooms.
	 */
	   public int removableRooms() {
			int i = 0;
			for (Room room : rooms) {
			    if (room.getReservationSize() == 0) {
			    
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
	* @param roomType    the room type of available room to find
	* @return The available room object if found, otherwise <code>null</code>.
	*/
   private Room findAvailableRoom(int checkInDay, int checkOutDay, String roomType) {
	   
	   Class<? extends Room > roomTypeClass = null;
	   if(roomType.equals("Standard")) {
		   roomTypeClass = StandardRoom.class;
	   }else if(roomType.equals("Deluxe")) {
		   roomTypeClass = DeluxeRoom.class;
	   }else if(roomType.equals("Executive")) {
		   roomTypeClass = ExecutiveRoom.class;
	   }
	   
	   if(roomTypeClass == null) {
		   return null;
	   }
	   
	   
        for (Room room : rooms) {
        	
            if (roomTypeClass.isInstance(room) && room.isAvailable(checkInDay, checkOutDay)) {
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
   private Room findRoom(String roomToFind) {
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
   private Reservation findReservation(String reservationID) {
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
	 * @return The number of available rooms in an array, from standard to executive 0 to 2.
	 */
	public int[] getNoOfAvailableRooms(int day) {
		int[] availableRooms = new int[3]; // Index 0: Standard, 1: Deluxe, 2: Executive
	
		for (Room room : rooms) {
			boolean isRoomAvailable = true;
	
			for (int j = 0; j < room.getReservationSize() && isRoomAvailable; j++) {
				Reservation reservation = room.getReservation(j);
				if (day >= reservation.getCheckIn() && day < reservation.getCheckOut()) {
					isRoomAvailable = false;
				}
			}
	
			if (isRoomAvailable) {
				if (room instanceof StandardRoom) {
					availableRooms[0]++;
				} else if (room instanceof DeluxeRoom) {
					availableRooms[1]++;
				} else if (room instanceof ExecutiveRoom) {
					availableRooms[2]++;
				}
			}
		}
	
		return availableRooms;
	}
	
   /**
    * Calculates the total estimated revenue from all reservations in the hotel.
	*
	* @return The total estimated revenue.
	*/
	 public double getEstimate() {
        double totalEstimate = 0.0;
        for (Reservation reservation : reservations) {
            totalEstimate += reservation.getTotalPrice();
        }
        return totalEstimate;
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
	public double getBasePrice() {
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
			//System.out.println("New Price: " + price);
			room.setRoomPrice(price);
		}
		for(Reservation res: reservations) {
			res.recalculatePrice(datePriceModifier);
			//System.out.println("New Price: " + price);
		}
		
	}
	
	/**
	 * Retrieves the reservation ID at the specified index.
	 *
	 * @param i The index of the reservation in the list.
	 * @return The reservation ID.
	 */
	public String getReservationID(int i){
	
		
		return reservations.get(i).getReservationID();
	}
	
	/**
	 * Returns the total number of reservations.
	 *
	 * @return The number of reservations.
	 */
	public int getReservationSize(){
		return reservations.size();
	}
	
	/**
	 * Returns the total number of rooms.
	 *
	 * @return The number of rooms.
	 */
	public int getRoomSize() {
		return rooms.size();
	}
	
	/**
	 * Retrieves the name of the room at the specified index.
	 *
	 * @param i The index of the room in the list.
	 * @return The room name.
	 */
	public String getRoomName(int i) {
		return rooms.get(i).getRoomName();
	}
	
	/**
	 * Returns a list of all room names.
	 *
	 * @return An ArrayList of room names.
	 */
	public ArrayList<String> getRoomList() {
		ArrayList<String> roomList = new ArrayList<String>();
		
		for(int i = 0; i < rooms.size(); i++) {
			roomList.add(rooms.get(i).getRoomName());
		}
		return roomList;
	}
	
	/**
	 * Returns a list of all reservation IDs.
	 *
	 * @return An ArrayList of reservation IDs.
	 */
	public ArrayList<String> getReservationist() {
		ArrayList<String> reservationList = new ArrayList<String>();
		
		for(int i = 0; i < reservations.size(); i++) {
			reservationList.add(reservations.get(i).getReservationID());
		}
		return reservationList;
	}
	
	/**
	 * Retrieves the guest name associated with the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The guest name.
	 */
	public String getReservationGuestName(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getGuestName();
	}
	
	/**
	 * Retrieves the room name associated with the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The room name.
	 */
	public String getReservationRoomName(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getRoomName();
	}
	/**
	 * Retrieves the check-in date for the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The check-in date as an integer.
	 */
	public int getReservationCheckIn(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getCheckIn();		
	}
	
	/**
	 * Retrieves the check-out date for the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The check-out date as an integer.
	 */
	public int getReservationCheckOut(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getCheckOut();		
	}

	/**
	 * Retrieves the total price for the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The total price.
	 */
	public double getReservationTotalPrice(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getTotalPrice();		
	}
	
	/**
	 * Retrieves the discount code used for the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The discount code.
	 */
	public String getReservationDiscount(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getDiscount();		
	}
	
	/**
	 * Retrieves the price breakdown for the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return An array of doubles representing the price breakdown.
	 */
	public double[] getReservationBreakdown(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getBreakdown();		
	}
	
	/**
	 * Retrieves the base rate for the specified room.
	 *
	 * @param selectedRoom The room name.
	 * @return The base rate of the room.
	 */
	public double getRoomBaseRate(String selectedRoom) {
		Room room = findRoom(selectedRoom);
		return room.getRoomBaseRate();
	}
	
	/**
	 * Retrieves the availability array for the specified room.
	 *
	 * @param selectedRoom The room name.
	 * @return A boolean array representing availability for each day.
	 */
	public boolean[] getAvailability(String selectedRoom) {
		Room room = findRoom(selectedRoom);
		return room.getAvailability();
	}
	
	/**
	 * Returns a list of all rooms with their names and types.
	 *
	 * @return An ArrayList of strings containing room names and their types.
	 */
	public ArrayList<String> getRoomNameAndType() {
		ArrayList<String> listOfRoomsAndType = new ArrayList<String>();
	    for(int i = 0; i < rooms.size(); i++) {
	    	Room room = rooms.get(i);
		    if (room instanceof StandardRoom) {
		        listOfRoomsAndType.add(String.format("%s (Standard)", room.getRoomName()));
		    } else if (room instanceof DeluxeRoom) {
		    	listOfRoomsAndType.add(String.format("%s (Deluxe)", room.getRoomName()));
		    } else if (room instanceof ExecutiveRoom) {
		    	listOfRoomsAndType.add(String.format("%s (Executive)", room.getRoomName()));
		    }
		    
	    }
	    return listOfRoomsAndType;
	    
	}
	
	/**
	 * Returns a list of removable rooms with their names and types.
	 *
	 * @return An ArrayList of strings containing removable room names and their types.
	 */
	public ArrayList<String> getRemovableRoomNameAndType() {
		ArrayList<String> listOfRoomsAndType = new ArrayList<String>();
	    for(int i = 0; i < rooms.size(); i++) {
	    	Room room = rooms.get(i);
	    	if(room.isBooked() == true ) {
	    		continue;
	    	}
		    if (room instanceof StandardRoom) {
		        listOfRoomsAndType.add(String.format("%s (Standard)", room.getRoomName()));
		    } else if (room instanceof DeluxeRoom) {
		    	listOfRoomsAndType.add(String.format("%s (Deluxe)", room.getRoomName()));
		    } else if (room instanceof ExecutiveRoom) {
		    	listOfRoomsAndType.add(String.format("%s (Executive)", room.getRoomName()));
		    }
		    
		    
	    }
	    
	    
	   
	    
	    return listOfRoomsAndType;
	    
	    
	}
	
	/**
	 * Removes the room with the specified name.
	 *
	 * @param roomName The name of the room to be removed.
	 */
	public void removeRoom(String roomName) {
		Room room = findRoom(roomName);
		rooms.remove(room);
	}
	
	/**
	 * Retrieves the type of the room with the specified name.
	 *
	 * @param roomName The room name.
	 * @return The room type.
	 */
	public String getRoomRoomType(String roomName) {
		Room room = findRoom(roomName);
		return room.getRoomType();
	}
	
	/**
	 * Retrieves the type of the room associated with the specified reservation ID.
	 *
	 * @param reservationID The reservation ID.
	 * @return The room type.
	 */
	public String getReservationRoomType(String roomName) {
		Reservation reservation = findReservation(roomName);
		return reservation.getRoom().getRoomType();
	}
	
	/**
	 * Initializes the price modifiers for each date.
	 */
	private void priceInitializer(){
		for(int i = 0; i < 30; i++){
			datePriceModifier[i] = 1.00;
		}
	}
	
	/**
	 * Returns the number of standard rooms.
	 *
	 * @return The number of standard rooms.
	 */
	public int getNoOfStandardRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof StandardRoom) {
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * Returns the number of deluxe rooms.
	 *
	 * @return The number of deluxe rooms.
	 */
	public int getNoOfDeluxeRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof DeluxeRoom) {
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * Returns the number of executive rooms.
	 *
	 * @return The number of executive rooms.
	 */
	public int getNoOfExecutiveRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof ExecutiveRoom) {
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * Updates the price modifiers for the specified dates.
	 *
	 * @param indices  The indices of the dates to update.
	 * @param priceMod The new price modifier.
	 */
	public void updateDatePriceModifier(int[] indices, double priceMod) {
		
		for (int index : indices) {
	        if (index >= 0 && index < datePriceModifier.length) {
	            datePriceModifier[index] = priceMod/100;
	        } else {
	            // Handle index out of bounds if needed
	            System.err.println("Index " + index + " is out of bounds.");
	        }
	    }
		for (Reservation reservation : reservations) {
			reservation.recalculatePrice(datePriceModifier); // You need to implement this in the Reservation class
        }
	}

	/**
	 * Retrieves the current date price modifiers.
	 *
	 * @return An array of doubles representing the date price modifiers.
	 */
	public double[] getDatePriceModifier(){
		return datePriceModifier;
	}
	
	/**
	 * Returns the total number of rooms in the hotel.
	 *
	 * @return The total number of rooms.
	 */
	public int getNoOfRooms() {
		return rooms.size();
	}
	
	/**
	 * Adds a specified number of standard rooms to the hotel.
	 *
	 * @param count The number of standard rooms to add.
	 */
	public void addStandardRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new StandardRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }
	
	/**
	 * Adds a specified number of deluxe rooms to the hotel.
	 *
	 * @param count The number of deluxe rooms to add.
	 */
    public void addDeluxeRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new DeluxeRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }
    
    /**
     * Adds a specified number of executive rooms to the hotel.
     *
     * @param count The number of executive rooms to add.
     */
    public void addExecutiveRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new ExecutiveRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }
    
    /**
     * Adds rooms to the hotel based on the specified counts for each type.
     *
     * @param scount The number of standard rooms to add.
     * @param dcount The number of deluxe rooms to add.
     * @param ecount The number of executive rooms to add.
     */
    public void addRooms(int scount, int dcount, int ecount) {
    	addStandardRooms(scount);
    	addDeluxeRooms(dcount);
    	addExecutiveRooms(ecount);
    }

}