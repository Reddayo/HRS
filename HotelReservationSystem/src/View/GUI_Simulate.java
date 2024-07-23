package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ButtonGroup;


public class GUI_Simulate extends JDialog {

    private static final long serialVersionUID = -2149736069481276153L;
    private JLabel checkInLabel;
    private JLabel checkOutLabel;
    private JToggleButton standardButtonSB;
    private JToggleButton deluxeButtonSB;
    private JToggleButton executiveButtonSB;
    private ButtonGroup roomTypePick;
    private JTextField discountFieldSB;
    private JButton bookButtonSB;
    private JButton[] dayButtons;
    private int checkInDay = 0;
    private int checkOutDay = 0;
    private boolean selectingCheckIn = true; 
    private JTextField guestNameFieldSB;
    private JPanel panel;
    private JPanel calendarPanel;
    private JPanel rightPanel;
    
    private JLabel errorLabel;

    GUI_Simulate(JFrame owner) {
        super(owner, "Simulate Booking", true);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        checkInLabel = new JLabel("Check-in: None");
        checkOutLabel = new JLabel("Check-out: None");

        
        standardButtonSB = new JToggleButton("Standard");
        deluxeButtonSB = new JToggleButton("Deluxe");
        executiveButtonSB = new JToggleButton("Executive");
        
        standardButtonSB.setBackground(Color.LIGHT_GRAY);
        deluxeButtonSB.setBackground(Color.decode("0xA020F0"));
        executiveButtonSB.setBackground(Color.decode("0xFFD700"));

        bookButtonSB = new JButton("Book");
        discountFieldSB = new JTextField(10);
        roomTypePick = new ButtonGroup();
        roomTypePick.add(standardButtonSB);
        roomTypePick.add(deluxeButtonSB);
        roomTypePick.add(executiveButtonSB);

     
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        JLabel guestNameLabelSB = new JLabel("Guest Name:");
        guestNameFieldSB = new JTextField(15);
        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setLayout(new GridBagLayout());
        GridBagConstraints roomTypeGbc = new GridBagConstraints();
        roomTypeGbc.insets = new Insets(5, 5, 5, 5);
        roomTypeGbc.anchor = GridBagConstraints.WEST;

        roomTypeGbc.gridx = 0;
        roomTypePanel.add(standardButtonSB, roomTypeGbc);

        roomTypeGbc.gridx = 1;
        roomTypePanel.add(deluxeButtonSB, roomTypeGbc);
        roomTypeGbc.gridx = 2;
        roomTypePanel.add(executiveButtonSB, roomTypeGbc);
        calendarPanel = createCalendarPanel();

      
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.insets = new Insets(5, 5, 5, 5); 
        rightGbc.fill = GridBagConstraints.HORIZONTAL; 

     
        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightPanel.add(guestNameLabelSB, rightGbc);

        rightGbc.gridy = 1;
        rightPanel.add(guestNameFieldSB, rightGbc);
        
        rightGbc.gridx = 0;
        rightGbc.gridy = 2;
        rightPanel.add(new JLabel("Pick a Room Type"), rightGbc);

        rightGbc.gridy = 3;
        rightPanel.add(roomTypePanel, rightGbc);

        rightGbc.gridy = 5;
        rightPanel.add(new JLabel("Discount Coupon "), rightGbc);

        rightGbc.gridy = 6;
        rightPanel.add(discountFieldSB, rightGbc);

        rightGbc.gridy = 7;
        rightPanel.add(bookButtonSB, rightGbc);

        rightGbc.gridy = 8;
        rightPanel.add(errorLabel, rightGbc); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Select Day (Check-in / Check-out): "), gbc);

        gbc.gridy = 1;
        panel.add(calendarPanel, gbc);

        gbc.gridy = 2;
        panel.add(checkInLabel, gbc);

        gbc.gridy = 3;
        panel.add(checkOutLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 7; 
        gbc.fill = GridBagConstraints.BOTH; 
        panel.add(rightPanel, gbc);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.add(panel);
        this.setSize(800, 600); 
        this.setResizable(true);
        addDocumentListener();
    }


    private JPanel createCalendarPanel() {
        JPanel calendarPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        calendarPanel.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2); 

 
        dayButtons = new JButton[31];
        for (int i = 0; i < 31; i++) {
            int day = i + 1;
            dayButtons[i] = new JButton(String.valueOf(day));
            dayButtons[i].setPreferredSize(new Dimension(60, 60)); 
            dayButtons[i].setFont(new Font("Arial", Font.BOLD, 16)); 
            dayButtons[i].setBackground(Color.WHITE);
            dayButtons[i].addActionListener(new CalendarButtonListener(day));

            gbc.gridx = i % 7; 
            gbc.gridy = (i / 7) + 1; 

            calendarPanel.add(dayButtons[i], gbc);
        }

        return calendarPanel;
    }

    private class CalendarButtonListener implements ActionListener {
        private int day;

        public CalendarButtonListener(int day) {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectingCheckIn) {
                handleCheckInSelection();
            } else {
                handleCheckOutSelection();
            }

            updateButtonStates();
        }

        private void handleCheckInSelection() {
            if (checkInDay != 0) {
                dayButtons[checkInDay - 1].setBackground(null); 
            }

            checkInDay = day;
            checkInLabel.setText("Check-in: Day " + day);
            dayButtons[day - 1].setBackground(Color.GREEN); 

           
            if (checkOutDay != 0 && checkOutDay <= checkInDay) {
                dayButtons[checkOutDay - 1].setBackground(null);
                checkOutDay = 0;
                checkOutLabel.setText("Check-out: None");
            }

            selectingCheckIn = false;
        }

        private void handleCheckOutSelection() {
            if (checkOutDay != 0) {
                dayButtons[checkOutDay - 1].setBackground(null); 
            }

            checkOutDay = day;
            checkOutLabel.setText("Check-out: Day " + day);
            dayButtons[day - 1].setBackground(Color.RED); 

            if (checkInDay != 0 && checkOutDay <= checkInDay) {
                dayButtons[checkInDay - 1].setBackground(null);
                checkInDay = checkOutDay;
                checkInLabel.setText("Check-in: Day " + checkOutDay);
                dayButtons[checkOutDay - 1].setBackground(Color.GREEN);
            }

            selectingCheckIn = true; 
        }
    }

   
    

    private void addDocumentListener() {
        discountFieldSB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkDiscountCode();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkDiscountCode();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
              
            }
        });
    }

    private void checkDiscountCode() {
        String text = discountFieldSB.getText().trim();
        
        if (text.equals("I_WORK_HERE") || text.equals("STAY4_GET1") || text.equals("PAYDAY")) {
            errorLabel.setText("Valid discount code entered: " + text);
            errorLabel.setForeground(Color.GREEN);
            bookButtonSB.setEnabled(true);
          
        } else if (text.isEmpty()){
        	errorLabel.setText("");
        	bookButtonSB.setEnabled(true);
        }else {
        	errorLabel.setText("Invalid discount code.");
       	 	errorLabel.setForeground(Color.RED);
       	 	bookButtonSB.setEnabled(false);
        }
    }

    private void updateButtonStates() {
        for (int i = 0; i < 31; i++) {
            if (i + 1 != checkInDay && i + 1 != checkOutDay) {
                dayButtons[i].setBackground(Color.WHITE); 
            }
        }
        if (checkInDay == checkOutDay || checkInDay == 0 || checkOutDay == 0) {
            bookButtonSB.setEnabled(false);
        } else {
            bookButtonSB.setEnabled(true);
        }
    }

 
    public void setActionListener(ActionListener listener) {
        bookButtonSB.addActionListener(listener);
    }

    public int getCheckInDay() {
        return checkInDay;
    }

    public int getCheckOutDay() {
        return checkOutDay;
    }
    
    public String getDiscountCode() {
        return discountFieldSB.getText();
    }
    
    public String getRoomType() {
        if(standardButtonSB.isSelected()) {
        	return "Standard";
        }else if(deluxeButtonSB.isSelected()) {
        	return "Deluxe";
        }else if(executiveButtonSB.isSelected()) {
        	return "Executive";
        }else return null;
    }
    
    public void disposeDialog() {
    	
    	if(standardButtonSB.isSelected()) {
        	standardButtonSB.setSelected(false);
        }
    	
    	if(deluxeButtonSB.isSelected()) {
    		deluxeButtonSB.setSelected(false);
        }
    	
    	if(executiveButtonSB.isSelected()) {
    		executiveButtonSB.setSelected(false);
        }
    	
    	
    	guestNameFieldSB.setText("");
    	discountFieldSB.setText("");
    	checkInDay = 0;
    	checkOutDay = 0;
    	updateButtonStates();
    	
    	dispose();
    }
    public String getGuestName() {
    	return guestNameFieldSB.getText();
    }

}
