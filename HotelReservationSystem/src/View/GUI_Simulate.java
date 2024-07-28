package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI_Simulate extends JDialog {

    private static final long serialVersionUID = 7007581124621903960L;
    private JLabel nameLabel;
    private JTextField guestNameField;
    private JLabel roomTypeLabel;
    private JToggleButton standardButton;
    private JToggleButton deluxeButton;
    private JToggleButton executiveButton;
    private JLabel checkInLabel;
    private JSpinner checkInSpinner;
    private JLabel checkOutLabel;
    private JSpinner checkOutSpinner;
    private JLabel discountLabel;
    private JTextField discountField;
    private JButton bookButton;
    private JLabel errorLabel;
    private boolean isValid;
    private  ButtonGroup roomTypeGroup;
	private DefaultListModel<String> listModel;
	private JList<String> priceList;
	private ArrayList<String> externalPriceList;
    GUI_Simulate(JFrame owner) {
        super(owner, "Simulate Booking", true);
    
        isValid = true;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 5);

        nameLabel = new JLabel("Your Name:");
        guestNameField = new JTextField(30);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        roomTypeLabel = new JLabel("Room Type:");
        standardButton = new JToggleButton("Standard");
        deluxeButton = new JToggleButton("Deluxe");
        executiveButton = new JToggleButton("Executive");

        standardButton.setBackground(Color.decode("0xC0C0C0"));
        deluxeButton.setBackground(Color.decode("0xA020F0"));
        executiveButton.setBackground(Color.decode("0xFFD700"));
        
        
        roomTypeGroup = new ButtonGroup();
        roomTypeGroup.add(standardButton);
        roomTypeGroup.add(deluxeButton);
        roomTypeGroup.add(executiveButton);

        JPanel roomTypePanel = new JPanel();
        roomTypePanel.add(standardButton);
        roomTypePanel.add(deluxeButton);
        roomTypePanel.add(executiveButton);

        discountLabel = new JLabel("Discount Code:");
        discountField = new JTextField(30);

        checkInLabel = new JLabel("Check In:");
        checkInSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));

        checkOutLabel = new JLabel("Check Out:");
        checkOutSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 31, 1));
        
        listModel = new DefaultListModel<>();
        priceList = new JList<>(listModel);
        externalPriceList = new ArrayList<String>();
        JScrollPane listScrollPane = new JScrollPane(priceList);
        listScrollPane.setPreferredSize(new Dimension(200, 300));
        
        
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        bookButton = new JButton("Book");
        bookButton.setActionCommand("Book");
        bookButton.setEnabled(true); 

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
        panel.add(checkInSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(checkOutLabel, gbc);

        gbc.gridx = 1;
        panel.add(checkOutSpinner, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        panel.add(listScrollPane, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bookButton, gbc);
        
        
        gbc.gridy = 7;
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
            }
        });

        add(panel);
        setSize(700, 400);
        setLocationRelativeTo(owner);
        setResizable(false);
        getContentPane().setForeground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

     
        checkInSpinner.addChangeListener(e -> updateCheckOutSpinner());
        checkOutSpinner.addChangeListener(e -> checkDiscountCode());
        checkOutSpinner.addChangeListener(e -> updateItemList());
    }
    
    
    
    
    private void updateItemList() {
        int checkInDay = (Integer) checkInSpinner.getValue();
        int checkOutDay = (Integer) checkOutSpinner.getValue();
        
        listModel.clear();
        for(int i = checkInDay-1; i < checkOutDay-1; i++) {
        	String s = externalPriceList.get(i);
        	listModel.addElement(s);
        }
       
    }
    
    
    public void updateDatePriceList(ArrayList<String> lis) {
    	externalPriceList = lis;
    	listModel.clear();
    	
    	
    	for(String s: lis) {
    		
    		listModel.addElement(s);
    	}
    	updateItemList();
    	
    
    }
    private void updateCheckOutSpinner() {
        int checkInDay = (Integer) checkInSpinner.getValue();
        checkOutSpinner.setModel(new SpinnerNumberModel(checkInDay + 1, checkInDay + 1, 31, 1));
        checkOutSpinner.setValue(checkInDay + 1); 
        checkDiscountCode();
        updateItemList();
    }

    private void checkDiscountCode() {
    	 String text = discountField.getText().trim();


    	    if (text.equals("I_WORK_HERE")) {
    	        errorLabel.setText("Valid discount code: " + text + " - 10% off.");
    	        errorLabel.setForeground(Color.GREEN);
    	        isValid = true;
    	    } else if (text.equals("STAY4_GET1")) {
    	        // Assuming you have methods to get reservation details
    	        int stayDuration = getCheckOutDay() - getCheckInDay(); 
    	        if (stayDuration >= 5) {
    	    
    	            errorLabel.setText("Valid discount code: " + text + " - First day is free.");
    	            errorLabel.setForeground(Color.GREEN);
    	            isValid = true;
    	        } else {
    	            errorLabel.setText("Invalid discount code for the stay duration.");
    	            errorLabel.setForeground(Color.RED);
    	            isValid = false;
    	            return;
    	        }
    	    } else if (text.equals("PAYDAY")) {
    	        int checkInDay = getCheckInDay(); 
    	        int checkOutDay = getCheckOutDay();
    	        if ((checkInDay <= 15 && checkOutDay > 15) || (checkInDay <= 30 && checkOutDay > 30)) {
    	         
    	            errorLabel.setText("Valid discount code: " + text + " - 7% off.");
    	            errorLabel.setForeground(Color.GREEN);
    	            isValid = true;
    	        } else {
    	            errorLabel.setText("Invalid discount code for the selected dates.");
    	            errorLabel.setForeground(Color.RED);
    	            isValid = false;
    	            return;
    	        }
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
        return (Integer) checkInSpinner.getValue();
    }

    public int getCheckOutDay() {
        return (Integer) checkOutSpinner.getValue();
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
    	roomTypeGroup.clearSelection();
        errorLabel.setText("");
        guestNameField.setText("");
        discountField.setText("");

        checkInSpinner.setValue(1);
        checkOutSpinner.setValue(2);
        if(isVisible()) {
        	dispose();
        }
    }
}
