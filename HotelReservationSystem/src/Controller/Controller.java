package Controller;


import Model.*;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller class that handles interactions between the GUI and the data model.
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class Controller implements ActionListener, ListSelectionListener, ChangeListener{
    
	/**
	 * The main GUI controller for the application, responsible for managing
	 * the overall user interface and interactions.
	 */
	private GUI_Main gui_Main;

	/**
	 * Manages the list of hotels within the application, including operations
	 * related to hotel data and interactions.
	 */
	private HotelListManager model_HRS;
    
    /**
     * Constructs a Controller with the specified model and view components.
     * 
     * @param model_HRS the model component managing hotels
     * @param gui_Main the main GUI component
     */
    public Controller(HotelListManager model_HRS, GUI_Main gui_Main){

        this.gui_Main = gui_Main;
        this.model_HRS = model_HRS;


        updateView();
        gui_Main.setCreateDialog(HotelListManager.MAX_ROOMS, 
                                 HotelListManager.defaultPrice, 
                                 HotelListManager.minimumPrice);
       
        gui_Main.setListSelectionListener(this);
        gui_Main.setChangeListener(this);
        gui_Main.getManagePanel().setListSelectionListener(this);
        setRoomSelectionListener();
        setReservationSelectionListener();
        gui_Main.setActionListener(this);
    }
    
    /**
     * Updates the hotel list in the view.
     */
    private void updateView() {
    	String shotel = gui_Main.getSelectedHotel();
    
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (int i = 0; i < model_HRS.getHotelListSize(); i++) {
            Hotel hotel = model_HRS.getHotel(i);
            listModel.addElement(hotel.getName());
           // System.out.println(hotel.getName());
        }
       
        gui_Main.updateHotelList(listModel);
        
       
        gui_Main.setSelectedHotel(shotel);
      
    }


    /**
     * Updates the room list in the view based on the selected hotel.
     */
    private void updateRoomView() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
                return;
             }


             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
            	 ArrayList<String> rooms = foundHotel.getRoomList();
                 for(String room: rooms) {
                 	
                     listModel.addElement(room);
                    // System.out.println(room);
                 }
             }
             
        
       
        gui_Main.updateRoomList(listModel);
    }
    
    
    /**
     * Updates the availability information in the view for a given day.
     * 
     * @param day the day for which to update availability information
     */
    private void updateAvailability(int day) {
        

        String selectedHotel = gui_Main.getSelectedHotel();
        if(selectedHotel == null){
            return;
        }
        Hotel hotel  = model_HRS.findHotel(selectedHotel);
        
        int[]num = hotel.getNoOfAvailableRooms(day);
        
        int max_size = hotel.getNoOfRooms();
        int totalAvailable = num[0]+num[1]+num[2];
        int totalBooked = max_size - totalAvailable;
        
        gui_Main.getViewPanel().updateAvailability(  totalBooked,
                                   hotel.getNoOfStandardRooms() - num[0],
                                   hotel.getNoOfDeluxeRooms() - num[1],
                                   hotel.getNoOfExecutiveRooms() - num[2],
                                   totalAvailable, num[0], num[1], num[2]
                                   );
    }


   
    /**
     * Handles action events from the view.
     * 
     * @param e the action event
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        
        if(event.equals("Create")){
            //gui_Main.showCreateDialog();
        
                try {
                    
                    
                    String name = gui_Main.getCreateDialog().getHotelName();

                    if(!model_HRS.isHotelNameUnique(name)){
                        throw new IllegalArgumentException("Hotel name is not unique");
                    }

                    int[] rooms = gui_Main.getCreateDialog().getRooms();
                    double price = gui_Main.getCreateDialog().getPrice();

                    //System.out.println("Hotel Name: " + name);
                   // System.out.println("Standard Rooms: " + rooms[0]);
                  //  System.out.println("Deluxe Rooms: " + rooms[1]);
                  //  System.out.println("Executive Rooms: " + rooms[2]);
                    //System.out.println("Price: " + price);
                   // System.out.println("Default Price Selected: " + gui_Main.getCreateDialog().isDefaultPriceSelected());
                    try {
                        model_HRS.createHotel(name,price, rooms[0], rooms[1], rooms[2]);
                    } catch (Exception e1) {
                        System.out.println("If this occurs then everything has gone awry");
                    }
                    updateView();
                    gui_Main.getCreateDialog().reset();
                    gui_Main.getCreateDialog().dispose();
                } catch (IllegalArgumentException  ex) {
                    gui_Main.showPopup(ex);
                }
           
        }else if (event.equals("View")) {
            
            gui_Main.showViewPanel();




        }else if(event.equals("Book")){
            
        	
        	
        	int checkInDay = gui_Main.getBooking().getCheckInDay();
        	int checkOutDay = gui_Main.getBooking().getCheckOutDay();
        	String discountCode = gui_Main.getBooking().getDiscountCode();
        	String guestName = gui_Main.getBooking().getGuestName();
        	String roomType = gui_Main.getBooking().getRoomType();
        	
        	
        	if (guestName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your name.", "Missing Information", JOptionPane.ERROR_MESSAGE);
               
            } else if (roomType == null) {
                JOptionPane.showMessageDialog(null, "Please select a room type.", "Missing Information", JOptionPane.ERROR_MESSAGE);
               
            } else if (checkInDay <= 0) {
                JOptionPane.showMessageDialog(null, "Please select a check-in day.", "Missing Information", JOptionPane.ERROR_MESSAGE);
               
            } else if (checkOutDay <= 0) {
                JOptionPane.showMessageDialog(null, "Please select a check-out day.", "Missing Information", JOptionPane.ERROR_MESSAGE);
              
            } else if (!gui_Main.getBooking().isValidBooking()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid discount code or leave it empty.", "Invalid Discount Code", JOptionPane.ERROR_MESSAGE);
               
            }else {
        	
            	if(showConfirmation() != 0) return;
        	
        	Hotel selectedHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
        	
        	try {
	        	selectedHotel.addReservation(guestName, 
	        								 roomType, 
	        								 checkInDay, 
	        								 checkOutDay, 
	        								 discountCode);
	        	
	        	updateView();
	        	updateReservationView();
	        	gui_Main.getBooking().disposeDialog();
	        	}catch(IllegalArgumentException ev) {
	        		gui_Main.showPopup(ev);
	        		return;
	        	}
        	gui_Main.getBooking().disposeDialog();
            }
        	
        	
        	
        	
        }else if(event.equals("Simulate Booking")){
        	String someString = gui_Main.getSelectedHotel();
        	Hotel fHotel = model_HRS.findHotel(someString);
        	ArrayList<String> list  = new ArrayList<String>();
        	if(fHotel != null) {
            	double[] priceModifier = fHotel.getDatePriceModifier();
            	
            	for (int i = 0; i < priceModifier.length; i++) {
            		String priceMod = String.format("Day %02d -> Day %02d = %.2f%%", i+1, i+2, priceModifier[i] * 100);
                    
                    list.add(priceMod);
                    //System.out.println(priceMod);
                }
             }
        	gui_Main.getBooking().updateDatePriceList(list);
            gui_Main.showSimulateBooking();
        }else if(event.equals("Create Hotel")){
            gui_Main.showCreateDialog();
        }else if(event.equals("View Hotel")){
        	
            gui_Main.showViewPanel();
        }else if(event.equals("Manage Hotel")){
            gui_Main.showManagePanel();
        }else if(event.equals("view availability information")){
            gui_Main.getViewPanel().showAvailabilityPanel();
            gui_Main.getViewPanel().resetAvailabilityInfo();
            updateAvailability(1);
        }else if(event.equals("view room information")){
            
            gui_Main.getViewPanel().showRoomPanel();
            updateRoomView();
        }else if(event.equals("view reservation information")){
            gui_Main.getViewPanel().showReservationPanel();
            updateReservationView();
        }else {
            handleManageHotelActions(event);
        }

    }

    /**
     * Handles actions related to hotel management in the view.
     * 
     * @param action the action command
     */
    private void handleManageHotelActions(String action) {
    	Hotel foundHotel;
        switch (action) {
            case "Change Hotel Name":
            	gui_Main.getManagePanel().openDialog(action);
                break;
            case "Add Room":
            	foundHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
            	if( foundHotel != null) {
            		if(foundHotel.getRoomSize() == 50) {
            			gui_Main.showPopup(new IllegalArgumentException("There are no more rooms to add"));
            			return;
            		}
            		gui_Main.getManagePanel().setMaxRooms(50 - foundHotel.getRoomSize());
            		gui_Main.getManagePanel().openDialog(action);
            	}
                
                break;
            case "Remove Room":
            	
            	foundHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
            	if( foundHotel != null) {
            		if(foundHotel.removableRooms() == 0) {
            			gui_Main.showPopup(new IllegalArgumentException("There are no available rooms to remove"));
            			return;
            		}
            		updateRoomListRemoveRoom();
                    gui_Main.getManagePanel().openDialog(action);
            	}
            	
                break;
            case "Update Base Price":
            	foundHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
            	if(foundHotel.getReservationSize() == 0) {
            		gui_Main.getManagePanel().updateCurrentBasePrice(foundHotel.getBasePrice());
            		gui_Main.getManagePanel().openDialog(action);
            	}else {
            		gui_Main.showPopup(new IllegalArgumentException("No price updates allowed when there are reservations"));
            	}
                //System.out.println(action);
                break;
            case "Remove Reservation":
            	foundHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
            	if( foundHotel != null) {
            		if(foundHotel.getReservationSize() == 0) {
            			gui_Main.showPopup(new IllegalArgumentException("There are no reservations to remove"));
            			return;
            		}
            		updateReservationListRemoveRes();
                	
                    gui_Main.getManagePanel().openDialog(action);
            	}
            	
                break;
            case "Remove Hotel":
                gui_Main.getManagePanel().openDialog(action);
                break;
            case "Modify Date Price":
            	foundHotel = model_HRS.findHotel(gui_Main.getSelectedHotel());
            	if(foundHotel.getReservationSize() == 0) {
            		updatePriceModListModifyD();
            		gui_Main.getManagePanel().openDialog(action);
            	}else {
            		gui_Main.showPopup(new IllegalArgumentException("No price updates allowed when there are reservations"));
            	}
                break;




            case "Confirm Change Hotel Name":
                handleChangeHotelName();
                break;
            case "Confirm Add Room":
                handleAddRoom();
                break;
            case "Confirm Remove Room":
            	handleRemoveRoom();
            	break;
            case "Confirm Remove Hotel":
                handleRemoveHotel();
                break;

            case "Confirm Remove Reservation":
            	handleRemoveReservation();
            	break;


            case "Confirm Update Base Price":
                handleUpdateBasePrice();
                break;
            case "Confirm Modify Date Price":
                handleModifyDatePrice();
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }
    
    /**
     * Handles the addition of new rooms to a hotel.
     */
    private void handleAddRoom() {
    	String oldName = gui_Main.getSelectedHotel();
    	Hotel foundHotel = model_HRS.findHotel(oldName);
    	
    	if(foundHotel == null){
            gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
            gui_Main.getManagePanel().disposeManageDialog();
            return;
        }
    	try {
    		if(showConfirmation() == 0){
          	
    			int[] rooms = gui_Main.getManagePanel().getRooms();
    			foundHotel.addRooms(rooms[0], rooms[1], rooms[2]);
    			gui_Main.getManagePanel().disposeManageDialog();
    			updateView();
    			updateRoomView();
          }
    			
    	}catch(IllegalArgumentException e) {
    		gui_Main.showPopup(e);
    	}
    	
    	
    	
    }
    
    
    
    
    /**
     * Handles the modification of date price modifiers for a hotel.
     */
    
    private void handleModifyDatePrice() {
    	  String oldName = gui_Main.getSelectedHotel();
    	  int[] selectedIndices = gui_Main.getManagePanel().getSelectedDateIndices();
    	  String getPrice = gui_Main.getManagePanel().getPriceModifier().trim();
    	  
          Hotel foundHotel = model_HRS.findHotel(oldName);

          if(foundHotel == null){
              gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
              gui_Main.getManagePanel().disposeManageDialog();
              return;
          }
          if (selectedIndices.length == 0) {
              gui_Main.showPopup(new IllegalArgumentException("Select at least one date to modify"));
              return;
          }
          if(getPrice.isEmpty()) {
        	  gui_Main.showPopup(new IllegalArgumentException("Price cannot be empty"));
              return;
          }
          Double num;
          try {
        	  num = Double.parseDouble(getPrice);
          }catch(NumberFormatException e){
        	  gui_Main.showPopup(new IllegalArgumentException("Modifier must be number or a double"));
        	  return;
          }
          if(num < 50 || num > 150) {
        	  
        	  gui_Main.showPopup(new IllegalArgumentException("Modifier must be between 50 and 150"));
        	  return;
        	  
          }
        	  
          if(showConfirmation() == 0){
          	
        	  foundHotel.updateDatePriceModifier(selectedIndices, num);
          	
        	  updateView();
          }
    
          gui_Main.getManagePanel().disposeManageDialog();
  		
  	}
    
    
    /**
     * Handles the removal of a reservation from a hotel.
     */
    private void handleRemoveReservation() {
    	  String oldName = gui_Main.getSelectedHotel();
    	  ArrayList<String> selectedReservationIDs = gui_Main.getManagePanel().getSelectedReservationID();
          Hotel foundHotel = model_HRS.findHotel(oldName);

          if(foundHotel == null){
              gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
              gui_Main.getManagePanel().disposeManageDialog();
              return;
          }
          
          if (selectedReservationIDs.isEmpty()) {
              gui_Main.showPopup(new IllegalArgumentException("Select at least one reservation to remove"));
              return;
          }
         // System.out.println(selectedReservationID);
          if(showConfirmation() == 0){
        	  for (String reservationID : selectedReservationIDs) {
                  foundHotel.removeReservation(reservationID);
              }
              updateView();
              updateReservationView();
              updateRoomView();
            
          }
          
         
          
          gui_Main.getManagePanel().disposeManageDialog();
		
	}
    /**
     * Handles the removal of rooms from a hotel.
     */
    private void handleRemoveRoom() {
  	  String oldName = gui_Main.getSelectedHotel();
  	  ArrayList<String> selectedRooms = gui_Main.getManagePanel().getSelectedRooms();
        Hotel foundHotel = model_HRS.findHotel(oldName);

        if(foundHotel == null){
            gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
            gui_Main.getManagePanel().disposeManageDialog();
            return;
        }
        if (selectedRooms.isEmpty()) {
            gui_Main.showPopup(new IllegalArgumentException("Select at least one room to remove"));
            return;
        }
        if(selectedRooms.size() == foundHotel.getRoomSize()) {
        	 gui_Main.showPopup(new IllegalArgumentException("WARNING! Removing this many rooms would remove hotel"));
        	 if(showConfirmation() == 0){
             	
        		 model_HRS.remove(foundHotel);
        		 updateView();
             }
        	 gui_Main.getManagePanel().disposeManageDialog();
        	 return;
        	
        }
        
        
        if(showConfirmation() == 0){
        	
        	for (String roomName : selectedRooms) {
                foundHotel.removeRoom(parseRoomName(roomName));
            }
            updateView();
            updateReservationView();
            updateRoomView();
        }
  
        gui_Main.getManagePanel().disposeManageDialog();
		
	}
    /**
     * Parses the room name from a room description that includes type information.
     * 
     * @param roomWithType the room description including type
     * @return the room name without type information
     */
    private String parseRoomName(String roomWithType) {
        int endIndex = roomWithType.indexOf(" (");
        if (endIndex != -1) {
            return roomWithType.substring(0, endIndex);
        }
        return roomWithType; 
    }
    /**
     * Handles the removal of a hotel.
     */
	private void handleRemoveHotel(){
        String oldName = gui_Main.getSelectedHotel();
        Hotel foundHotel = model_HRS.findHotel(oldName);

        if(foundHotel == null){
            gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
            gui_Main.getManagePanel().disposeManageDialog();
            return;
        }
            model_HRS.remove(foundHotel);
            updateView();
            
            gui_Main.getManagePanel().disposeManageDialog();
    }
    
    
    
	 /**
     * Handles the update of a hotel's base price.
     */
    private void handleUpdateBasePrice(){
        String oldName = gui_Main.getSelectedHotel();
        Hotel foundHotel = model_HRS.findHotel(oldName);

        if(foundHotel == null){
            gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
            gui_Main.getManagePanel().disposeManageDialog();
            return;
        }
        
        String newPrice = gui_Main.getManagePanel().getNewPrice();
        try{
            if (Double.parseDouble(newPrice) >= 100) {

                if(showConfirmation() == 0){
                    foundHotel.setPrice(Double.parseDouble(newPrice));
                    updateView();
                }
                    gui_Main.getManagePanel().disposeManageDialog();
                    
                
            } else {
                gui_Main.showPopup(new IllegalArgumentException("Input a number >= 100"));
            }
        } catch(NumberFormatException e){
            gui_Main.showPopup(new IllegalArgumentException("Input a number >= 100"));
        }


    }
    /**
     * Handles changing the name of a hotel.
     */
    private void handleChangeHotelName() {

        String oldName = gui_Main.getSelectedHotel();
        
        Hotel foundHotel = model_HRS.findHotel(oldName);

        if(foundHotel == null){
            gui_Main.showPopup(new IllegalArgumentException("Select a hotel first"));
            gui_Main.getManagePanel().disposeManageDialog();
            return;
        }

        String newName = gui_Main.getManagePanel().getNewHotelName();

       
        if (!newName.trim().isEmpty() && model_HRS.findHotel(newName.trim()) == null) {

            if(showConfirmation() == 0){
                model_HRS.changeHotelName(oldName, newName.trim());
                updateView();
            }
                gui_Main.getManagePanel().disposeManageDialog();
                 
            
        } else {
        	
        	if(newName.trim().isEmpty()) {
        		gui_Main.showPopup(new IllegalArgumentException("Provide a name."));
        	}else {
        		gui_Main.showPopup(new IllegalArgumentException("New Hotel name is not unique"));
        	}
        }

        
    }
    /**
     * Displays a confirmation dialog to the user and returns the user's choice.
     * 
     * @return an integer representing the user's choice
     */
    private int showConfirmation(){
      return gui_Main.showConfirmationDialog("Do you agree?");
    }

    /**
     * Updates the reservation list in the view.
     */
    private void updateReservationView() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
                return;
             }


             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
                for (int i = 0; i < foundHotel.getReservationSize(); i++) {
                    listModel.addElement(foundHotel.getReservationID(i));
                    //System.out.println(foundHotel.getReservation(i).getReservationID());
                }
             }
       
        gui_Main.updateReservationList(listModel);
    }
    
    /**
     * Updates the reservation list for removal purposes.
     */
    private void updateReservationListRemoveRes() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
                return;
             }


             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
                for (int i = 0; i < foundHotel.getReservationSize(); i++) {
                    listModel.addElement(foundHotel.getReservationID(i));
                    //System.out.println(foundHotel.getReservation(i).getReservationID());
                }
             }
       
        gui_Main.getManagePanel().updateReservationList(listModel);
    }
    /**
     * Updates the room list for removal purposes.
     */
    private void updateRoomListRemoveRoom() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
                return;
             }


             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
            	ArrayList<String> removableRoomList = foundHotel.getRemovableRoomNameAndType();
            	
            	for (int i = 0; i < removableRoomList.size(); i++) {
                    String roomInfo = removableRoomList.get(i);
                    
                    listModel.addElement(roomInfo);
                  //  System.out.println(roomInfo);
                }
             }
       
        gui_Main.getManagePanel().updateRoomList(listModel);
    }
    /**
     * Updates the date price modifier list in the view for modification purposes.
     */
    private void updatePriceModListModifyD() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
                return;
             }


             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
            	double[] priceModifier = foundHotel.getDatePriceModifier();
            	
            	for (int i = 0; i < priceModifier.length; i++) {
            		String priceMod = String.format("Day %02d -> Day %02d = %.2f%%", i+1, i+2, priceModifier[i] * 100);
                    
                    listModel.addElement(priceMod);
                  //  System.out.println(priceMod);
                }
             }
       
        gui_Main.getManagePanel().updateDatePriceModifierList(listModel);
    }
    
    
    /**
     * Sets the listener for room selection events.
     */
    public void setRoomSelectionListener(){
        gui_Main.getViewPanel().getRoomList().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
            	
                String selectedHotel = gui_Main.getSelectedHotel();
                Hotel foundHotel = model_HRS.findHotel(selectedHotel);
                
                if(foundHotel == null){
                	
                    return;
                }
              
                String selectedRoom = gui_Main.getViewPanel().getSelectedRoom();
               
                if(selectedRoom == null){
                    return;
                }
                
                
               gui_Main.getViewPanel().showRoomPanel();

                gui_Main.getViewPanel().showRoomInfo(selectedRoom, foundHotel.getRoomRoomType(selectedRoom),
                                                     foundHotel.getRoomBaseRate(selectedRoom), 
                                                     foundHotel.getAvailability(selectedRoom));
               // boolean[] a = foundHotel.getAvailability(selectedRoom);

                
                //System.out.println("This is working");

            }
            
        });
    }
    
    /**
     * Sets the listener for reservation selection events.
     */
    public void setReservationSelectionListener(){
        gui_Main.getViewPanel().getReservationList().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedHotel = gui_Main.getSelectedHotel();
                Hotel foundHotel = model_HRS.findHotel(selectedHotel);

                if(foundHotel == null){
                    return;
                }
                String selectedReservation = gui_Main.getViewPanel().getSelectedReservation();
               // System.out.println(selectedReservation);
                if(selectedReservation == null){
                    return;
                }
                
                

                gui_Main.getViewPanel().showReservationInfo(selectedReservation,
                		foundHotel.getReservationGuestName(selectedReservation),
                		foundHotel.getReservationRoomName(selectedReservation), 
                		foundHotel.getReservationRoomType(selectedReservation),
                		foundHotel.getReservationCheckIn(selectedReservation),
                		foundHotel.getReservationCheckOut(selectedReservation),
                		foundHotel.getReservationTotalPrice(selectedReservation), 
                		foundHotel.getReservationDiscount(selectedReservation),
                		foundHotel.getReservationBreakdown(selectedReservation)
                                                            );
        
               // System.out.println("This is working");

            }
            
        });
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
           // System.out.println(e.getSource().toString());
             String selectedHotel = gui_Main.getSelectedHotel();
             if(selectedHotel == null){
            	 gui_Main.setSimulateEnabled(false);
            	 gui_Main.getViewPanel().noInfo();
                return;
             }
             gui_Main.setSimulateEnabled(true);
             Hotel foundHotel = model_HRS.findHotel(selectedHotel);
             if(foundHotel != null) {
             //hotel name, number of rooms, we can also get it dissected, estimate earnings
            	  gui_Main.getViewPanel().showInfo();
                 gui_Main.getViewPanel().showHotelInfo(foundHotel.getName(), foundHotel.getRoomSize(), 
                                foundHotel.getNoOfStandardRooms(), 
                                foundHotel.getNoOfDeluxeRooms(),
                                foundHotel.getNoOfExecutiveRooms(),
                                foundHotel.getEstimate());
                updateAvailability((int)gui_Main.getViewPanel().getAvailabilitySpinner().getValue());
                
             }
             if(gui_Main.isRoomInformationSelected()){
                updateRoomView();
                gui_Main.getViewPanel().resetRoomInfo();
             }else if(gui_Main.isReservationInformationSelected()){
                updateReservationView();
                gui_Main.getViewPanel().resetReservationInfo();
             }
             
             gui_Main.revalidate();
             gui_Main.repaint();
         }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
       if (e.getSource() == gui_Main.getViewPanel().getAvailabilitySpinner()){
            //System.out.println("It's changing huh");

            updateAvailability((int)gui_Main.getViewPanel().getAvailabilitySpinner().getValue());
       }
    }
}

