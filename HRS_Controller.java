package Controller;

import java.awt.CardLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.*;

import Model.*;
import View.*;
public class HRS_Controller implements ActionListener, DocumentListener, ComponentListener {
	private HotelListManager model;
	private HRS_GUI gui;
	
	public HRS_Controller(HotelListManager model, HRS_GUI gui){
		
		this.model = model;
		this.gui = gui;
		
		//updateView();
		gui.setActionListener(this);
		gui.setComponentListener(this);
		//gui.setDocumentListener(this);
		updateView();
	}

	
	
	  public void updateView() {
	        DefaultListModel<String> listModel = new DefaultListModel<>();
	        
	        for (int i = 0; i < model.getHotelListSize(); i++) {
	        	Hotel hotel = model.getHotel(i);
	            listModel.addElement(hotel.getName());
	            System.out.println(hotel.getName());
	        }
	       
	        gui.updateList(listModel);
	    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) gui.getCards().getLayout();
		if(e.getActionCommand().equals("Create Hotel")) {
			
			gui.showCreateMenu();
			System.out.println("You created a hotel!");
			cardLayout.show(gui.getCards(), "Create Hotel");
			
		}else if(e.getActionCommand().equals("View Hotel")) {
			System.out.println("You viewed a hotel!");
			cardLayout.show(gui.getCards(), "View Hotel");
		}else if(e.getActionCommand().equals("Manage Hotel")) {
			System.out.println("You managed a hotel!");
			cardLayout.show(gui.getCards(), "Manage Hotel");
		} else if (e.getSource() == gui.getSubmitButton()) {
            try {
                String name = gui.getNameField().getText();
                int standardRooms = Integer.parseInt(gui.getStandardField().getText());
                int deluxeRooms = Integer.parseInt(gui.getDeluxeField().getText());
                int executiveRooms = Integer.parseInt(gui.getExecutiveField().getText());
                double price = 1299;

                if (!gui.getDefaultPriceCheckBox().isSelected()) {
                    price = Double.parseDouble(gui.getPriceField().getText());
                }

                int totalRooms = standardRooms + deluxeRooms + executiveRooms;
                if (totalRooms <= 0 || totalRooms > 50) {
                    throw new IllegalArgumentException("Total rooms must be between 1 and 50.");
                }

                if (price <= 100) {
                    throw new IllegalArgumentException("Price must be greater than 100.");
                }
                
   
                try {
					model.createHotel(name, price, standardRooms, deluxeRooms, executiveRooms);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					throw new IllegalArgumentException("Hotel Name is Not Unique");
				}
                updateView();
                gui.showPopup("Success", "Hotel created successfully!");
            } catch (NumberFormatException ex) {
                gui.showPopup("Invalid Input", "Please enter valid numbers for rooms and price.");
            } catch (IllegalArgumentException ex) {
                gui.showPopup("Invalid Input", ex.getMessage());
            } 
            
        }
		updateView();
		
	}
	
	
	
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		 //System.out.println("Component resized");
		gui.updateCardPanelSize();
		
	}



	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
