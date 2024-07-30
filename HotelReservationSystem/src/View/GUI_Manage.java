package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * The GUI_Manage class is responsible for managing the user interface components 
 * related to hotel management functionalities such as adding, removing, and modifying 
 * hotels, rooms, reservations, and prices.
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 * 
 */
public class GUI_Manage extends JPanel {

    
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2466504295136985156L;
	/**
	 * List of custom buttons used in the GUI.
	 */
	private ArrayList<PlainButton> buttons;

	/**
	 * The parent frame for the GUI, serving as the main window.
	 */
	private JFrame parentFrame;

	/**
	 * Tool bar component for the GUI, providing various tool options.
	 */
	private PlainToolBar toolBar;

	/**
	 * Button for confirming actions or inputs.
	 */
	private JButton confirmation;

	/**
	 * Dialog window for additional user interactions.
	 */
	private JDialog dialog;

	/**
	 * Listener for managing list selections, used for handling names and other data.
	 */
	private ListSelectionListener lsl;

	/**
	 * Text field for entering the name of a new hotel.
	 */
	private JTextField newHotelNameField;

	/**
	 * Text field for entering the base price of a new hotel.
	 */
	private JTextField newBasePriceField;

	/**
	 * Text field for entering the price modifier for a new hotel.
	 */
	private JTextField newPriceModifierField;

	/**
	 * Label for displaying or indicating the new base price.
	 */
	private JLabel newBasePrice;

	/**
	 * The currently selected item in the GUI.
	 */
	private String selected;

	/**
	 * List component for displaying a list of reservations.
	 */
	private JList<String> reservationList;

	/**
	 * List component for displaying a list of rooms.
	 */
	private JList<String> roomList;

	/**
	 * List component for displaying a list of price modifiers.
	 */
	private JList<String> priceModsList;

	/**
	 * Spinner for selecting the number of standard rooms.
	 */
	private JSpinner standardSpinner;

	/**
	 * Spinner for selecting the number of deluxe rooms.
	 */
	private JSpinner deluxeSpinner;

	/**
	 * Spinner for selecting the number of executive rooms.
	 */
	private JSpinner executiveSpinner;

	/**
	 * Maximum number of rooms allowed.
	 */
	private int maxrooms;

    
    
    /**
     * Constructs a GUI_Manage instance with the specified parent frame.
     * 
     * @param parentFrame the parent JFrame
     */
    public GUI_Manage(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        confirmation = new JButton();
        initializeToolBar();
        initializeButtons();
        setLayout(new BorderLayout());
        
        add(toolBar, BorderLayout.NORTH);
        maxrooms = 50;
        reservationList = new JList<String>();
    	roomList = new JList<String>();
    	priceModsList = new JList<String>();
        lsl = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<?> source = (JList<?>) e.getSource();
                
                if (!source.isSelectionEmpty()) {
                    enableButtons();
                    selected = (String) source.getSelectedValue();
                }else{
                    disableButtons();
                }
            }
            
        };
        
        
    
        
    }
    
    /**
     * Sets the ListSelectionListener for reservationList, roomList, and priceModsList.
     * 
     * @param listener the ListSelectionListener to set
     */
    public void setListSelectionListener(ListSelectionListener listener) {
    	reservationList.addListSelectionListener(listener);
    	roomList.addListSelectionListener(listener);
    	priceModsList.addListSelectionListener(listener);
    
    }
    
    /**
     * Updates the reservation list with the specified list model.
     * 
     * @param listModel the list model to update with
     */
    
    public void updateReservationList(DefaultListModel<String> listModel) {
    	
    	reservationList.setModel(listModel);
    	
    }
    
    
    /**
     * Updates the room list with the specified list model.
     * 
     * @param listModel the list model to update with
     */
    public void updateRoomList(DefaultListModel<String> listModel) {
    	
	 	roomList.setModel(listModel);
    	
    }
    
    /**
     * Updates the date price modifier list with the specified list model.
     * 
     * @param listModel the list model to update with
     */
    public void updateDatePriceModifierList(DefaultListModel<String> listModel) {
    	
	 	priceModsList.setModel(listModel);
    	
    }
    
    /**
     * Returns the selected reservation IDs.
     * 
     * @return the selected reservation IDs
     */
    public ArrayList<String>  getSelectedReservationID() {
    	java.util.List<String> selectedValuesList = reservationList.getSelectedValuesList();
    	
    	return new ArrayList<>(selectedValuesList);
    }
    
    /**
     * Returns the selected rooms.
     * 
     * @return the selected rooms
     */
    public ArrayList<String> getSelectedRooms() {
    	
        java.util.List<String> selectedValuesList = roomList.getSelectedValuesList();
        return new ArrayList<>(selectedValuesList);
    }
    
    /**
     * Updates the current base price displayed.
     * 
     * @param basePriceToShow the base price to show
     */
    public void updateCurrentBasePrice(double basePriceToShow) {
    	newBasePrice = new JLabel(String.format("Current Base Price: %s", basePriceToShow));
    }
    
    /**
     * Returns the ListSelectionListener.
     * 
     * @return the ListSelectionListener
     */
    public ListSelectionListener getLSL(){
        return lsl;
    }
    
    /**
     * Sets the ActionListener for the confirmation button and other buttons.
     * 
     * @param listener the ActionListener to set
     */
    public void setActionListener(ActionListener listener){
        confirmation.addActionListener(listener);
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).addActionListener(listener);
           // System.out.println(buttons.get(i).getText());
        } 
        disableButtons();
        revalidate();
        repaint();
    }
    
    /**
     * Disables the buttons in the toolbar.
     */
    private void disableButtons(){
        
           
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).setEnabled(false);
            }
           
            revalidate();
            repaint();
    }
    
    /**
     * Enables the buttons in the toolbar.
     */
    private void enableButtons(){
        
           
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setEnabled(true);
        }
        revalidate();
        repaint();
}
    
    /**
     * Initializes the toolbar.
     */

    private void initializeToolBar() {
        toolBar = new PlainToolBar();
        toolBar.setFloatable(false); // Makes sure the toolbar cannot be moved
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set the layout to FlowLayout aligned to the left
    }
    
    /**
     * Initializes the buttons in the toolbar.
     */

    private void initializeButtons() {
        buttons = new ArrayList<PlainButton>();
        
        
        String[] buttonLabels = {
            "Change Hotel Name", "Add Room", "Remove Room", 
            "Update Base Price", "Remove Reservation", "Remove Hotel", 
            "Modify Date Price"//, "Duplicate Hotel"
        };
        //int i = 0;
        for (String buttonLabel : buttonLabels) {
            PlainButton button = new PlainButton(buttonLabel);
            buttons.add(button); // Add to button array
            toolBar.add(button);
            //System.out.println(buttons.get(i++).getText());
        }

        
    }

    /**
     * Opens a dialog with the specified type.
     * 
     * @param dialogType the type of dialog to open
     */
    public void openDialog(String dialogType) {
        JPanel panel = createPanel(dialogType);

        if (panel != null) {
            dialog = new JDialog(parentFrame, dialogType, true);
            confirmation.setActionCommand("Confirm " + dialogType);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setContentPane(panel);
            dialog.setResizable(false);
            dialog.pack();
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
            
        }else{
        	System.out.println("The panel is nulllllllll!");
        }
        

    }

    /**
     * Disposes the manage dialog.
     */
    public void disposeManageDialog(){
        if(dialog!= null){  
            dialog.dispose();
        }
       
    }
    
    /**
     * Creates a panel based on the specified dialog type.
     * 
     * @param dialogType the type of dialog
     * @return the created panel
     */

    private JPanel createPanel(String dialogType) {
        switch (dialogType) {
            case "Change Hotel Name"://done
                return createChangeHotelNamePanel();
            case "Add Room"://tom //SOMEDAY ILL ADD YOU TODAY THO FR
                return createAddRoomPanel();
            case "Remove Room"://done //we're just gonna show removable rooms
                return createRemoveRoomPanel();
            case "Update Base Price"://done
                return createUpdateBasePricePanel();
            case "Remove Reservation"://done
                return createRemoveReservationPanel();
            case "Remove Hotel"://done
                return createRemoveHotelPanel();
            case "Modify Date Price"://done
                return createModifyDatePricePanel();
            default:
                return null;
        }
    }

    /**
     * Creates a panel for changing the hotel name.
     * 
     * @return the created panel
     */
    private JPanel createChangeHotelNamePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panel1 = new JPanel(new GridLayout(0, 1));
        JPanel panel2 = new JPanel(new GridLayout(1, 3));
        
        newHotelNameField = new JTextField(20);
        JLabel newHotelName = new JLabel("New Hotel Name:  " );
        confirmation.setActionCommand("Confirm");
        confirmation.setText("Change");
        
        panel1.add(new JLabel("Old Hotel Name: " + selected));
        panel1.add(newHotelName);
        
        panel1.add(newHotelNameField);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.add(new JLabel(" "));
        panel2.add(confirmation);
        panel2.add(new JLabel(" "));
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
       
        return panel;
    }

    /**
     * Returns the new hotel name from the text field.
     * 
     * @return the new hotel name
     */
    public String getNewHotelName(){
        return newHotelNameField.getText();
    }
    
    /**
     * Returns the new price from the text field.
     * 
     * @return the new price
     */
    public String getNewPrice(){
        return newBasePriceField.getText();
    }
    
    /**
     * Returns the base price from the text field.
     * 
     * @return the base price
     */
    public String getBasePriceField(){
        return newBasePriceField.getText();
    }
    
    /**
     * Creates a panel for adding rooms.
     * 
     * @return the created panel
     */
    private JPanel createAddRoomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        standardSpinner = new JSpinner();
        deluxeSpinner = new JSpinner();
        executiveSpinner = new JSpinner();
        ((SpinnerNumberModel) standardSpinner.getModel()).setMinimum(0);
        ((SpinnerNumberModel) deluxeSpinner.getModel()).setMinimum(0);
        ((SpinnerNumberModel) executiveSpinner.getModel()).setMinimum(0);
        JLabel standardLabel = new JLabel("Standard: ");
        JLabel deluxeLabel = new JLabel("Deluxe: ");
        JLabel executiveLabel = new JLabel("Executive: ");
        
        panel.add(standardLabel);
        panel.add(new JLabel(" "));
        panel.add(standardSpinner);
        panel.add(new JLabel(" "));
        panel.add(deluxeLabel);
        panel.add(new JLabel(" "));
        panel.add(deluxeSpinner);
        panel.add(new JLabel(" "));
        panel.add(executiveLabel);
        panel.add(new JLabel(" "));
        panel.add(executiveSpinner);

        JPanel somePanel = new JPanel(new GridLayout(1, 3));
        confirmation.setText("Add");
        somePanel.add(new JLabel(" "));
        somePanel.add(confirmation);
        somePanel.add(new JLabel(" "));
        somePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(somePanel);

        addSpinnerListeners();

        return panel;
    }
    
    /**
     * Sets the maximum number of rooms.
     * 
     * @param maxrooms the maximum number of rooms
     */
    public void setMaxRooms(int maxrooms) {
    	this.maxrooms = maxrooms;
    }
    
    /**
     * Adds listeners to the spinners.
     */
    private void addSpinnerListeners() {
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateSpinnerModels();
            }
        };

        standardSpinner.addChangeListener(listener);
        deluxeSpinner.addChangeListener(listener);
        executiveSpinner.addChangeListener(listener);

       
    }
    /**
     * Updates the models of the spinners based on their values.
     */
    private void updateSpinnerModels() {
        int standard = (Integer) standardSpinner.getValue();
        int deluxe = (Integer) deluxeSpinner.getValue();
        int executive = (Integer) executiveSpinner.getValue();

        ((SpinnerNumberModel) standardSpinner.getModel()).setMaximum(maxrooms - deluxe - executive);
        ((SpinnerNumberModel) deluxeSpinner.getModel()).setMaximum(maxrooms - standard - executive);
        ((SpinnerNumberModel) executiveSpinner.getModel()).setMaximum(maxrooms - standard - deluxe);
        
   
        if (standard > maxrooms - deluxe - executive) {
            standardSpinner.setValue(maxrooms - deluxe - executive);
        }
        if (deluxe > maxrooms - standard - executive) {
            deluxeSpinner.setValue(maxrooms - standard - executive);
        }
        if (executive > maxrooms - standard - deluxe) {
            executiveSpinner.setValue(maxrooms - standard - deluxe);
        }
    }
    
    /**
     * Returns the number of rooms from the spinners.
     * 
     * @return an array of room counts
     * @throws IllegalArgumentException if the total number of rooms is zero
     */
    public int[] getRooms() throws IllegalArgumentException {
        Integer standardRooms = (Integer) standardSpinner.getValue();
        Integer deluxeRooms = (Integer) deluxeSpinner.getValue();
        Integer executiveRooms = (Integer) executiveSpinner.getValue();

        int totalRooms = standardRooms + deluxeRooms + executiveRooms;
        if (totalRooms == 0) {
            throw new IllegalArgumentException("Total number of rooms cannot be zero.");
        }

        return new int[]{standardRooms, deluxeRooms, executiveRooms};
    }

    /**
     * Creates a panel for removing rooms.
     * 
     * @return the created panel
     */
    private JPanel createRemoveRoomPanel() {
    
        
        JScrollPane scrollPane = new JScrollPane(roomList);
    	JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JLabel removableRooms = new JLabel("Select Room/s to Remove");
        roomList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel1.add(removableRooms, BorderLayout.NORTH);
        panel.add(scrollPane);
        
        confirmation.setText("Remove");
        //panel.add(confirmation);
        
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        panel1.add(panel, BorderLayout.CENTER);
        panel1.add(confirmation, BorderLayout.SOUTH);
        return panel1;
    }
    
   
    /**
     * Creates a panel for updating the base price.
     * 
     * @return the created panel
     */
    private JPanel createUpdateBasePricePanel() {
 
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new GridLayout(1, 3));

        newBasePriceField = new JTextField(20);
        
       //newBasePrice= new JLabel("New Base Price:  " );
        //confirmation.setActionCommand("Confirm");
        confirmation.setText("Update");
       
        panel1.add(newBasePrice, BorderLayout.NORTH);
    
        panel1.add(new JLabel("Enter new price (>=100): "), BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        panel3.add(newBasePriceField, BorderLayout.SOUTH);
        panel3.add(panel1, BorderLayout.NORTH);
        panel2.add(new JLabel(" "));
        panel2.add(confirmation);
        panel2.add(new JLabel(" "));
        panel.add(panel3, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.SOUTH);
        return panel;
    }
    
    /**
     * Creates a panel for removing reservations.
     * 
     * @return the created panel
     */
    private JPanel createRemoveReservationPanel() {
    	
    	JScrollPane scrollPane = new JScrollPane(reservationList);
    	JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JLabel removingRes = new JLabel("Select a Reservation to Remove");

        panel1.add(removingRes, BorderLayout.NORTH);
        panel.add(scrollPane);
        
        confirmation.setText("Remove");
        //panel.add(confirmation);
        
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        panel1.add(panel, BorderLayout.CENTER);
        panel1.add(confirmation, BorderLayout.SOUTH);
        return panel1;
    }

    
    /**
     * Creates a panel for removing hotels.
     * 
     * @return the created panel
     */
    private JPanel createRemoveHotelPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
      //  JTextField hotelRemove = new JTextField();
        JLabel removingHotel = new JLabel("Removing Hotel.");
       // confirmation.setActionCommand("Confirm");
        confirmation.setText("Confirm");
        panel.add(removingHotel);
        panel.add(new JLabel(" "));
        panel.add(confirmation);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
        //panel.add(hotelRemove);
        return panel;
    }
    
    /**
     * Returns the selected date indices from the list.
     * 
     * @return the selected date indices
     */
    
    public int[] getSelectedDateIndices(){
    	return priceModsList.getSelectedIndices();
    	
    }
    
    /**
     * Returns the price modifier from the text field.
     * 
     * @return the price modifier
     */
    
    public String getPriceModifier() {
    	return newPriceModifierField.getText();
    }
    
    /**
     * Creates a panel for modifying date prices.
     * 
     * @return the created panel
     */

    private JPanel createModifyDatePricePanel() {
    	//no i wont size them, ill let them handle their size
            JScrollPane scrollPane = new JScrollPane(priceModsList);
        	JPanel panel1 = new JPanel(new BorderLayout());
        	JPanel panel2 = new JPanel(new BorderLayout());
        	JPanel panel3 = new JPanel(new BorderLayout());
            JPanel panel = new JPanel(new GridLayout(0, 1));
            
            JLabel modifiableDates = new JLabel("Select Dates to Modify: ");
            priceModsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            newPriceModifierField = new JTextField(20);
            panel1.add(modifiableDates, BorderLayout.NORTH);
            panel.add(scrollPane);
            panel3.add(new JLabel("Enter number: "), BorderLayout.NORTH);
            
            panel2.add(newPriceModifierField, BorderLayout.NORTH);
            confirmation.setText("Modify");
            //panel.add(confirmation);
            
            panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
            panel1.add(panel, BorderLayout.CENTER);
            panel2.add(confirmation, BorderLayout.SOUTH);
            panel3.add(panel2, BorderLayout.SOUTH);
            
            panel1.add(panel3, BorderLayout.SOUTH);
            return panel1;
     
    }
}
