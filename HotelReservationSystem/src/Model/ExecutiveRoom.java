package Model;

public class ExecutiveRoom extends Room {


    private static final double BASE_RATE_MULTIPLIER = 1.35;

    ExecutiveRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }

    @Override
    public void setRoomPrice(double roomPrice) {
        super.setRoomPrice(roomPrice*BASE_RATE_MULTIPLIER);
    }
}