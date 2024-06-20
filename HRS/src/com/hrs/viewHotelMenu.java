package com.hrs;

public enum viewHotelMenu {
	OVERVIEW("Overview information"),
	DETAILED("Detailed information"),
	EXITV("3. Exit");
	
	 private final String label;

    viewHotelMenu(String description) {
        this.label = description;
    }

    public String getDescription() {
        return label;
    }
}
