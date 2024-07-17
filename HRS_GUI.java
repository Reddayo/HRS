package View;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class HRS_GUI extends JFrame{
    
    
    
    private static final long serialVersionUID = 7413264850018485788L;

    /**
     * Fonts properties of the GUI
     */
    private Font buttonFonts;
    private Font whiteButtonFonts;

    /**
     * Dimensions that will be used throughout the GUI
     */
    private Dimension buttonSizes;
    private Dimension mainFrameSize;
    
    /**
     * Lists that will be shown using scroll pane
     */
    private JList<String> hotelList; //= new JList<String>();
    private JList<String> roomList;
    private JList<String> reservationList;

	private JScrollPane listScrollPane; //= new JScrollPane(anyList);
	private JPanel titlePanel;

	private JPanel cardMainInfoPanel; 
    private JPanel createHotelPanel; //only one panel no need to worry

            private JTextField nameField;
            private JTextField standardField;
            private JTextField deluxeField;
            private JTextField executiveField;
            private JCheckBox defaultPriceCheckBox;
            private JTextField priceField;
            private JLabel priceLabel;
            private JButton createHotelSubmitButton;



    private JPanel viewHotelPanel; //4 panels in total
	private JPanel viewHotelInfoPanel; //this would have the info //box layout
	private JPanel viewMiscInfoPanel; // this would have labels, dates, and more dates //box layout
	private JPanel viewButtonsPanel; //this would be center // as far as I care
		private JLabel hotelInfoLabel;
        private JLabel hotelName;
        private JLabel roomSize;
        private JLabel standardRoomSize;
        private JLabel deluxeRoomSize;
        private JLabel executiveRoomSize;
        private JLabel estimateEarnings;

        private JToggleButton hotelAvailabilityButton;
        private JToggleButton roomInformationButton;
        private JToggleButton reservationInformationButton;

        private ButtonGroup viewHotelButtonGroup;
        private JPanel viewHotelTopPanel;
        private JPanel hotelAvailabilityPanel;
            private JButton viewHotelSubmitButton;
            
            private JLabel bookedRooms;
            private JLabel bookedStandardRooms;
            private JLabel bookedDeluxeRooms;
            private JLabel bookedExecutiveRooms;
            private JLabel availableRooms;
            private JLabel availableStandardRooms;
            private JLabel availableDeluxeRooms;
            private JLabel availableExecutiveRooms;

        private JPanel roomInformationPanel;

            private JLabel roomNameRoI;
            private JLabel roomPriceRoI;
            private JLabel availabilityCalendarRoI;

        private JPanel reservationInformationPanel;
        	
        	private JLabel reservationIDReI;
            private JLabel guestNameReI;
            private JLabel roomNameReI;
            private JLabel checkInReI;
            private JLabel checkOutReI;
            private JLabel totalPriceReI;
            private JLabel breakDownPriceNightReI;



    //make sure to utilize a confirmation thing
    private JPanel manageHotelPanel;
        private JPanel changeHotelNamePanel; //ofc this would have labels and stuff
            private JTextField newHotelName;
            private JLabel oldHotelName;

        private JPanel addRoomPanel;
            private JTextField roomAdd;
            private JLabel addableRooms;

        private JPanel removeRoomPanel;
            private JTextField roomRemove;
            private JLabel removableRooms;
            
        private JPanel updateBasePricePanel;
            private JTextField newBasePrice; //should still be >= 100
            private JLabel oldBasePrice;
            
        private JPanel removeReservationPanel;
            private JTextField reservationRemove;
            private JLabel removingRes;
        private JPanel removeHotelPanel;
            private JTextField hotelRemove;
            private JLabel removingHotel;
        private JPanel modifyDatePricePanel;
            private JTextField datePriceModifier;
            private JLabel addPriceMod;


    private JPanel simulateBookingPanel; //only one panel no need to worry
        private JTextField hotelNameFieldSB;
        private JTextField guestNameFieldSB;
        
        private JToggleButton standardButtonSB;
        private JToggleButton deluxeButtonSB;
        private JToggleButton executiveButtonSB;
        
        private ButtonGroup roomTypePick;
        
    private JTextField discountFieldSB;
    private JDialog dialog;
    private JButton bookButtonSB;

	private CardLayout mainInfoShower = new CardLayout();
    private CardLayout manageHotelShower = new CardLayout();
    private CardLayout viewHotelMiscInfoShower = new CardLayout();

   

    /** TOP BAR
     * Buttons that will be shown at the top using a flow panel
     */
	private JToggleButton createButton;
	private JToggleButton viewButton;
	private JToggleButton manageButton;
    private JButton simulateButton; //it would be pretty satisfying if you click it
    private JPanel topToolBar;




    /**
     * Utility labels
     */
	private JLabel blankLabel = new JLabel(" ");

	
	public HRS_GUI(String name){
		super(name);
		setLayout(new BorderLayout());
	
	
		//submitButton.setActionCommand("CSubmit");
		
		setSize(new Dimension(800, 600));
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		setMinimumSize(new Dimension(800, 600));
		//pack();
		
		revalidate();
        repaint();
		
		
	}
	

    public void init(){


        //This won't change
        dimensionInit();
        fontsInit();
        titlePanelInit();
        viewHotelInit();
        createHotelPanelInit();
        manageHotelInit();
        simulateBookingInit();
        mainInfoPanelInit();
        topToolBarInit();
        hotelListInit();
        
        this.add(topToolBar, BorderLayout.NORTH);
        this.add(cardMainInfoPanel, BorderLayout.WEST);
        this.add(listScrollPane, BorderLayout.CENTER);
        
        revalidate();
        repaint();
        
        

    }

    private void titlePanelInit() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        
        // Set the alignment of the titlePanel to the center
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleLabel = new JLabel("Hotel Reservation System++");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel devName = new JLabel("Made by: Jusper Angelo Cesar");
        devName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel E2 = new JLabel("As a requirement for CCPROG3 MCO2");
        E2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel mistakeName = new JLabel("This was a mistake!");
        mistakeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        titlePanel.add(new JLabel(" "));
        
        titlePanel.add(titleLabel);
        titlePanel.add(devName);
        titlePanel.add(E2);
        titlePanel.add(mistakeName);
        
        // cardMainInfoPanel.add(titlePanel);
    }

    public void hotelListInit() {
        hotelList = new JList<>();
		listScrollPane = new JScrollPane(hotelList);
		hotelList.setModel(new DefaultListModel<String>());
		hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//list.setCellRenderer();
		hotelList.setVisible(true);
		hotelList.setEnabled(false);
		int lastIndex = -1;
		hotelList.setSelectedIndex(lastIndex);
		hotelList.ensureIndexIsVisible(lastIndex);
		//hotelList.setEnabled(false);
		listScrollPane.setViewportView(hotelList);
	}
	
	public void updateHotelList(DefaultListModel<String> listModel) {
	    hotelList.setModel(listModel); 
	    listScrollPane.setViewportView(hotelList); 
	}
    
	
    private void viewHotelInit(){
        viewHotelPanel = new JPanel(new BorderLayout());
        
        viewMiscInfoPanel = new JPanel(viewHotelMiscInfoShower);
        
        viewHotelInfoPanel = new JPanel();
        
        viewHotelInfoPanel.setLayout(new BoxLayout(viewHotelInfoPanel, BoxLayout.Y_AXIS));
        
        hotelName = new JLabel(" ");
        roomSize = new JLabel("No. of Rooms:");
        standardRoomSize = new JLabel("Standard: ");
        deluxeRoomSize = new JLabel("Deluxe: ");
        executiveRoomSize = new JLabel("Executive: ");
        estimateEarnings = new JLabel("Estimate Earnings: ");
        
        
        hotelInfoLabel = new JLabel("Hotel Information");
        
        hotelName.setFont(new Font("Consolas", Font.BOLD, 30));
        roomSize.setFont(new Font("Consolas", Font.BOLD, 20));

        
        viewHotelInfoPanel.add(hotelInfoLabel);
        viewHotelInfoPanel.add(hotelName);
        viewHotelInfoPanel.add(roomSize);
        viewHotelInfoPanel.add(standardRoomSize);
        viewHotelInfoPanel.add(deluxeRoomSize);
        viewHotelInfoPanel.add(executiveRoomSize);
        viewHotelInfoPanel.add(estimateEarnings);
        viewHotelInfoPanel.add(blankLabel);
        //hotelName.setVisible(false); box layout just stacks things and dont care if one component is visible or not
        
        
        
        viewButtonsPanel = new JPanel();   
        
        viewButtonsPanel.setLayout(new BoxLayout(viewButtonsPanel, BoxLayout.X_AXIS));
        
        
        
        hotelAvailabilityButton = new JToggleButton("Availability Information");
		roomInformationButton = new JToggleButton(" Room Information ");
		reservationInformationButton = new JToggleButton("Reservation Information");
		
		
		hotelAvailabilityButton.setActionCommand("view availability information");
		roomInformationButton.setActionCommand("view room information");
		reservationInformationButton.setActionCommand("view reservation information");
		
		hotelAvailabilityButton.setBorderPainted(false);
		hotelAvailabilityButton.setFocusPainted(false);
		hotelAvailabilityButton.setPreferredSize(buttonSizes);
		hotelAvailabilityButton.setFont(whiteButtonFonts);
		
		roomInformationButton.setBorderPainted(false);
		roomInformationButton.setFocusPainted(false);
		roomInformationButton.setPreferredSize(buttonSizes);
		roomInformationButton.setFont(whiteButtonFonts);

		reservationInformationButton.setBorderPainted(false);
		reservationInformationButton.setFocusPainted(false);
		reservationInformationButton.setPreferredSize(buttonSizes);
		reservationInformationButton.setFont(whiteButtonFonts);
		
		hotelAvailabilityButton.setBackground(Color.decode("0x646464"));
		roomInformationButton.setBackground(Color.decode("0x646464"));
		reservationInformationButton.setBackground(Color.decode("0x646464"));
		
		hotelAvailabilityButton.setForeground(Color.decode("0xFFFFFF"));
		roomInformationButton.setForeground(Color.decode("0xFFFFFF"));
		reservationInformationButton.setForeground(Color.decode("0xFFFFFF"));
		
       
		viewHotelInfoPanel.setBackground(Color.decode("0xFFFFFF"));
        
        
        
        viewHotelButtonGroup = new ButtonGroup();
        viewHotelButtonGroup.add(hotelAvailabilityButton);
        viewHotelButtonGroup.add(roomInformationButton);
        viewHotelButtonGroup.add(reservationInformationButton);
        
        viewButtonsPanel.add(hotelAvailabilityButton);
        viewButtonsPanel.add(roomInformationButton);
        viewButtonsPanel.add(reservationInformationButton);
        
        viewButtonsPanel.setBackground(Color.decode("0x646464"));
        
        
        viewMiscInfoPanel = new JPanel(viewHotelMiscInfoShower);
        
        
        //viewMiscInfoPanel.add(new JLabel("Hello this is where the room info and other shit go"));
        
        
        hotelAvailabilityPanel = new JPanel();
        hotelAvailabilityPanel.setLayout(new BoxLayout(hotelAvailabilityPanel, BoxLayout.Y_AXIS));
        hotelAvailabilityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hotelAvailabilityPanel.setAlignmentX(LEFT_ALIGNMENT);
        viewHotelSubmitButton =  new JButton("Submit");
        viewHotelSubmitButton.setActionCommand("VSubmit");
        
        
        
       // dateFieldVH;
        //dateLabelVH;
        
        
        bookedRooms =             new JLabel("Total No. of Booked Rooms: ", SwingConstants.LEFT);
        bookedStandardRooms =     new JLabel("   Standard: ");
        bookedDeluxeRooms =       new JLabel("      Deluxe: ");
        bookedExecutiveRooms =    new JLabel("  Executive: ");
        availableRooms =          new JLabel("Total No. of Available Rooms: ");
        availableStandardRooms =  new JLabel("   Standard: ");
        availableDeluxeRooms =    new JLabel("      Deluxe: ");
        availableExecutiveRooms = new JLabel("  Executive:  ");
        
     
        

        hotelAvailabilityPanel.add(bookedRooms);
        hotelAvailabilityPanel.add(bookedStandardRooms);
        hotelAvailabilityPanel.add(bookedDeluxeRooms);
        hotelAvailabilityPanel.add(bookedExecutiveRooms);
        hotelAvailabilityPanel.add(availableRooms);
        hotelAvailabilityPanel.add(availableStandardRooms);
        hotelAvailabilityPanel.add(availableDeluxeRooms);
        hotelAvailabilityPanel.add(availableExecutiveRooms);
        
        roomInformationPanel = new JPanel();
        
        
        roomInformationPanel.setLayout(new BoxLayout(roomInformationPanel, BoxLayout.Y_AXIS));
        roomInformationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        roomNameRoI = new JLabel("Room Name: ");
        roomPriceRoI = new JLabel("Room Price: "); //if there is a modification 
        //it would call this date modified prices..
        availabilityCalendarRoI = new JLabel("Availability Calendar: ");
        
        
        
        roomInformationPanel.add(roomNameRoI);
        roomInformationPanel.add(roomPriceRoI);
        roomInformationPanel.add(availabilityCalendarRoI);
        
        
        
        
        reservationInformationPanel = new JPanel();
        
        reservationInformationPanel.setLayout(new BoxLayout(reservationInformationPanel, BoxLayout.Y_AXIS));
        reservationInformationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        reservationIDReI = new JLabel("Reservation ID: ");
        guestNameReI = new JLabel("Guest Name: ");
        roomNameReI = new JLabel("Room Name: ");
        checkInReI = new JLabel("Check In: ");
        checkOutReI = new JLabel("Check Out: ");
        totalPriceReI = new JLabel("Total Price: ");
        
        breakDownPriceNightReI = new JLabel("Breakdown price per night: ");
        
        reservationInformationPanel.add(reservationIDReI);
        reservationInformationPanel.add(guestNameReI);
       
        reservationInformationPanel.add(roomNameReI);
        reservationInformationPanel.add(checkInReI);
        reservationInformationPanel.add(checkOutReI);
        reservationInformationPanel.add(totalPriceReI);
        reservationInformationPanel.add(breakDownPriceNightReI);
        
        
        //viewMiscInfoPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        viewMiscInfoPanel.add(hotelAvailabilityPanel, "view availability information");
        viewMiscInfoPanel.add(roomInformationPanel, "view room information");
        viewMiscInfoPanel.add(reservationInformationPanel, "view reservation information");
        
        
        
        
        
        
        
        //viewButtonsPanel.setMinimumSize(new Dimension((int)buttonSizes.getWidth()*3+10, 10 ));
        
        
        
        
        
        
        
        
        
        viewHotelTopPanel = new JPanel(new BorderLayout());
        viewHotelTopPanel.add(viewHotelInfoPanel, BorderLayout.CENTER);
        viewHotelTopPanel.add(viewButtonsPanel, BorderLayout.SOUTH);
        
        viewHotelInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
       
        viewHotelPanel.add(viewHotelTopPanel, BorderLayout.NORTH);
        viewHotelPanel.add(viewMiscInfoPanel, BorderLayout.CENTER);
        
        
        
        
        
        
        
        
        
        
        //viewHotelPanel.add(new JLabel("View Hotel"), BorderLayout.NORTH);

    }
    
    
    
   
    
    //public void
    
    
    public void showViewRoomInformation() {
    	viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view room information");
    }
    public void showViewAvailabilityInformation() {
    	viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view availability information");
    }
    public void showViewReservationInformation() {
    	viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view reservation information");
    }
    
    
    
    
    
    public String getSelectedHotel() {
    	return hotelList.getSelectedValue();
    }
    
    
    

    private void manageHotelInit(){
        manageHotelPanel = new JPanel(new BorderLayout());
    
        manageHotelPanel.add(new JLabel("Manage Hotel"), BorderLayout.NORTH);
    }

    private void simulateBookingInit(){
        simulateBookingPanel = new JPanel();
        
        simulateBookingPanel.setLayout(new GridLayout(0, 1));
        
        
        hotelNameFieldSB = new JTextField();
        guestNameFieldSB = new JTextField();
        discountFieldSB = new JTextField();
        
        
        standardButtonSB = new JToggleButton("Standard");
        deluxeButtonSB = new JToggleButton("Deluxe");
        executiveButtonSB = new JToggleButton("Executive");
        //bookButtonSB = new JButton("Book");
        
        
        //bookButtonSB.setActionCommand("new booking");
        standardButtonSB.setBackground(Color.LIGHT_GRAY);
        deluxeButtonSB.setBackground(Color.decode("0xA020F0"));
        executiveButtonSB.setBackground(Color.decode("0xFFD700"));
        
        
        standardButtonSB.setSize(buttonSizes);
        deluxeButtonSB.setSize(buttonSizes);
        executiveButtonSB.setSize(buttonSizes);
        
        roomTypePick = new ButtonGroup();
        
        roomTypePick.add(standardButtonSB);
        roomTypePick.add(deluxeButtonSB);
        roomTypePick.add(executiveButtonSB);
        
        
        //standardButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
       // deluxeButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
        //executiveButtonSB.setBorder(BorderFactory.createRaisedBevelBorder());
        
        JPanel roomTypePanel = new JPanel();
        
        roomTypePanel.setLayout(new GridLayout(1, 3));
        roomTypePanel.add(standardButtonSB);
        roomTypePanel.add(deluxeButtonSB);
        roomTypePanel.add(executiveButtonSB);
       
        
        simulateBookingPanel.add(new JLabel("Hotel Name: "));
        simulateBookingPanel.add(hotelNameFieldSB);
        simulateBookingPanel.add(new JLabel("Your Name: "));
        simulateBookingPanel.add(guestNameFieldSB);
        simulateBookingPanel.add(new JLabel("Pick a Room Type"));
        simulateBookingPanel.add(roomTypePanel);
        simulateBookingPanel.add(new JLabel("Discount Coupon "));
        simulateBookingPanel.add(discountFieldSB);
        simulateBookingPanel.add(blankLabel);
        //simulateBookingPanel.add(bookButtonSB);
        
        
        simulateBookingPanel.setSize(400, 600);
        simulateBookingPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
       
       
        
    }
    
    
    public void showViewMenu() {
    	
    	
    	
    	
    	
    	if(!hotelList.isEnabled()) {
    		hotelList.clearSelection();
    		hotelName.setText(" ");
            roomSize.setText("No. of Rooms:");
            standardRoomSize.setText("Standard: ");
            deluxeRoomSize.setText("     Deluxe: ");
            executiveRoomSize.setText("Executive: ");
            estimateEarnings.setText("Estimate Earnings: ");
    	}
    	hotelList.setEnabled(true);
    	/*
    	hotelName = new JLabel(" ");
        roomSize = new JLabel("No. of Rooms:");
        standardRoomSize = new JLabel("Standard: ");
        deluxeRoomSize = new JLabel("Deluxe: ");
        executiveRoomSize = new JLabel("Executive: ");
        estimateEarnings = new JLabel("Estimate Earnings: ");
    	
    	*/
    	
    	
    	
    	
    	mainInfoShower.show(cardMainInfoPanel, "View Hotel");
    	
    }
    
    public void showManageMenu() {
    	hotelList.setEnabled(true);
    	mainInfoShower.show(cardMainInfoPanel, "Manage Hotel");
    }
    
    
    public void showCreateMenu() {
		
		hotelList.setEnabled(false);
		hotelList.clearSelection();
		mainInfoShower.show(cardMainInfoPanel, "Create Hotel");
		
		
		
	 	nameField.setText("Enter Text");
        standardField.setText("0");
        deluxeField.setText("0");
        executiveField.setText("0");
        defaultPriceCheckBox.setSelected(true);
        priceField.setText("1299");
        priceField.setVisible(false);
        priceLabel.setVisible(false);
    //JOptionPane.showMessageDialog(this, "Create Hotel", "Hotel Creation", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void toggleDefaultPrice() {
		boolean selected = defaultPriceCheckBox.isSelected();
		priceField.setVisible(!selected);
        priceLabel.setVisible(!selected);
        if (selected) {
            priceField.setText("1299");
        } else {
            priceField.setText("100");
        }
	}
  
    
    public void showSimulation() {
    	
    	
    	
    	
    	hotelNameFieldSB.setText("");
        guestNameFieldSB.setText("");
        discountFieldSB.setText("");
        roomTypePick.clearSelection();
        //this.setEnabled(false);
    	//dialog.setVisible(true);
    	 Object[] options = {"Book"};
    	JOptionPane.showOptionDialog(
                null, // Parent component
                simulateBookingPanel, // Message
                "Booking Simulation++", // Title
                JOptionPane.DEFAULT_OPTION, // Option type
                JOptionPane.PLAIN_MESSAGE, // Message type
                null, // Icon (default)
                options, // Custom options
                options[0]); 
    	 //(this, "Enter your name!", "Booking Simulation++", JOptionPane.INFORMATION_MESSAGE);
    	
    	
    }
    public boolean isDefaultPriceCheckBoxSelected() {
		 return defaultPriceCheckBox.isSelected();
	}

	public String getHotelName() {
		return nameField.getText();
	}
	public String getStandardRoomNum() {
		return standardField.getText();
	}
	public String getDeluxeRoomNum() {
		return deluxeField.getText();
	}
	public String getExecutiveRoomNum() {
		return executiveField.getText();
	}
	public String getPrice() {
		return priceField.getText();
	}
	
	public void showPopup(String title, String message) {
		
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void fontsInit(){
      buttonFonts = new Font("Helvetica", Font.BOLD, 12);
      whiteButtonFonts = new Font("Arial", Font.PLAIN, 10);
    }

    private void dimensionInit(){
        buttonSizes = new Dimension(94, 30); //120, 30
        mainFrameSize = new Dimension(800, 600);
    }
    private void createHotelPanelInit() {
        createHotelPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        createHotelPanel.setLayout(layout);
        createHotelPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        createHotelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, getHeight() / 3, 0));

        nameField = new JTextField(20);
        standardField = new JTextField(5);
        deluxeField = new JTextField(5);
        executiveField = new JTextField(5);
        defaultPriceCheckBox = new JCheckBox("Default Price (1299)");
        priceField = new JTextField(10);
        priceLabel = new JLabel("Price (must be >= 100): ");
        createHotelSubmitButton = new JButton("Submit");
        createHotelSubmitButton.setActionCommand("CSubmit");
        
        JLabel nameLabel = new JLabel("Name: ");
        JLabel standardLabel = new JLabel("Standard: ");
        JLabel deluxeLabel = new JLabel("Deluxe: ");
        JLabel executiveLabel = new JLabel("Executive: ");
        
        
        nameLabel.setLabelFor(nameField);
        standardLabel.setLabelFor(standardField);
        executiveLabel.setLabelFor(executiveField);
        priceLabel.setLabelFor(priceField);
        // Adding components to the panel
        createHotelPanel.add(nameLabel);
        createHotelPanel.add(nameField);
        
        createHotelPanel.add(standardLabel);
        createHotelPanel.add(standardField);
        createHotelPanel.add(deluxeLabel);
        createHotelPanel.add(deluxeField);
        createHotelPanel.add(executiveLabel);
        createHotelPanel.add(executiveField);
        createHotelPanel.add(defaultPriceCheckBox);
        createHotelPanel.add(priceLabel);
        createHotelPanel.add(priceField);
        createHotelPanel.add(createHotelSubmitButton);
        
        //to whoever reads this, yes i did this manually... 
        //i was gonna use SpringUtilities.compactGrid from oracle's website, but turns out it's not part of JDK HAHAHAHAHAAA
        /*
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 0, SpringLayout.NORTH, nameField );
        layout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, createHotelPanel);
        layout.putConstraint(SpringLayout.EAST, nameLabel, 20, SpringLayout.WEST, nameField);
        layout.putConstraint(SpringLayout.EAST, nameField, -20, SpringLayout.EAST, createHotelPanel );
        layout.putConstraint(SpringLayout.NORTH, nameField, 50, SpringLayout.NORTH, createHotelPanel);
        
        */
        //layout.putConstraint(SpringLayout.WEST, nameField, 5, SpringLayout.EAST, nameLabel);
        
        SpringLayout.Constraints nameLabelCons = layout.getConstraints(nameLabel);
        nameLabelCons.setX(Spring.constant(5));
        nameLabelCons.setY(Spring.constant(5));

        SpringLayout.Constraints nameFieldCons = layout.getConstraints(nameField);
        nameFieldCons.setX(Spring.constant(100));
        nameFieldCons.setY(Spring.constant(5));

        SpringLayout.Constraints standardLabelCons = layout.getConstraints(standardLabel);
        standardLabelCons.setX(Spring.constant(5));
        standardLabelCons.setY(Spring.constant(35));

        SpringLayout.Constraints standardFieldCons = layout.getConstraints(standardField);
        standardFieldCons.setX(Spring.constant(100));
        standardFieldCons.setY(Spring.constant(35));

        SpringLayout.Constraints deluxeLabelCons = layout.getConstraints(deluxeLabel);
        deluxeLabelCons.setX(Spring.constant(5));
        deluxeLabelCons.setY(Spring.constant(65));

        SpringLayout.Constraints deluxeFieldCons = layout.getConstraints(deluxeField);
        deluxeFieldCons.setX(Spring.constant(100));
        deluxeFieldCons.setY(Spring.constant(65));

        SpringLayout.Constraints executiveLabelCons = layout.getConstraints(executiveLabel);
        executiveLabelCons.setX(Spring.constant(5));
        executiveLabelCons.setY(Spring.constant(95));

        SpringLayout.Constraints executiveFieldCons = layout.getConstraints(executiveField);
        executiveFieldCons.setX(Spring.constant(100));
        executiveFieldCons.setY(Spring.constant(95));

        SpringLayout.Constraints defaultPriceCheckBoxCons = layout.getConstraints(defaultPriceCheckBox);
        defaultPriceCheckBoxCons.setX(Spring.constant(5));
        defaultPriceCheckBoxCons.setY(Spring.constant(125));

        SpringLayout.Constraints priceLabelCons = layout.getConstraints(priceLabel);
        priceLabelCons.setX(Spring.constant(5));
        priceLabelCons.setY(Spring.constant(155));

        SpringLayout.Constraints priceFieldCons = layout.getConstraints(priceField);
        priceFieldCons.setX(Spring.constant(140));
        priceFieldCons.setY(Spring.constant(155));

        SpringLayout.Constraints submitButtonCons = layout.getConstraints(createHotelSubmitButton);
        submitButtonCons.setX(Spring.constant(5));
        submitButtonCons.setY(Spring.constant(185));
        
        
        defaultPriceCheckBox.setSelected(true);
        
        priceLabel.setVisible(false);
        priceField.setVisible(false);
    }



    private void mainInfoPanelInit(){
        cardMainInfoPanel = new JPanel(mainInfoShower); 
        cardMainInfoPanel.add(titlePanel, "Title");
        cardMainInfoPanel.add(createHotelPanel, "Create Hotel");
        cardMainInfoPanel.add(viewHotelPanel, "View Hotel");
        cardMainInfoPanel.add(manageHotelPanel, "Manage Hotel");
    }

    private void topToolBarInit(){
        topToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        createButton = new JToggleButton("Create Hotel");
        viewButton = new JToggleButton("View Hotel");
        manageButton = new JToggleButton("Manage Hotel");
        simulateButton = new JButton("Simulate");

        createButton.setPreferredSize(buttonSizes);
		viewButton.setPreferredSize(buttonSizes);
		manageButton.setPreferredSize(buttonSizes);
        simulateButton.setPreferredSize(buttonSizes);

        createButton.setFont(buttonFonts);
		viewButton.setFont(buttonFonts);
		manageButton.setFont(buttonFonts);
		simulateButton.setFont(buttonFonts);

		createButton.setFocusPainted(false);
		viewButton.setFocusPainted(false);
		manageButton.setFocusPainted(false);
        simulateButton.setFocusPainted(false);
		
		createButton.setBorderPainted(false);
		viewButton.setBorderPainted(false);
		manageButton.setBorderPainted(false);
        
        createButton.setBorder(BorderFactory.createRaisedBevelBorder());
        viewButton.setBorder(BorderFactory.createRaisedBevelBorder());
        manageButton.setBorder(BorderFactory.createRaisedBevelBorder());
        simulateButton.setBorder(BorderFactory.createRaisedBevelBorder());

        createButton.setBackground(Color.decode("0x353535"));
		createButton.setForeground(Color.decode("0xFFFFFF"));
		
		viewButton.setBackground(Color.decode("0x353535"));
		viewButton.setForeground(Color.decode("0xFFFFFF"));
		
		manageButton.setBackground(Color.decode("0x353535"));
		manageButton.setForeground(Color.decode("0xFFFFFF"));
		
		simulateButton.setBackground(Color.decode("0xB23A48"));
		simulateButton.setForeground(Color.decode("0xFFFFFF"));

        createButton.setActionCommand("create");
        viewButton.setActionCommand("view");
        manageButton.setActionCommand("manage");
        simulateButton.setActionCommand("simulate");


        ButtonGroup ttb = new ButtonGroup();

        ttb.add(createButton);
        ttb.add(viewButton);
        ttb.add(manageButton);
        //ttb.add(simulateButton);
        topToolBar.add(createButton);
        topToolBar.add(viewButton);
        topToolBar.add(manageButton);
        topToolBar.add(simulateButton);
        topToolBar.setBackground(Color.BLACK);
        //Color
       // topToolBar.setBackground(Color.decode("0xD9D9D9"));
        topToolBar.setBorder(BorderFactory.createRaisedBevelBorder());
        topToolBar.setVisible(true);
        topToolBar.setEnabled(true);
    }

	
	public void updateCardPanelSize() {
        int width = getWidth()/2;
        int height = getHeight()/2;
        cardMainInfoPanel.setPreferredSize(new Dimension(width, height));
        cardMainInfoPanel.revalidate();
        cardMainInfoPanel.repaint();
    }
	
	public void setActionListener(ActionListener listener) {
		createButton.addActionListener(listener);
		viewButton.addActionListener(listener);
		manageButton.addActionListener(listener);
        simulateButton.addActionListener(listener);

        //create hotel
        createHotelSubmitButton.addActionListener(listener);


		defaultPriceCheckBox.addActionListener(listener);
		
        //view hotel
			//view hotel availability listener
        	hotelAvailabilityButton.addActionListener(listener);
			reservationInformationButton.addActionListener(listener);
			roomInformationButton.addActionListener(listener);
		//availSubmit.addActionListener(listener);
			
			
	}
	
	public void setComponentListener(ComponentListener e) {
		addComponentListener(e);
	}
	
	public void setDocumentListener(DocumentListener listener) {
		
		nameField.getDocument().addDocumentListener(listener);
		standardField.getDocument().addDocumentListener(listener);
		deluxeField.getDocument().addDocumentListener(listener);
		executiveField.getDocument().addDocumentListener(listener);
		priceField.getDocument().addDocumentListener(listener);
		
		//availDate.getDocument().addDocumentListener(listener);
		
		
	}
	
	public void setListListener(ListSelectionListener listener) {
		hotelList.addListSelectionListener(listener);
	}


	public void showHotelInfo(String name, int roomSize2, int noOfStandardRooms, int noOfDeluxeRooms,
			int noOfExecutiveRooms, double estimate) {
        	
			hotelName.setText(name);
	        roomSize.setText("No. of Rooms: " + roomSize2);
	        standardRoomSize.setText("  Standard: " + noOfStandardRooms);
	        deluxeRoomSize.setText("     Deluxe: " + noOfDeluxeRooms);
	        executiveRoomSize.setText("Executive: " + noOfExecutiveRooms);
	        estimateEarnings.setText("Estimate Earnings: "  + estimate);
            
            
		
	}


	public void showNewBooking() {
		//dialog.dispose();
		//this.setEnabled(true);
		 JOptionPane.showMessageDialog(this, "Booking confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
