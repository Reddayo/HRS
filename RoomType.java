package Model;

public enum RoomType {
    STANDARD(1.0),
    DELUXE(1.20),
    EXECUTIVE(1.35);

    private final double base_rate_multiplier;

    RoomType(double base_rate_multiplier){
        this.base_rate_multiplier = base_rate_multiplier;
    }

    public double getBaseRateMultiplier(){
        return base_rate_multiplier;
    }

    @Override
    public String toString(){
        return this.name();
    }
}
