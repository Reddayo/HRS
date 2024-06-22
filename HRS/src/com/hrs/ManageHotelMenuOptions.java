package com.hrs;

public enum ManageHotelMenuOptions {

	CHANGEHOTELNAME("Change Hotel"),
	ADD_ROOMS("Add Room(s)"),
	REMOVE_ROOMS("Remove Room(s)"),
	CHANGEROOMPRICE("Change Room Price"),
	REMOVERESERVATION("Remove Reservation"),
	REMOVEHOTEL("Remove Hotel"),
	EXITM("Exit");
	private String label;
	ManageHotelMenuOptions(String description){
		this.label = description;
	}
	
	public String getDescription() {
		return label;
	}
	
}
