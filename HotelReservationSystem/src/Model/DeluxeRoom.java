package Model;


/**
 * <p>
 * The <code>DeluxeRoom</code> class epresents a deluxe room in a hotel. 
 * This class extends the {@link Room} class and provides specific functionality for deluxe rooms.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class DeluxeRoom extends Room {

	/**
	 * A constant multiplier used to calculate base rates. 
	 * Deluxe Room multiplier is 1.20, meaning 20% more than the base rate.
	 */
    private static final double BASE_RATE_MULTIPLIER = 1.20;
    
    /**
     * Constructs a new <code>DeluxeRoom</code> object with the specified name and base price.
     *
     * @param name  The name of the room.
     * @param price The base price of the room.
     */
    DeluxeRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }
    
    /**
     * Sets the price for the deluxe room, applying the base rate multiplier.
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
     * @return A string indicating the room type, which is "Deluxe".
     */
    @Override 
    public String getRoomType() {
    	return "Deluxe";
    }
}
