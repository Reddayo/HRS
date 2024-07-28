package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI_Create extends JDialog {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5466791194797092670L;
	private JTextField nameField;
    private JSpinner standardSpinner;
    private JSpinner deluxeSpinner;
    private JSpinner executiveSpinner;
    private JCheckBox defaultPriceCheckBox;
    private JTextField priceField;
    private JLabel priceLabel;
    private JButton createHotelButton;
    private int max_rooms;
    private double default_price;
    private double min_price;

    public GUI_Create(Frame owner) {

        super(owner, "Create Hotel", true);
        setLayout(new BorderLayout());
        setSize(300, 500);
        setLocationRelativeTo(owner);
        setResizable(false);

        //it is hard coded but i did make a setter just in case this has changed in the model;
        //so it can be easier changed.
        this.max_rooms = 50;
        this.default_price = 1299.00;
        this.min_price = 100.00;
    

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the name field
        nameField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name: ");

        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Create spinners
        standardSpinner = createSpinner();
        deluxeSpinner = createSpinner();
        executiveSpinner = createSpinner();

        defaultPriceCheckBox = new JCheckBox("Default Price (" + default_price + ")");
        defaultPriceCheckBox.setSelected(true); 

        priceField = new JTextField(10);
        priceField.setText(String.valueOf(default_price));

        priceLabel = new JLabel("Price (must be >= (" + String.valueOf(min_price)+"): ");

        priceField.setVisible(false);
        priceLabel.setVisible(false);

        JLabel standardLabel = new JLabel("Standard: ");
        JLabel deluxeLabel = new JLabel("Deluxe: ");
        JLabel executiveLabel = new JLabel("Executive: ");

        // Add labels and spinners to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(standardLabel);
        panel.add(standardSpinner);
        panel.add(deluxeLabel);
        panel.add(deluxeSpinner);
        panel.add(executiveLabel);
        panel.add(executiveSpinner);
        panel.add(defaultPriceCheckBox);
        panel.add(priceLabel);
        panel.add(priceField);

        // Add error label

        add(panel, BorderLayout.CENTER);


        createHotelButton = new JButton("Create");
        JPanel somePanel = new JPanel(new GridLayout(1, 3));
        somePanel.add(new JLabel(" "));
        somePanel.add(createHotelButton);
        somePanel.add(new JLabel(" "));
        somePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20 , 20));
        
       
        add(somePanel, BorderLayout.SOUTH);

      
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        
        addSpinnerListeners();
        addPriceCheckBoxListener();
    }
  
    public void updateParameters(int max_rooms, double default_price, double min_price){
        this.max_rooms = max_rooms;
        this.default_price = default_price;
        this.min_price = min_price;
    }

    private JSpinner createSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 50, 1);
        JSpinner spinner = new JSpinner(model);
        return spinner;
    }

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

    private void addPriceCheckBoxListener() {
        defaultPriceCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (defaultPriceCheckBox.isSelected()) {
                    priceLabel.setVisible(false);
                    priceField.setVisible(false);
                    priceField.setText(String.valueOf(default_price)); // Set default price
                } else {
                    priceLabel.setVisible(true);
                    priceField.setVisible(true);
                }
            }
        });
    }

    private void updateSpinnerModels() {
        int standard = (Integer) standardSpinner.getValue();
        int deluxe = (Integer) deluxeSpinner.getValue();
        int executive = (Integer) executiveSpinner.getValue();

    
        ((SpinnerNumberModel) standardSpinner.getModel()).setMaximum(max_rooms - deluxe - executive);
        ((SpinnerNumberModel) deluxeSpinner.getModel()).setMaximum(max_rooms - standard - executive);
        ((SpinnerNumberModel) executiveSpinner.getModel()).setMaximum(max_rooms - standard - deluxe);

        // Ensure no negative values
        if (standard > max_rooms - deluxe - executive) {
            standardSpinner.setValue(max_rooms - deluxe - executive);
        }
        if (deluxe > max_rooms - standard - executive) {
            deluxeSpinner.setValue(max_rooms - standard - executive);
        }
        if (executive > max_rooms - standard - deluxe) {
            executiveSpinner.setValue(max_rooms - standard - deluxe);
        }
    }

    public void addCreateButtonListener(ActionListener listener) {
        createHotelButton.addActionListener(listener);
    }

    public String getHotelName() throws IllegalArgumentException {
        String name = nameField.getText();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty.");
        }
        return name;
    }

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
    public double getPrice() throws NumberFormatException {
        try {
            if (defaultPriceCheckBox.isSelected()) {
                return default_price; // Default price if checkbox is selected
            }

            String priceText = priceField.getText().trim();
            if (priceText.isEmpty()) {
                throw new NumberFormatException("Price field is empty.");
            }
            double price = Double.parseDouble(priceText);
            if (price < min_price) {
                throw new NumberFormatException("Price must be >= " + min_price + " .");
            }
            return price;
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public boolean isDefaultPriceSelected() {
        return defaultPriceCheckBox.isSelected();
    }

    public void reset(){
        nameField.setText("");
        deluxeSpinner.setValue(0);
        standardSpinner.setValue(0);
        executiveSpinner.setValue(0);
        defaultPriceCheckBox.setEnabled(true);
        priceField.setText(String.valueOf(default_price));
    }
}
