package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


/**
 * A custom toolbar component that extends {@link JPanel}.
 * This toolbar contains a set of buttons for different functionalities related to hotel management and simulation.
 * It includes buttons for creating a hotel, viewing a hotel, managing a hotel, and simulating bookings.
 * 
 * @author Jusper Angelo Cesar
 * @version 4.4
 * 
 */
public class GUI_toolBar extends JPanel {

    
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5178967000993076846L;
	/**
	 * Button for creating new items or actions in the GUI.
	 */
	private PlainButton createButton;

	/**
	 * Toggle button for switching to the view mode in the GUI.
	 */
	private PlainToggleButton viewButton;

	/**
	 * Toggle button for switching to the manage mode in the GUI.
	 */
	private PlainToggleButton manageButton;

	/**
	 * Button for initiating simulation actions or dialogs in the GUI.
	 */
	private PlainButton simulateButton;

    /**
     * Constructs a {@code GUI_toolBar} and initializes the layout and buttons.
     * Sets the layout to {@link FlowLayout} with left alignment and sets the background color to a light gray.
     * Initializes and adds the buttons to the toolbar.
     */
    public GUI_toolBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(221, 221, 221));
        setBorder(null);
        createButton = createButton("Create Hotel");
        viewButton = createToggleButton("View Hotel");
        manageButton = createToggleButton("Manage Hotel");
        simulateButton = createButton("Simulate Booking");

        add(createButton);
        add(viewButton);
        add(manageButton);
        add(simulateButton);
    }
    /**
     * Returns the {@code PlainButton} used for simulating bookings.
     *
     * @return the button for simulating bookings
     */
    public PlainButton getSimulateButton() {
    	return simulateButton;
    }
    /**
     * Creates a {@code PlainButton} with the specified text.
     * Sets the action command of the button to the specified text.
     *
     * @param text the text to be displayed on the button
     * @return a new {@code PlainButton} instance with the specified text
     */
    private PlainButton createButton(String text) {
        PlainButton button = new PlainButton(text);
        button.setActionCommand(text);
        return button;
    }
    /**
     * Creates a {@code PlainToggleButton} with the specified text.
     * Sets the action command of the button to the specified text.
     *
     * @param text the text to be displayed on the button
     * @return a new {@code PlainToggleButton} instance with the specified text
     */
    private PlainToggleButton createToggleButton(String text) {
        PlainToggleButton button = new PlainToggleButton(text);
        button.setActionCommand(text);
        return button;
    }
    /**
     * Sets the action listener for all buttons in the toolbar.
     * This listener will be notified when any button is clicked.
     *
     * @param listener the {@code ActionListener} to be added to all buttons
     */
    public void setActionListener(ActionListener listener) {
        createButton.addActionListener(listener);
        viewButton.addActionListener(listener);
        manageButton.addActionListener(listener);
        simulateButton.addActionListener(listener);
    }
}

