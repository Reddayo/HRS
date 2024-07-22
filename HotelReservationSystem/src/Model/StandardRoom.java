package Model;

public class StandardRoom extends Room {
    
    //Why aren't my room protected? i already got them initialized using super,
    //and I have the appropriate setters and getters for it
    //I don't need to extend this but for the sake of making it an instance of Room
    private static final double BASE_RATE_MULTIPLIER = 1.00;

    StandardRoom(String name, double price){

        super(name, price);

        super.setRoomPrice(price * BASE_RATE_MULTIPLIER);
    }


    @Override
    public void setRoomPrice(double roomPrice) {
        super.setRoomPrice(roomPrice*BASE_RATE_MULTIPLIER);
    }
}

