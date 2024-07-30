package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * GUI_View class is responsible for displaying the GUI components for viewing hotel information,
 * room details, and reservation information. 
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 */
public class GUI_View extends JPanel {

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 940514140031279123L;
	private JPanel viewHotelInfoPanel;
    private JPanel viewMiscInfoPanel;
    private JPanel viewButtonsPanel;
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
    private JLabel discountReI;
    private CardLayout viewHotelMiscInfoShower;
    private JSpinner dateSpinner;

    private JPanel selectSomething;
    private JSplitPane splitPane;
    private JPanel calendarPanel;
    private JPanel breakdownPanel;
    private CardLayout showInfo;
    
    /**
     * Constructs a new GUI_View instance, initializing the layout and components for the GUI.
     */
    public GUI_View() {
    	showInfo = new CardLayout();
        setLayout(showInfo);
        JPanel blankPanel = new JPanel();
        JLabel hotelaselect = new JLabel("Select a hotel");
        hotelaselect.setFont(new Font("Segoe UI", Font.PLAIN, 15));
       // blankPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 0));
        blankPanel.add(hotelaselect, BorderLayout.NORTH);
        		
        add(blankPanel, "blank");
        
        viewHotelMiscInfoShower = new CardLayout();
        viewMiscInfoPanel = new JPanel(viewHotelMiscInfoShower);
        viewHotelInfoPanel = createHotelInfoPanel();
        viewButtonsPanel = createButtonsPanel();
        hotelAvailabilityPanel = createHotelAvailabilityPanel();
        roomInformationPanel = createRoomInformationPanel();
        reservationInformationPanel = createReservationInformationPanel();
        calendarPanel = new JPanel();
        roomInformationPanel.add(calendarPanel);
         
        
        
        breakdownPanel = new JPanel();
        reservationInformationPanel.add(breakdownPanel);
        viewMiscInfoPanel.add(hotelAvailabilityPanel, "view availability information");
        viewMiscInfoPanel.add(roomInformationPanel, "view room information");
        viewMiscInfoPanel.add(reservationInformationPanel, "view reservation information");
        
        viewHotelTopPanel = new JPanel(new BorderLayout());
        viewHotelTopPanel.add(viewHotelInfoPanel, BorderLayout.CENTER);
        viewHotelTopPanel.add(viewButtonsPanel, BorderLayout.SOUTH);
        
        


        JPanel basedPanel = new JPanel(new BorderLayout());


        basedPanel.add(viewHotelTopPanel, BorderLayout.NORTH);
        
        roomListInit();
        reservationListInit();
        
       // roomInformationPanel.setMaximumSize(new Dimension(300, 300));
        /*JPanel listPanel = new JPanel(listShower);
        
        listPanel.add(roomList, "room list");
        listPanel.add(reservationList, "reservationList");
        */
         
        selectSomething = new JPanel();
        selectSomething.add(new JLabel("Press one of the three buttons to show information"));
        listScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        listScrollPane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));
        viewMiscInfoPanel.add(selectSomething, "show nothing");
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, viewMiscInfoPanel, listScrollPane );
        
       listScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
    	    @Override
    	    protected void configureScrollBarColors() {
    	        this.thumbColor = new Color(205, 205, 205);
    	        //this.incrButton.setBackground(thumbColor);
    	    }
    	    
    	    @Override
    	    protected JButton createDecreaseButton(int orientation) {
    	        JButton button = super.createDecreaseButton(orientation);
    	        button.setBackground(new Color(240, 240, 240));
    	        button.setForeground(new Color(77, 77, 77));
    	        button.setBorder(null);
    	        return button;
    	    }

    	    @Override
    	    protected JButton createIncreaseButton(int orientation) {
    	        JButton button = super.createIncreaseButton(orientation);
    	        button.setBackground(new Color(240, 240, 240));
    	        button.setForeground(new Color(240, 240, 240));
    	        button.setBorder(null);
    	        return button;
    	    }
    	});
        add(basedPanel,"hotel info");
        splitPane.setDividerLocation(0.6);
       // splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.6);
        
        splitPane.setRightComponent( null);
        basedPanel.add(splitPane, BorderLayout.CENTER);
        splitPane.setUI(new BasicSplitPaneUI() 
        {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() 
            {
                return new BasicSplitPaneDivider(this) 
                {                
                    /**
					 * 
					 */
					private static final long serialVersionUID = 6804859109026597907L;

					public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g) 
                    {
                        g.setColor(new Color(221, 221, 221));
                        g.fillRect(0, 0, getSize().width,  getSize().height);
                        super.paint(g);
                    }
                };
            }
        });

        splitPane.setBorder(null);
        //listScrollPane.setVisible(false);
    }
    
    
    
    /**
     * Displays the hotel information panel.
     */
    public void showInfo() {
    	showInfo.show(this, "hotel info");
    }
    
    /**
     * Displays a blank panel when no information is available.
     */
    public void noInfo() {
    	showInfo.show(this, "blank");
    }
    
    /**
     * Shows nothing in the view panel.
     */
    public void shownothing() {
    	viewHotelMiscInfoShower.show(viewMiscInfoPanel, "show nothing");
    	
    }
    
    /**
     * Sets the visibility of the list components.
     * 
     * @param bool the visibility state to set for the lists
     */
    public void setVisibleList(boolean bool) {
    	listScrollPane.setVisible(bool);
    	listScrollPane2.setVisible(bool);
    }

    private JList<String> roomList;
    private JList<String> reservationList;
    private JScrollPane listScrollPane;
    private JScrollPane listScrollPane2 ;
    private JPanel availabilityCalendarPanelRoI;
    private JPanel breakDownPriceNightListReI;
	private JLabel roomTypeRoI;
	private JLabel roomTypeReI;

	
	 /**
     * Initializes the room list component.
     */
    public void roomListInit() {
        roomList = new JList<String>();
		listScrollPane = new JScrollPane(roomList);
		roomList.setModel(new DefaultListModel<String>());
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//list.setCellRenderer();
		roomList.setVisible(true);
		//hotelList.setEnabled(false);
		int lastIndex = -1;
		roomList.setSelectedIndex(lastIndex);
		roomList.ensureIndexIsVisible(lastIndex);
		roomList.setEnabled(true);
		listScrollPane.setViewportView(roomList);
	}

    
    /**
     * Initializes the reservation list component.
     */
    public void reservationListInit() {
        reservationList = new JList<String>();
		listScrollPane2 = new JScrollPane(reservationList);
		reservationList.setModel(new DefaultListModel<String>());
		reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//list.setCellRenderer();
		reservationList.setVisible(true);
		//hotelList.setEnabled(false);
		int lastIndex = -1;
		reservationList.setSelectedIndex(lastIndex);
		reservationList.ensureIndexIsVisible(lastIndex);
		reservationList.setEnabled(true);
		listScrollPane2.setViewportView(reservationList);
	}

    
    /**
     * Checks if the reservation information button is selected.
     * 
     * @return true if the reservation information button is selected, false otherwise
     */
    public boolean isReservationInformationSelected(){
        return reservationInformationButton.isSelected();
    }

    
    /**
     * Updates the room list with a new model.
     * 
     * @param listModel the model containing room data
     */
    public void updateRoomList(DefaultListModel<String> listModel) {
	    roomList.setModel(listModel); 
	    listScrollPane.setViewportView(roomList); 
	}
    
    /**
     * Creates and returns a JPanel with hotel information components.
     * 
     * @return the JPanel with hotel information
     */

    private JPanel createHotelInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        hotelName = new JLabel(" ");
        roomSize = new JLabel("No. of Rooms:");
        standardRoomSize = new JLabel("Standard: ");
        deluxeRoomSize = new JLabel("Deluxe: ");
        executiveRoomSize = new JLabel("Executive: ");
        estimateEarnings = new JLabel("Estimate Earnings: ");

        JLabel hotelInfoLabel = new JLabel("Hotel Information");
        hotelName.setFont(new Font("Consolas", Font.BOLD, 30));
        roomSize.setFont(new Font("Consolas", Font.BOLD, 20));

        panel.add(hotelInfoLabel);
        panel.add(hotelName);
        panel.add(roomSize);
        panel.add(standardRoomSize);
        panel.add(deluxeRoomSize);
        panel.add(executiveRoomSize);
        panel.add(estimateEarnings);
        panel.add(new JLabel(" "));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        panel.setBackground(Color.decode("0xFFFFFF"));
        return panel;
    }

    /**
     * Creates and returns a JPanel with buttons for selecting different views.
     * 
     * @return the JPanel with view selection buttons
     */
    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        viewHotelButtonGroup = new ButtonGroup();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        hotelAvailabilityButton = createToggleButton("Availability Information", "view availability information");
        roomInformationButton = createToggleButton("Room Information", "view room information");
        reservationInformationButton = createToggleButton("Reservation Information", "view reservation information");
        viewHotelButtonGroup.add(hotelAvailabilityButton);
        viewHotelButtonGroup.add(roomInformationButton);
        viewHotelButtonGroup.add(reservationInformationButton);
        panel.add(hotelAvailabilityButton, gbc);
        gbc.gridx = 1;
        panel.add(roomInformationButton, gbc);
        gbc.gridx = 2;
        panel.add(reservationInformationButton, gbc);

        panel.setBackground(Color.decode("0x646464"));
        return panel;
    }

    
    /**
     * Creates and returns a JToggleButton with specified text and action command.
     * 
     * @param text the text to display on the button
     * @param actionCommand the action command for the button
     * @return the created JToggleButton
     */
    private JToggleButton createToggleButton(String text, String actionCommand) {
        JToggleButton button = new JToggleButton(text);
        button.setActionCommand(actionCommand);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Helvetica", Font.PLAIN, 12));
        button.setBackground(Color.decode("0x646464"));
        button.setForeground(Color.decode("0xFFFFFF"));
        return button;
    }
    
    
    /**
     * Creates and returns a JPanel with hotel availability information components.
     * 
     * @return the JPanel with hotel availability information
     */

    private JPanel createHotelAvailabilityPanel() {
        JPanel underPanel = new JPanel();
        underPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        bookedRooms = new JLabel("Total No. of Booked Rooms: ", SwingConstants.LEFT);
        bookedStandardRooms = new JLabel("   Standard: ");
        bookedDeluxeRooms = new JLabel("      Deluxe: ");
        bookedExecutiveRooms = new JLabel("  Executive: ");
        availableRooms = new JLabel("Total No. of Available Rooms: ");
        availableStandardRooms = new JLabel("   Standard: ");
        availableDeluxeRooms = new JLabel("      Deluxe: ");
        availableExecutiveRooms = new JLabel("  Executive:  ");

        SpinnerModel model = new SpinnerNumberModel(1, 1, 30, 1); // initial value, min, max, step
        dateSpinner = new JSpinner(model);

        panel.add(bookedRooms);
        panel.add(bookedStandardRooms);
        panel.add(bookedDeluxeRooms);
        panel.add(bookedExecutiveRooms);
        panel.add(new JLabel(" "));
        panel.add(availableRooms);
        panel.add(availableStandardRooms);
        panel.add(availableDeluxeRooms);
        panel.add(availableExecutiveRooms);

        underPanel.add(panel, BorderLayout.CENTER);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        JPanel someButtonPanel = new JPanel();
        someButtonPanel.setLayout(new GridLayout(1, 3));

        someButtonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));

        someButtonPanel.setAlignmentX(CENTER_ALIGNMENT);
        someButtonPanel.add(new JLabel("Choose Date: "));
        someButtonPanel.add(dateSpinner);
        someButtonPanel.add(new JLabel(" "));



        underPanel.add(someButtonPanel, BorderLayout.NORTH);
        underPanel.add(new JLabel("Note: The 31st is unavailable for check-in."), BorderLayout.SOUTH);
        underPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
        return underPanel;
    }

    
    /**
     * Sets the change listener for the date spinner.
     * 
     * @param listener the ChangeListener to be set
     */

    public void setChangeListener(ChangeListener listener){
        dateSpinner.addChangeListener(listener);
    }

    /**
     * Creates and returns a JPanel with room information components.
     * 
     * @return the JPanel with room information
     */
    private JPanel createRoomInformationPanel() {
        JPanel mainPanel = new JPanel();
        JPanel roomDetailsPanel = new JPanel();
        JPanel availabilityPanel = new JPanel();
        JPanel notePanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        roomDetailsPanel.setLayout(new GridLayout(3, 1)); 
        availabilityPanel.setLayout(new BorderLayout());
        notePanel.setLayout(new BorderLayout());

        roomNameRoI = new JLabel("Room Name: ");
        roomTypeRoI = new JLabel("Room Type: ");
        roomPriceRoI = new JLabel("Room Base Price: ");
        availabilityCalendarRoI = new JLabel("Availability Calendar: ");
        availabilityCalendarPanelRoI = new JPanel();

        roomDetailsPanel.add(roomNameRoI);
        roomDetailsPanel.add(roomTypeRoI);
        roomDetailsPanel.add(roomPriceRoI);
        
        availabilityPanel.add(availabilityCalendarRoI, BorderLayout.NORTH);
        availabilityPanel.add(availabilityCalendarPanelRoI, BorderLayout.CENTER);
        notePanel.add(new JLabel("Note: Day 31 cannot be checked-in thereby blacked out"), BorderLayout.SOUTH);
        
        mainPanel.add(roomDetailsPanel, BorderLayout.NORTH);
        mainPanel.add(availabilityPanel, BorderLayout.CENTER);
        mainPanel.add(notePanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return mainPanel;
    }

    /**
     * Creates and returns a JPanel with reservation information components.
     * 
     * @return the JPanel with reservation information
     */
    private JPanel createReservationInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        reservationIDReI = new JLabel("Reservation ID: ");
        guestNameReI = new JLabel("Guest Name: ");
        roomNameReI = new JLabel("Room Name: ");
        roomTypeReI = new JLabel("Room Type: ");
        checkInReI = new JLabel("Check In: ");
        checkOutReI = new JLabel("Check Out: ");
        totalPriceReI = new JLabel("Total Price: ");
        discountReI = new JLabel("Discount Code: ");
        breakDownPriceNightReI = new JLabel("Breakdown price per night: ");
        breakDownPriceNightListReI = new JPanel();
        panel.add(reservationIDReI);
        panel.add(guestNameReI);
        panel.add(roomNameReI);
        panel.add(roomTypeReI);
        panel.add(checkInReI);
        panel.add(checkOutReI);
        panel.add(totalPriceReI);
        panel.add(discountReI);
        panel.add(breakDownPriceNightReI);

        return panel;
    }

    /**
     * Displays hotel information.
     * 
     * @param name the hotel name
     * @param roomSize2 the total number of rooms
     * @param noOfStandardRooms the number of standard rooms
     * @param noOfDeluxeRooms the number of deluxe rooms
     * @param noOfExecutiveRooms the number of executive rooms
     * @param estimate the estimated earnings
     */
    public void showHotelInfo(String name, int roomSize2, int noOfStandardRooms, int noOfDeluxeRooms,
                              int noOfExecutiveRooms, double estimate) {
        hotelName.setText(name);
        roomSize.setText("No. of Rooms: " + roomSize2);
        standardRoomSize.setText("  Standard: " + noOfStandardRooms);
        deluxeRoomSize.setText("     Deluxe: " + noOfDeluxeRooms);
        executiveRoomSize.setText("Executive: " + noOfExecutiveRooms);
        estimateEarnings.setText(String.format("Estimate Earnings: %.2f", estimate));
    }

    /**
     * Displays room information.
     * 
     * @param name the room name
     * @param roomType the type of the room
     * @param roomPrice the base price of the room
     * @param availabilityCalendar an array indicating the availability of the room
     */
    public void showRoomInfo(String name, String roomType, double roomPrice, boolean[] availabilityCalendar) {
        roomNameRoI.setText("Room Name: " + name);
        roomTypeRoI.setText("Room Type: " + roomType);
        //ystem.out.println(name+roomPrice+availabilityCalendar);
        roomPriceRoI.setText("Room Price: " + (String.format("%.2f", roomPrice)));
        availabilityCalendarRoI.setText("Availablity Calendar: " );
        updateCalendarPanel(availabilityCalendar);
        revalidate();
        repaint();
    
    
    }
    
    /**
     * Displays reservation information.
     * 
     * @param reservationID the reservation ID
     * @param guestName the guest's name
     * @param roomName the room name
     * @param roomType the room type
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     * @param totalPrice the total price of the reservation
     * @param discount the discount code used
     * @param breakDownPriceNight the breakdown of the price per night
     */
    public void showReservationInfo(String reservationID, 
                                     String guestName,
                                     String roomName, 
                                     String roomType,
                                     int checkIn, 
                                     int checkOut,
                                     double totalPrice, 
                                     String discount,
                                     double[] breakDownPriceNight){
        reservationIDReI.setText("Reservation ID: " + reservationID);
        guestNameReI.setText("Guest Name: "+ guestName);
        roomNameReI.setText("Room Name: " + roomName);
        roomTypeReI.setText("Room Type: " + roomType);
        checkInReI.setText("Check In: " + checkIn);
        checkOutReI.setText("Check Out: " + checkOut);
        totalPriceReI.setText("Total Price: "+ String.format("%.2f", totalPrice));
        discountReI.setText("Discount Code: " + discount);
        updateBreakdownPriceNight(breakDownPriceNight, checkIn, checkOut);
    }

    
    /**
     * Updates the breakdown price per night in the reservation panel.
     * 
     * @param breakDownPriceNight an array of price breakdowns per night
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     */
    private void updateBreakdownPriceNight(double[] breakDownPriceNight, int checkIn, int checkOut ) {
        // Remove the old panel
        breakdownPanel.remove(breakDownPriceNightListReI);

        // Create and add the new panel
        breakDownPriceNightListReI = createBreakdownPanel(breakDownPriceNight, checkIn, checkOut);
        breakdownPanel.add(breakDownPriceNightListReI);

        // Revalidated and repaint to update the UI
        breakdownPanel.revalidate();
        breakdownPanel.repaint();
    }



    /**
     * Updates the availability calendar panel with new data.
     * 
     * @param availabilityCalendar an array indicating the availability of rooms
     */

    private void updateCalendarPanel(boolean[] availabilityCalendar) {
        // Remove the old panel
        calendarPanel.removeAll();
        // Create and add the new panel
        availabilityCalendarPanelRoI = createCalendarPanel(availabilityCalendar);
        calendarPanel.add(availabilityCalendarPanelRoI);
        // Revalidated and repaint to update the UI
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
    
    
    /**
     * Creates and returns a JPanel representing a calendar view of room availability.
     * 
     * @param availabilityArray an array indicating the availability of rooms
     * @return the JPanel representing the calendar view
     */
    private JPanel createCalendarPanel(boolean[] availabilityArray) {
        int SQUARE_SIZE = 50;
        return new JPanel() {
            

			/**
			 * 
			 */
			private static final long serialVersionUID = 1910073217049470011L;

			{
                setPreferredSize(new Dimension(SQUARE_SIZE * 7, SQUARE_SIZE * 5));
                setBackground(Color.WHITE);
                setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                drawCalendar(g2d);
            }

            private void drawCalendar(Graphics2D g2d) {
                g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
                
                for (int i = 0; i < 31; i++) {
                    int row = i / 7;
                    int col = i % 7;
                    int x = col * SQUARE_SIZE;
                    int y = row * SQUARE_SIZE;

                    // Draw white square
                    if(!availabilityArray[i]){
                        g2d.setColor(new Color(255, 90, 90)); // Lightish red
                    }else if(i == 30) {      
                    	g2d.setColor(Color.GRAY);
                    	
                    }else{
                    	g2d.setColor(Color.WHITE);
                    }
                    g2d.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g2d.setColor(new Color(200, 200, 200, 150)); // Faint gray border with transparency
                    g2d.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                    

                    // Draw day number
                    g2d.setColor(!availabilityArray[i] ? Color.WHITE : Color.BLACK);
                    g2d.drawString(String.valueOf(i + 1), x + 18, y + 30);
                }
            }
        };
    }
    /**
     * Updates the hotel availability information displayed in the panel.
     * 
     * @param bookedRooms the total number of booked rooms
     * @param bookedStandardRooms the number of booked standard rooms
     * @param bookedDeluxeRooms the number of booked deluxe rooms
     * @param bookedExecutiveRooms the number of booked executive rooms
     * @param availableRooms the total number of available rooms
     * @param availableStandardRooms the number of available standard rooms
     * @param availableDeluxeRooms the number of available deluxe rooms
     * @param availableExecutiveRooms the number of available executive rooms
     */

    public void updateAvailability(int bookedRooms,
                                   int bookedStandardRooms, 
                                   int bookedDeluxeRooms, 
                                   int bookedExecutiveRooms, 
                                   int availableRooms,
                                   int availableStandardRooms, 
                                   int availableDeluxeRooms, 
                                   int availableExecutiveRooms
                                   ){
        this.bookedRooms.setText("Total No. of Booked Rooms: " + bookedRooms);
        this.bookedStandardRooms .setText("   Standard: "+bookedStandardRooms);
        this.bookedDeluxeRooms.setText("      Deluxe: "+bookedDeluxeRooms);
        this.bookedExecutiveRooms.setText("  Executive: "+bookedExecutiveRooms);
        this.availableRooms.setText("Total No. of Available Rooms: "+availableRooms);
        this.availableStandardRooms.setText("   Standard: "+availableStandardRooms);
        this.availableDeluxeRooms.setText("      Deluxe: "+availableDeluxeRooms);
        this.availableExecutiveRooms.setText("  Executive:  "+availableExecutiveRooms);
    }
    /**
     * Creates and returns a JPanel representing the breakdown of prices per night.
     * 
     * @param percentages an array of percentages representing the breakdown of prices
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     * @return the JPanel representing the price breakdown
     */
    public JPanel createBreakdownPanel(double[] percentages, int checkIn, int checkOut) {
        JPanel priceModPanel = new JPanel();

        priceModPanel.setLayout(new FlowLayout());
        
        // Convert percentages to a String array
        String[] percentStrings = new String[checkOut - checkIn +1];
        for (int i = checkIn-1, j = 0; i < checkOut-1; i++, j++) {
            percentStrings[j] = String.format("Day %-2d -> Day %-2d = %6.2f", i + 1, i + 2, percentages[j]);
        }
        
        // Create the JList with the percentage strings
        JList<String> percentList = new JList<>(percentStrings);
        percentList.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set monospaced font
        percentList.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 10)); // Add some padding

        // Create a JScrollPane to contain the JList
        JScrollPane scrollPane = new JScrollPane(percentList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        priceModPanel.add(scrollPane, BorderLayout.CENTER);
        return priceModPanel;
    }


    /**
     * Sets the action listener for the view toggle buttons.
     * 
     * @param listener the ActionListener to be set
     */
    
    public void setActionListener(ActionListener listener) {
        hotelAvailabilityButton.addActionListener(listener);
        roomInformationButton.addActionListener(listener);
        reservationInformationButton.addActionListener(listener);
    }
    
    /**
     * Displays the hotel availability panel.
     */
    public void showAvailabilityPanel() {
    	resetInfo();
        viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view availability information");
        
        splitPane.setRightComponent( null);
    }
    /**
     * Displays the room information panel.
     */
    public void showRoomPanel() {
    	resetInfo();
    		viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view room information");
     
    	   splitPane.setRightComponent(listScrollPane);
    }
	
    /**
     * Clears the information displayed in the room, reservation, and availability panels,
     * and display reservation panel in miscinfopanel
     * 
     */
    public void showReservationPanel() {
    	resetInfo();
    	viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view reservation information");
        splitPane.setRightComponent(listScrollPane2);
    }

    /**
     * Gets the availability spinner used to select dates.
     * 
     * @return the JSpinner component used for date selection.
     */
    public JSpinner getAvailabilitySpinner() {
        return dateSpinner;
    }
    
    /**
     * Checks if the room information button is selected.
     * 
     * @return true if the room information button is selected, false otherwise.
     */
    public boolean isRoomInformationSelected() {
       return roomInformationButton.isSelected();
    }

    /**
     * Gets the currently selected room from the room list.
     * 
     * @return the selected room name, or null if no room is selected.
     */
    public String getSelectedRoom() {
        return roomList.getSelectedValue();
    }

    /**
     * Gets the list of rooms available in the UI.
     * 
     * @return the JList component displaying room names.
     */
    public JList<String> getRoomList() {
        return roomList;
    }

    /**
     * Resets the room information displayed in the UI to default values.
     * Clears the room name, type, price, and availability calendar.
     */
    public void resetRoomInfo() {
        roomNameRoI.setText("Room Name: ");
        roomTypeRoI.setText("Room Type: ");
        roomPriceRoI.setText("Room Price: " );
        availabilityCalendarRoI.setText("Availablity Calendar: " );
        boolean[] blah = new boolean[31];
        for(int i = 0; i < 31; i++)
            blah[i] = true;
        updateCalendarPanel(blah);
    }
    
    /**
     * Resets the reservation information displayed in the UI to default values.
     * Clears the reservation ID, guest name, room details, check-in/check-out dates, 
     * total price, and discount code.
     */

    public void resetReservationInfo() {
        reservationIDReI.setText("Reservation ID: ");
        guestNameReI.setText("Guest Name: ");
        roomNameReI.setText("Room Name: ");
        roomTypeReI.setText("Room Type: ");
        checkInReI.setText("Check In: " );
        checkOutReI.setText("Check Out: " );
        totalPriceReI.setText("Total Price: ");
        discountReI.setText("Discount Code: ");
        updateBreakdownPriceNight(new double[31], 0, -1);
    }

    /**
     * Resets the information displayed for room, reservation, and availability panels.
     * Calls the specific reset methods for each type of information.
     */
    public void resetInfo() {
    	
    	resetRoomInfo();
    	resetReservationInfo();
    	resetAvailabilityInfo();
    }
    
    /**
     * Clears the selection of any buttons in the view hotel button group.
     */
    public void clearButtonSelection() {
    	
    	viewHotelButtonGroup.clearSelection();
    
    }
    
    /**
     * Resets the availability information displayed.
     */
    public void resetAvailabilityInfo() {
    	this.bookedRooms.setText("Total No. of Booked Rooms: ");
        this.bookedStandardRooms .setText("   Standard: ");
        this.bookedDeluxeRooms.setText("      Deluxe: ");
        this.bookedExecutiveRooms.setText("  Executive: ");
        this.availableRooms.setText("Total No. of Available Rooms: ");
        this.availableStandardRooms.setText("   Standard: ");
        this.availableDeluxeRooms.setText("      Deluxe: ");
        this.availableExecutiveRooms.setText("  Executive:  ");
        this.dateSpinner.setValue(1);
    }
    
    /**
     * Gets the currently selected reservation from the reservation list.
     * 
     * @return the selected reservation ID or description, or null if no reservation is selected.
     */	
    
    public String getSelectedReservation() {
        return reservationList.getSelectedValue();
    }

    /**
     * Gets the list of reservations available in the UI.
     * 
     * @return the JList component displaying reservation IDs or descriptions.
     */
    public JList<String> getReservationList() {
        return reservationList;
    }
    
    /**
     * updates the list model to the reservation list component and updates the view.
     * 
     * @param listModel the new DefaultListModel to set for the reservation list.
     */

    public void updateReservationList(DefaultListModel<String> listModel) {
        reservationList.setModel(listModel); 
	    listScrollPane2.setViewportView(reservationList); 
    }
}
