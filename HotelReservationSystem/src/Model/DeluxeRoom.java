package Model;

public class DeluxeRoom extends Room {

    
    private static final double BASE_RATE_MULTIPLIER = 1.20;

    DeluxeRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }

    @Override
    public void setRoomPrice(double roomPrice) {
        super.setRoomPrice(roomPrice*BASE_RATE_MULTIPLIER);
    }
    @Override 
    public String getRoomType() {
    	return "Deluxe";
    }
}
