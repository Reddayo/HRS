package com.hrs;

public enum DetailedViewHotelMenu {
	AVAILABILITY_DATE("No. of Available & Booked Rooms for a Selected Date"),
	ROOM_INFO("Room Information"),
	RESERVATION_INFO("Reservation Information"),
	GOBACKTOVH("Go back to previous menu");
	 private final String label;

	 DetailedViewHotelMenu(String description) {
        this.label = description;
    }

    public String getDescription() {
        return label;
    }
}
