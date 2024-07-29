package Model;

/**
 * <p>
 * The <code>StandardRoom</code> represents a standard room in a hotel. 
 * This class extends the {@link Room} class  and provides specific functionality for standard rooms.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class StandardRoom extends Room {
    
 
    private static final double BASE_RATE_MULTIPLIER = 1.00;
    
    /**
     * Constructs a new <code>StandardRoom</code> object with the specified name and base price.
     *
     * @param name  The name of the room.
     * @param price The base price of the room.
     */
    StandardRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }

    
    /**
     * Sets the price for the standard room, applying the base rate multiplier.
     *
     * @param roomPrice The base price to be set.
     */
    @Override
    public void setRoomPrice(double roomPrice) {
        super.setRoomPrice(roomPrice*BASE_RATE_MULTIPLIER);
    }
    
    /**
     * Returns the type of the room as a string.
     *
     * @return A string indicating the room type, which is "Standard".
     */
    @Override 
    public String getRoomType() {
    	return "Standard";
    }
}

