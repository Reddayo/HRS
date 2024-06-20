package com.hrs;

public enum MainMenuOptions {
	CREATE_HOTEL("Create Hotel"),
	VIEW_HOTEL("View Hotel"),
	MANAGE_HOTEL("Manage Hotel"),
	SIMULATE_BOOKING("Simulate Booking"),
	EXIT("Exit");
	 private final String label;

    MainMenuOptions(String description) {
        this.label = description;
    }

    public String getDescription() {
        return label;
    }
}