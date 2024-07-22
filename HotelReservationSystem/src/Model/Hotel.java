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

	private double[] datePriceModifier;
	
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


	public double[] getDatePriceModifier(){
		return datePriceModifier;
	}
	
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
		this.datePriceModifier = new double[30];
		
		this.hotelRoomPrefix = hotelRoomPrefix;
		
		roomsInitializer(standard, deluxe, executive);
		priceInitializer();
	}
	
	private void priceInitializer(){
		for(int i = 0; i < 30; i++){
			datePriceModifier[i] = 1.00;
		}
	}
	
	public int getNoOfStandardRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof StandardRoom) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int getNoOfDeluxeRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof DeluxeRoom) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int getNoOfExecutiveRooms() {
		int count = 0;
	    for (Room room : rooms) {
	        if (room instanceof ExecutiveRoom) {
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
		int roomCount = rooms.size(); // Start counting from the existing number of rooms
	
		for (int i = 1; i <= standard; i++) {
			String roomId = String.format("%s%02d", hotelRoomPrefix, i + roomCount);
			rooms.add(new StandardRoom(roomId, price));
		}
	
		roomCount += standard; // Increment the count by the number of standard rooms added
	
		for (int i = 1; i <= deluxe; i++) {
			String roomId = String.format("%s%02d", hotelRoomPrefix, i + roomCount);
			rooms.add(new DeluxeRoom(roomId, price));
		}
	
		roomCount += deluxe; // Increment the count by the number of deluxe rooms added
	
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

	public void addStandardRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new StandardRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }

    public void addDeluxeRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new DeluxeRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }

    public void addExecutiveRooms(int count) {
        for (int i = 0; i < count; i++) {
			String nextRoomId = getNextRoomId();
            rooms.add(new ExecutiveRoom(nextRoomId, price));
        }
		sortRoomsByRoomNumber();
    }

	/**
     * Adds a reservation to the hotel.
     *
     * @param reservation The reservation to add.
     */
	public void addReservation(String guestName, String roomType, int checkIn, int checkOut, String discount) {
		
	   Room room = findAvailableRoom(checkIn, checkOut, roomType);
		
	   if(room == null) {
		   throw new IllegalArgumentException ("There are no more " + roomType +  " rooms");
	   }
		
		Reservation res = new Reservation(guestName, checkIn, checkOut, room, discount, datePriceModifier);
		reservations.add(res);
		room.addReservation(res);
	}
	/**
	  * Removes a reservation from the hotel, including its associated room and reservation list.
	  *
	  * @param someRes The reservation to remove.
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
	 * @return The number of available rooms.
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
			System.out.println("New Price: " + price);
			room.setRoomPrice(price);
		}
		for(Reservation res: reservations) {
			res.recalculatePrice(datePriceModifier);
			System.out.println("New Price: " + price);
		}
		
		for(int i = 0; i < datePriceModifier.length; i++) {
			System.out.println(datePriceModifier[i]);
		}
	}

	public String getReservationID(int i){
	
		
		return reservations.get(i).getReservationID();
	}

	public int getReservationSize(){
		return reservations.size();
	}

	public int getRoomSize() {
		return rooms.size();
	}
	
	public String getRoomName(int i) {
		return rooms.get(i).getRoomName();
	}
	
	
	public ArrayList<String> getRoomList() {
		ArrayList<String> roomList = new ArrayList<String>();
		
		for(int i = 0; i < rooms.size(); i++) {
			roomList.add(rooms.get(i).getRoomName());
		}
		return roomList;
	}
	
	public ArrayList<String> getReservationist() {
		ArrayList<String> reservationList = new ArrayList<String>();
		
		for(int i = 0; i < reservations.size(); i++) {
			reservationList.add(reservations.get(i).getReservationID());
		}
		return reservationList;
	}

	public String getReservationGuestName(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getGuestName();
	}

	public String getReservationRoomName(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getRoomName();
	}

	public int getReservationCheckIn(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getCheckIn();		
	}

	public int getReservationCheckOut(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getCheckOut();		
	}

	public double getReservationTotalPrice(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getTotalPrice();		
	}

	public String getReservationDiscount(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getDiscount();		
	}

	public double[] getReservationBreakdown(String reservationID) {
		Reservation res = findReservation(reservationID);
		
		return res.getBreakdown();		
	}

	public double getRoomBaseRate(String selectedRoom) {
		Room room = findRoom(selectedRoom);
		return room.getRoomBaseRate();
	}

	public boolean[] getAvailability(String selectedRoom) {
		Room room = findRoom(selectedRoom);
		return room.getAvailability();
	}
	
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
	    
	    
	    for(int i = 0; i < listOfRoomsAndType.size(); i++) {
	    	System.out.println(listOfRoomsAndType.get(i));
	    }
	    
	    return listOfRoomsAndType;
	    
	    
	}

	public void removeRoom(String roomName) {
		Room room = findRoom(roomName);
		rooms.remove(room);
	}

}