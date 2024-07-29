package Model;

/**
 * 
 * <p>
 * The <code>ExecutiveRoom</code> represents a executive room in a hotel. 
 * This class extends the {@link Room} class and provides specific functionality for executive rooms.
 * </p>
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class ExecutiveRoom extends Room {


    private static final double BASE_RATE_MULTIPLIER = 1.35;

    /**
     * Constructs a new <code>ExecutiveRoom</code> object with the specified name and base price.
     *
     * @param name  The name of the room.
     * @param price The base price of the room.
     */
    ExecutiveRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }
    
    /**
     * Sets the price for the executive room, applying the base rate multiplier.
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
     * @return A string indicating the room type, which is "Executive".
     */
    @Override 
    public String getRoomType() {
    	return "Executive";
    }
}