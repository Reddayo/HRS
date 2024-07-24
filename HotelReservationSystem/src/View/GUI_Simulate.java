package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Simulate extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7007581124621903960L;
	private JLabel nameLabel;
    private JTextField guestNameField;
    private JLabel roomTypeLabel;
    private JToggleButton standardButton;
    private JToggleButton deluxeButton;
    private JToggleButton executiveButton;
    private JLabel checkInLabel;
    private JComboBox<String> checkInComboBox;
    private JLabel checkOutLabel;
    private JComboBox<String> checkOutComboBox;
    private JLabel discountLabel;
    private JTextField discountField;
    private JButton bookButton;
    private JLabel errorLabel;
    private boolean isValid;
    private int checkInDay;
    private int checkOutDay;

    GUI_Simulate(JFrame owner) {
        super(owner, "Simulate Booking", true);
        checkInDay = 1;
        checkOutDay = 2;
        isValid = true;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 5);

        nameLabel = new JLabel("Your Name:");
        guestNameField = new JTextField(20);

        roomTypeLabel = new JLabel("Room Type:");
        standardButton = new JToggleButton("Standard");
        deluxeButton = new JToggleButton("Deluxe");
        executiveButton = new JToggleButton("Executive");

        // Group the buttons together to allow only one selection at a time
        ButtonGroup roomTypeGroup = new ButtonGroup();
        roomTypeGroup.add(standardButton);
        roomTypeGroup.add(deluxeButton);
        roomTypeGroup.add(executiveButton);

        JPanel roomTypePanel = new JPanel();
        roomTypePanel.add(standardButton);
        roomTypePanel.add(deluxeButton);
        roomTypePanel.add(executiveButton);

        discountLabel = new JLabel("Discount Code:");
        discountField = new JTextField(20);

        checkInLabel = new JLabel("Check In:");
        checkInComboBox = new JComboBox<>();
        updateCheckInComboBox();
        checkInComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInDay = checkInComboBox.getSelectedIndex() + 1;
                updateCheckOutComboBox();
            }
        });

        checkOutLabel = new JLabel("Check Out:");
        checkOutComboBox = new JComboBox<>();
        updateCheckOutComboBox();
        checkOutComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOutDay = checkOutComboBox.getSelectedIndex() + 1;
            }
        });

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        bookButton = new JButton("Book");
        bookButton.setActionCommand("Book");
        bookButton.setEnabled(true); // Always enabled
       

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(guestNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(roomTypeLabel, gbc);

        gbc.gridx = 1;
        panel.add(roomTypePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(discountLabel, gbc);

        gbc.gridx = 1;
        panel.add(discountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(checkInLabel, gbc);

        gbc.gridx = 1;
        panel.add(checkInComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(checkOutLabel, gbc);

        gbc.gridx = 1;
        panel.add(checkOutComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bookButton, gbc);

        gbc.gridy = 6;
        panel.add(errorLabel, gbc);

        discountField.getDocument().addDocumentListener(new DocumentListener() {
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
                // Plain text components do not fire these events
            }
        });

        add(panel);
        setSize(450, 400);
        setLocationRelativeTo(owner);
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void updateCheckInComboBox() {
        checkInComboBox.removeAllItems();
        for (int i = 1; i <= 30; i++) { // Exclude day 31 for check in
            checkInComboBox.addItem("Day " + i);
        }
        if (checkInDay != 0) {
            checkInComboBox.setSelectedItem("Day " + checkInDay);
        }
    }

    private void updateCheckOutComboBox() {
        checkOutComboBox.removeAllItems();
        int startDay = checkInDay + 1; 
        for (int i = startDay; i <= 31; i++) {
            checkOutComboBox.addItem("Day " + i);
        }
        if (checkOutDay != 0) {
            checkOutComboBox.setSelectedItem("Day " + checkOutDay);
        }
    }

    private void checkDiscountCode() {
        String text = discountField.getText().trim();

        if (text.equals("I_WORK_HERE") || text.equals("STAY4_GET1") || text.equals("PAYDAY")) {
            errorLabel.setText("Valid discount code entered: " + text);
            errorLabel.setForeground(Color.GREEN);
            isValid = true;
        } else if (text.isEmpty()) {
            errorLabel.setText("");
            isValid = true;
        } else {
            errorLabel.setText("Invalid discount code.");
            errorLabel.setForeground(Color.RED);
            isValid = false;
        }
    }

   
    
    
    public int getCheckInDay() {
    	return checkInDay;
    }
    
    public int getCheckOutDay() {
    	return checkOutDay;
    }
    
    public String getDiscountCode() {
    	return discountField.getText().trim();
    }
    
    public String getGuestName() {
    	return guestNameField.getText().trim();
    }
    public String getRoomType() {
    	 if (standardButton.isSelected()) {
             return "Standard";
         } else if (deluxeButton.isSelected()) {
             return "Deluxe";
         } else if (executiveButton.isSelected()) {
             return "Executive";
         } else {
             return null;
         }
    }
    
    
    
    
    public void setActionListener(ActionListener listener) {
    	bookButton.addActionListener(listener);
    }
    
    public boolean isValidBooking() {
    	return isValid;
    }
   
    public void disposeDialog() {
    	standardButton.setSelected(false);
    	deluxeButton.setSelected(false);
    	executiveButton.setSelected(false);
    	errorLabel.setText("");
    	guestNameField.setText("");
    	discountField.setText("");
    	checkInDay = 1;
    	checkOutDay = 2;
    	
    	dispose();
    }

   
}
