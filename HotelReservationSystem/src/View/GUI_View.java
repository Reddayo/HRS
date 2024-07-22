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
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

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


    private JSplitPane splitPane;
    private JPanel calendarPanel;
    private JPanel breakdownPanel;
    public GUI_View() {
        setLayout(new BorderLayout());
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
        basedPanel.add(viewMiscInfoPanel, BorderLayout.CENTER);
        roomListInit();
        reservationListInit();
        /*JPanel listPanel = new JPanel(listShower);
        
        listPanel.add(roomList, "room list");
        listPanel.add(reservationList, "reservationList");
        */
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,basedPanel, listScrollPane );
        add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(0.5);
        splitPane.setRightComponent( null);
        
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

    private JList<String> roomList;
    private JList<String> reservationList;
    private JScrollPane listScrollPane;
    private JScrollPane listScrollPane2 ;
    private JPanel availabilityCalendarPanelRoI;
    private JPanel breakDownPriceNightListReI;

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

    public boolean isReservationInformationSelected(){
        return reservationInformationButton.isSelected();
    }

    public void updateRoomList(DefaultListModel<String> listModel) {
	    roomList.setModel(listModel); 
	    listScrollPane.setViewportView(roomList); 
	}

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

        SpinnerModel model = new SpinnerNumberModel(1, 1, 31, 1); // initial value, min, max, step
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
       
        return underPanel;
    }


    public void setChangeListener(ChangeListener listener){
        dateSpinner.addChangeListener(listener);
    }


    private JPanel createRoomInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        roomNameRoI = new JLabel("Room Name: ");
        roomPriceRoI = new JLabel("Room Price: ");
        availabilityCalendarRoI = new JLabel("Availability Calendar: ");
        availabilityCalendarPanelRoI = new JPanel();
        panel.add(roomNameRoI);
        panel.add(roomPriceRoI);
        panel.add(availabilityCalendarRoI);
        panel.add(availabilityCalendarPanelRoI);
        panel.add(new JLabel("Note: Day 31 cannot be booked thereby blacked out"));

        return panel;
    }

    private JPanel createReservationInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        reservationIDReI = new JLabel("Reservation ID: ");
        guestNameReI = new JLabel("Guest Name: ");
        roomNameReI = new JLabel("Room Name: ");
        checkInReI = new JLabel("Check In: ");
        checkOutReI = new JLabel("Check Out: ");
        totalPriceReI = new JLabel("Total Price: ");
        discountReI = new JLabel("Discount Code: ");
        breakDownPriceNightReI = new JLabel("Breakdown price per night: ");
        breakDownPriceNightListReI = new JPanel();
        panel.add(reservationIDReI);
        panel.add(guestNameReI);
        panel.add(roomNameReI);
        panel.add(checkInReI);
        panel.add(checkOutReI);
        panel.add(totalPriceReI);
        panel.add(discountReI);
        panel.add(breakDownPriceNightReI);

        return panel;
    }

    public void showHotelInfo(String name, int roomSize2, int noOfStandardRooms, int noOfDeluxeRooms,
                              int noOfExecutiveRooms, double estimate) {
        hotelName.setText(name);
        roomSize.setText("No. of Rooms: " + roomSize2);
        standardRoomSize.setText("  Standard: " + noOfStandardRooms);
        deluxeRoomSize.setText("     Deluxe: " + noOfDeluxeRooms);
        executiveRoomSize.setText("Executive: " + noOfExecutiveRooms);
        estimateEarnings.setText(String.format("Estimate Earnings: %.2f", estimate));
    }

    public void showRoomInfo(String name, double roomPrice, boolean[] availabilityCalendar) {
        roomNameRoI.setText("Room Name: " + name);
        //ystem.out.println(name+roomPrice+availabilityCalendar);
        roomPriceRoI.setText("Room Price " + roomPrice);
        availabilityCalendarRoI.setText("Availablity Calendar: " );
        updateCalendarPanel(availabilityCalendar);
        revalidate();
        repaint();
    
    
    }
    public void showReservationInfo(String reservationID, 
                                     String guestName,
                                     String roomName, 
                                     int checkIn, 
                                     int checkOut,
                                     double totalPrice, 
                                     String discount,
                                     double[] breakDownPriceNight){
        reservationIDReI.setText("Reservation ID: " + reservationID);
        guestNameReI.setText("Guest Name: "+ guestName);
        roomNameReI.setText("Room Name: " + roomName);
        checkInReI.setText("Check In: " + checkIn);
        checkOutReI.setText("Check Out: " + checkOut);
        totalPriceReI.setText("Total Price: "+ String.format("%.2f", totalPrice));
        discountReI.setText("Discount Code: " + discount);
        updateBreakdownPriceNight(breakDownPriceNight, checkIn, checkOut);
    }

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





    
    public void setActionListener(ActionListener listener) {
        hotelAvailabilityButton.addActionListener(listener);
        roomInformationButton.addActionListener(listener);
        reservationInformationButton.addActionListener(listener);
    }

    public void showAvailabilityPanel() {
        viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view availability information");
        splitPane.setRightComponent( null);
    }

    public void showRoomPanel() {
        viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view room information");
        splitPane.setRightComponent(listScrollPane);
    }

    public void showReservationPanel() {
        viewHotelMiscInfoShower.show(viewMiscInfoPanel, "view reservation information");
        splitPane.setRightComponent(listScrollPane2);
    }

    public JSpinner getAvailabilitySpinner() {
        return dateSpinner;
    }

    public boolean isRoomInformationSelected() {
       return roomInformationButton.isSelected();
    }


    public String getSelectedRoom() {
        return roomList.getSelectedValue();
    }

    public JList<String> getRoomList() {
        return roomList;
    }


    public void resetRoomInfo() {
        roomNameRoI.setText("Room Name: ");
        roomPriceRoI.setText("Room Price: " );
        availabilityCalendarRoI.setText("Availablity Calendar: " );
        boolean[] blah = new boolean[31];
        for(int i = 0; i < 31; i++)
            blah[i] = true;
        updateCalendarPanel(blah);
    }


    public void resetReservationInfo() {
        reservationIDReI.setText("Reservation ID: ");
        guestNameReI.setText("Guest Name: ");
        roomNameReI.setText("Room Name: ");
        checkInReI.setText("Check In: " );
        checkOutReI.setText("Check Out: " );
        totalPriceReI.setText("Total Price: ");
        discountReI.setText("Discount Code: ");
        updateBreakdownPriceNight(new double[31], 0, -1);
    }


    public String getSelectedReservation() {
        return reservationList.getSelectedValue();
    }


    public JList<String> getReservationList() {
        return reservationList;
    }

    public void updateReservationList(DefaultListModel<String> listModel) {
        reservationList.setModel(listModel); 
	    listScrollPane2.setViewportView(reservationList); 
    }
}
