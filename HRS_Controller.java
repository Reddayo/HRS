package Controller;


import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.event.*;

import Model.*;
import View.*;
public class HRS_Controller implements  ActionListener, 
										DocumentListener, 
										ComponentListener,
										FocusListener, 
										MouseListener, 
										KeyListener, 
										ListSelectionListener {
	private HotelListManager model;
	private HRS_GUI gui;
	
	public HRS_Controller(HotelListManager model, HRS_GUI gui){
		
		this.model = model;
		this.gui = gui;
		
		//updateView();
		gui.setActionListener(this);
		gui.setComponentListener(this);
		gui.setDocumentListener(this);
		gui.setFocusListener(this);
		gui.setListListener(this);
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
	
		if(e.getActionCommand().equals("Create Hotel")) {
			
			gui.showCreateMenu();
			System.out.println("You created a hotel!");
			
		}else if(e.getActionCommand().equals("View Hotel")) {
			System.out.println("You viewed a hotel!");
			gui.showViewMenu();
		}else if(e.getActionCommand().equals("Manage Hotel")) {
			System.out.println("You managed a hotel!");
			gui.showManageMenu();
		} else if (e.getActionCommand().equals("CSubmit")) {
            try {
                String name = gui.getHotelName();
                int standardRooms = Integer.parseInt(gui.getStandardRoomNum());
                int deluxeRooms = Integer.parseInt(gui.getDeluxeRoomNum());
                int executiveRooms = Integer.parseInt(gui.getExecutiveRoomNum());
                double price = 1299;

                if (!gui.isDefaultPriceCheckBoxSelected()) {
                    price = Double.parseDouble(gui.getPrice());
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
            
            gui.showCreateMenu();
        }else if (e.getActionCommand().equals("VSubmit") ) {
        	gui.showViewMenu();
        }else if(e.getActionCommand().equals("Default Price (1299)")) {
        	gui.toggleDefaultPrice();
        }
		
		
	}
	
	
	
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Text inserted: " + e.getLength() + " characters");
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



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	 	public void focusGained(FocusEvent e) {
	        if (e.getSource() instanceof JTextField) {
	            JTextField textField = (JTextField) e.getSource();
	            String currentText = textField.getText();

	            // Example: Check if the current text matches a placeholder
	            if ("Enter Text".equals(currentText)) {
	                gui.removeNameFieldText(); // Method in Gui to remove placeholder text
	            }
	        }
	    }

	    @Override
	    public void focusLost(FocusEvent e) {
	        if (e.getSource() instanceof JTextField) {
	            JTextField textField = (JTextField) e.getSource();
	            String currentText = textField.getText();

	            // Example: Check if the current text is empty
	            if (currentText.isEmpty()) {
	                gui.addGhostNameField(); // Method in Gui to add placeholder text
	            }
	        }
	    }



		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
                gui.showHotelInfo();
            }
		}

	
}
