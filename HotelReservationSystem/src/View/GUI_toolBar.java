package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GUI_toolBar extends JPanel {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5178967000993076846L;
	private PlainButton createButton;
    private PlainToggleButton viewButton;
    private PlainToggleButton manageButton;
    private PlainButton simulateButton;

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
    
    public PlainButton getSimulateButton() {
    	return simulateButton;
    }

    private PlainButton createButton(String text) {
        PlainButton button = new PlainButton(text);
        button.setActionCommand(text);
        return button;
    }

    private PlainToggleButton createToggleButton(String text) {
        PlainToggleButton button = new PlainToggleButton(text);
        button.setActionCommand(text);
        return button;
    }

    public void setActionListener(ActionListener listener) {
        createButton.addActionListener(listener);
        viewButton.addActionListener(listener);
        manageButton.addActionListener(listener);
        simulateButton.addActionListener(listener);
    }
}

